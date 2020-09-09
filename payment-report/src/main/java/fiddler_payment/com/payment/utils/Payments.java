/**
 * 
 */
package fiddler_payment.com.payment.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.stripe.exception.StripeException;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Subscription;
import com.stripe.net.RequestOptions;

import fiddler_payment.com.payment.utils.model.PaymentModel;

/**
 * @author ppradhan
 *
 */
public class Payments {
	
	private RequestOptions requestOptions = null;
	
	public Payments(){
		 this.requestOptions = RequestOptions.builder()
				  .setApiKey("rk_test_51GsTFZAfeRYJS3I0OACGmiw0GmetVIBjgQg1BKIpVt92c9aLL7r1WjVCq7ONc9mG8mZt9zOu95kC3NS1S2LfaCxJ00w8DLnJqz")
				  .build();
	}
	
   public List<PaymentModel> getPaymentFrom(long fromDate) {
	   List<PaymentModel> paymentsRow = new ArrayList<PaymentModel>();
	   try {
			List<Charge> data = getCharges(fromDate);
			System.out.println("\nPreparing payment info...\n");
			System.out.println("\n--------"+data.size()+"--------\n");
			for (Charge charge : data) {
				if(charge.getPaid()) {
					System.out.println("\n.................\n");
					PaymentModel model = new PaymentModel();
					model.setPaymentId(charge.getId());
					model.setDescription(charge.getDescription());
					model.setSellerMessage(""); //TODO
					Invoice invoice = Invoice.retrieve(charge.getInvoice(),requestOptions);
					model.setInvoiceId(invoice.getId());
					Long date = charge.getCreated();
					System.out.println("\n Charge Date "+date+"\n");
					model.setCreatedAt(formatDate(date));
					model.setAmount(String.valueOf(charge.getAmount()/100));
					model.setAmountRefunded(charge.getAmountRefunded().toString());
					model.setCurrency(charge.getCurrency());
					model.setConvertedAmount("");//TODO find and fill
					System.out.println("\n.................\n");
					double fees = getTransactionFees(charge);
					model.setFee(fees);
					model.setTax(""); //TODO find tax and update 
					model.setConvertedCurrency(charge.getCurrency()); //TODO need to find converted currency now using charge currency
					model.setStatus(invoice.getStatus());
					model.setCustomerId(charge.getCustomer());
					model.setCustomerEmail(charge.getReceiptEmail());
					//model.setCardId(); source id is not available need to find ways
					String paymentType = getPaymentType(charge);
					model.setPaymentSourceType(paymentType);
					System.out.println("\n.................\n");
					Subscription subscription = Subscription.retrieve(invoice.getSubscription(), requestOptions);
					
					model.setSubscriptionType(subscription.getPlan().getInterval());
					model.setSubscriptionStart(formatDate(subscription.getCurrentPeriodStart()));
					model.setSubscriptionEnd(formatDate(subscription.getCurrentPeriodEnd()));
					paymentsRow.add(model);
					System.out.println("\n........."+paymentsRow.size()+"........\n");
					System.out.println("\n.................\n");
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	return paymentsRow;
   }

private List<Charge> getCharges(long fromDate) throws StripeException {
	HashMap<String, Object> params = new HashMap<String, Object>();
	params.put("created[gt]", String.valueOf(fromDate));
	//params.put("starting_after", "ch_1HB5YSJlNjFitnDH4eC6ODmq");
	params.put("status","succeeded");
	ChargeCollection list = Charge.list(params, requestOptions);
	List<Charge> data = list.getData();
	System.out.println(new Gson().toJson(data));
	data = filterUnpaidCharges(data,fromDate);
	System.out.println("\nfirstChargeId : "+data.get(0).getId()+"\n");
	String lastChargeId = data.get(data.size()-1).getId();
	System.out.println("\nlastChargeId : "+lastChargeId+"\n");
	data = addToChargeList(data,lastChargeId,fromDate);
	return data;
}

private List<Charge> filterUnpaidCharges(List<Charge> data, long fromDate) {
	ListIterator<Charge> listIterator = data.listIterator();
	while(listIterator.hasNext()) {
		Charge charge = listIterator.next();
		if(!charge.getPaid() || !charge.getLivemode() || charge.getCreated()<fromDate) listIterator.remove();
	}
	return data;
}


private List<Charge> addToChargeList(List<Charge> data, String lastChargeId , long fromDate) throws StripeException {
	HashMap<String, Object> params = new HashMap<String, Object>();
	params.put("starting_after", lastChargeId);
	ChargeCollection list = Charge.list(params, requestOptions);
	if(list!=null && !list.getData().isEmpty()) {
		List<Charge> chainData = list.getData();
		String chin_lastChargeId = chainData.get(chainData.size()-1).getId();
		chainData = filterUnpaidCharges(chainData,fromDate);
		data.addAll(chainData);
		data = addToChargeList(data,chin_lastChargeId,fromDate);
	}
	return data;
}

private String getPaymentType(Charge charge) throws StripeException {
	PaymentMethod paymentMethod = PaymentMethod.retrieve(charge.getPaymentMethod(), requestOptions);
	String paymentType = "";
	if(paymentMethod.getType()!=null)
		paymentType=paymentMethod.getType();
	return paymentType;
}

private double getTransactionFees(Charge charge) throws StripeException {
	List<BalanceTransaction> transactionsByCharge = getBalanceTransactionsByCharge(charge);
	double fees = 0L;
	for (BalanceTransaction balanceTransaction : transactionsByCharge) {
		fees += balanceTransaction.getFee();
	}
	return fees/100;
}

private List<BalanceTransaction> getBalanceTransactionsByCharge(Charge charge) throws StripeException {
	Map<String, Object> balanceTransactionParams = new HashMap<String, Object>();
	balanceTransactionParams.put("source", charge.getId());
	BalanceTransactionCollection transactionCollection = BalanceTransaction.list(balanceTransactionParams, requestOptions);
	List<BalanceTransaction> transactionsByCharge = transactionCollection.getData();
	return transactionsByCharge;
}
   
   public void printPaymentFrom(long fromDate) {
	   List<PaymentModel> payments = getPaymentFrom(fromDate);
	   if(payments!=null && payments.size()>0) {
			System.out.println("\n Writing to excel\n");
			new ExcelWriter().writeExcel("resource/payment.xlsx", payments);
			System.out.println("\n completed writing to excel\n");
		}
   }
   
   private String formatDate(Long date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(new Date(date*1000L));
	}
   
   public void printChargeInfo(String charge) {
	   charge = "ch_1HAzvlJlNjFitnDHMBE62bgu";
	   try {
		Charge retrieve = Charge.retrieve(charge, requestOptions);
		System.out.println(new Gson().toJson(retrieve));
	} catch (StripeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

}

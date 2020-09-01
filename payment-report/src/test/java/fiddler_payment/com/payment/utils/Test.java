/**
 * 
 */
package fiddler_payment.com.payment.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stripe.exception.StripeException;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Plan;
import com.stripe.model.Subscription;
import com.stripe.model.SubscriptionCollection;
import com.stripe.net.RequestOptions;

import fiddler_payment.com.payment.utils.model.PaymentModel;

/**
 * @author ppradhan
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Payments payments = new Payments();
		payments.printPaymentFrom(1596240000);
		
//		SimpleDateFormat sdf = new SimpleDateFormat();
//		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//		System.out.println(sdf.format(new Date(1598757770*1000L)));
		//payments.printChargeInfo("");
		
//		RequestOptions requestOptions = RequestOptions.builder()
//				  .setApiKey("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")
//				  .build();
		try {
			
//			Gson gson = new Gson();
//			HashMap<String, Object> params = new HashMap<String, Object>();
//			params.put("starting_after", "ch_1HCSWiJlNjFitnDHtaDGa8bO");
//			ChargeCollection list = Charge.list(params, requestOptions);
//			System.out.println(gson.toJson(list.getData()));
//			HashMap<String, Object> params = new HashMap<String, Object>();
//			params.put("created[gt]", 1596240000);
//			Customer.list(new HashMap<String, Object>(), requestOptions);
//			//Customer retrieve = Customer.retrieve("cus_HrvYhqr2RfWGT3", requestOptions);
//			//System.out.println(gson.toJson(retrieve));
//			List<PaymentModel> paymentsRow = new ArrayList<PaymentModel>();
//			ChargeCollection list = Charge.list(params, requestOptions);
//			List<Charge> data = list.getData();
//			for (Charge charge : data) {
//				String json = gson.toJson(charge);
//				if(charge.getPaid()) {
//					PaymentModel model = new PaymentModel();
//					model.setPaymentId(charge.getId());
//					model.setDescription(charge.getDescription());
////					System.out.println(gson.toJson(charge));
//					model.setSellerMessage(""); //TODO
//					Invoice invoice = Invoice.retrieve(charge.getInvoice(),requestOptions);
//					System.out.println(gson.toJson(invoice.getDefaultSource()));
//					Long date = charge.getCreated();
//					SimpleDateFormat sdf = new SimpleDateFormat();
//			        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
//					model.setCreatedAt(sdf.format(new Date(date)));
//					model.setAmount(charge.getAmount().toString());
//					model.setAmountRefunded(charge.getAmountRefunded().toString());
//					model.setCurrency(charge.getCurrency());
//					model.setConvertedAmount("");//TODO find and fill
//					
//					Map<String, Object> balanceTransactionParams = new HashMap<String, Object>();
//					balanceTransactionParams.put("source", charge.getId());
//					BalanceTransactionCollection transactionCollection = BalanceTransaction.list(balanceTransactionParams, requestOptions);
//					List<BalanceTransaction> transactionsByCharge = transactionCollection.getData();
//					long fees = 0L;
//					for (BalanceTransaction balanceTransaction : transactionsByCharge) {
//						fees += balanceTransaction.getFee();
//					}
//					model.setFee(String.valueOf(fees));
//					model.setTax(""); //TODO find tax and update 
//					model.setConvertedCurrency(charge.getCurrency()); //TODO need to find converted currency now using charge currency
//					model.setStatus(invoice.getStatus());
//					model.setCustomerId(charge.getCustomer());
//					model.setCustomerEmail(charge.getReceiptEmail());
//					//model.setCardId(); source id is not available need to find ways
//					JsonElement jsonElement = new JsonParser().parse(json);
//					JsonObject customerInfo = jsonElement.getAsJsonObject().get("customer").getAsJsonObject();
//					String customerId = customerInfo.get("id").getAsString();
//					Customer customer = Customer.retrieve(customerId, requestOptions);
//					SubscriptionCollection subscriptions = customer.getSubscriptions();
//					Subscription subscription = subscriptions.getData().get(0);
//					Plan plan = subscription.getPlan();
//					PaymentMethod paymentMethod = PaymentMethod.retrieve(charge.getPaymentMethod(), requestOptions);
//					String paymentType = "";
//					if(paymentMethod.getType()!=null)
//						paymentType=paymentMethod.getType();
//					model.setPaymentSourceType(paymentType);
//					Subscription subscription3 = Subscription.retrieve(invoice.getSubscription(), requestOptions);
//					
//					model.setSubscriptionType(subscription3.getPlan().getInterval());
//					model.setSubscriptionStart(String.valueOf(subscription3.getCurrentPeriodStart()));
//					model.setSubscriptionEnd(String.valueOf(subscription3.getCurrentPeriodEnd()));
//					paymentsRow.add(model);
//				}
//				
//			}
//			if(paymentsRow.size()>0) {
//				System.out.println("\n Writing to excel\n");
//				Test test = new Test();
//				test.writeExcel("resource/payment.xlsx", paymentsRow);
//				System.out.println("\n completed writing to excel\n");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

/**
 * 
 */
package fiddler_payment.com.payment.utils.model;

/**
 * @author ppradhan
 *
 */
public class PaymentModel {

	private String paymentId;
	private String description;
	private String sellerMessage;
	private String createdAt;
	private String amount;
	private String amountRefunded;
	private String currency;
	private String convertedAmount;
	private double fee;
	private String tax;
	private String convertedCurrency;
	private String status;
	private String customerId;
	private String customerEmail;
	private String cardId;
	private String invoiceId;
	private String paymentSourceType;
	private String subscriptionType;
	private String subscriptionStart;
	private String subscriptionEnd;

	public String getPaymentId() {
		return paymentId;
	}

	public String getDescription() {
		return description;
	}

	public String getSellerMessage() {
		return sellerMessage;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getAmount() {
		return amount;
	}

	public String getAmountRefunded() {
		return amountRefunded;
	}

	public String getCurrency() {
		return currency;
	}

	public String getConvertedAmount() {
		return convertedAmount;
	}

	public double getFee() {
		return fee;
	}

	public String getTax() {
		return tax;
	}

	public String getConvertedCurrency() {
		return convertedCurrency;
	}

	public String getStatus() {
		return status;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public String getCardId() {
		return cardId;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public String getPaymentSourceType() {
		return paymentSourceType;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public String getSubscriptionStart() {
		return subscriptionStart;
	}

	public String getSubscriptionEnd() {
		return subscriptionEnd;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSellerMessage(String sellerMessage) {
		this.sellerMessage = sellerMessage;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setAmountRefunded(String amountRefunded) {
		this.amountRefunded = amountRefunded;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setConvertedAmount(String convertedAmount) {
		this.convertedAmount = convertedAmount;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public void setConvertedCurrency(String convertedCurrency) {
		this.convertedCurrency = convertedCurrency;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public void setPaymentSourceType(String paymentSourceType) {
		this.paymentSourceType = paymentSourceType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public void setSubscriptionStart(String subscriptionStart) {
		this.subscriptionStart = subscriptionStart;
	}

	public void setSubscriptionEnd(String subscriptionEnd) {
		this.subscriptionEnd = subscriptionEnd;
	}

}

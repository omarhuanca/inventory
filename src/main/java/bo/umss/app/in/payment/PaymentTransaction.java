package bo.umss.app.in.payment;

import bo.umss.app.in.NoteTransaction;
import bo.umss.app.in.discount.Discount;
import bo.umss.app.in.price.Price;

public class PaymentTransaction {

	public static final String TOTAL_CAN_NOT_BE_NULL = "Total can not be null";
	public static final String DISCOUNT_CAN_NOT_BE_NULL = "Discount can not be null";
	public static final String AMOUNT_TO_PAY_CAN_NOT_BE_NULL = "Amount to pay can not be null";
	public static final String NOTE_TRANSACTION_CAN_NOT_BE_NULL = "Note referral can not be null";

	private Price total;
	private Discount discount;
	private Price amountToPay;
	private NoteTransaction noteTransaction;

	public PaymentTransaction(Price total, Discount discount, Price amountToPay, NoteTransaction noteTransaction) {
		this.total = total;
		this.discount = discount;
		this.amountToPay = amountToPay;
		this.noteTransaction = noteTransaction;
	}

	public static PaymentTransaction at(Price total, Discount discount, Price amountToPay, NoteTransaction noteTransaction) {
		if (null == total)
			throw new RuntimeException(TOTAL_CAN_NOT_BE_NULL);
		if (null == discount)
			throw new RuntimeException(DISCOUNT_CAN_NOT_BE_NULL);
		if (null == amountToPay)
			throw new RuntimeException(AMOUNT_TO_PAY_CAN_NOT_BE_NULL);
		if (null == noteTransaction)
			throw new RuntimeException(NOTE_TRANSACTION_CAN_NOT_BE_NULL);

		return new PaymentTransaction(total, discount, amountToPay, noteTransaction);
	}

	public Price getTotal() {
		return total;
	}

	public Discount getDiscount() {
		return discount;
	}

	public Price getAmountToPay() {
		return amountToPay;
	}

	public NoteTransaction getNoteTransaction() {
		return noteTransaction;
	}	
}

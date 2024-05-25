package bo.umss.app.in.payment;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.NoteTransaction;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.discount.Discount;
import bo.umss.app.in.price.Price;
import bo.umss.app.in.referral.NoteReferral;

public class PaymentTransactionTest {

	public Price total;
	public Discount discount;
	public Price amountToPay;
	public NoteTransaction noteReferral;

	@BeforeEach
	public void setUp() {
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		total = Price.at(10, coin);
		discount = Discount.at(0, coin);
		amountToPay = Price.at(total.getValue(), coin);
		LocalDate date = LocalDate.of(2024, 05, 18);
		noteReferral = NoteReferral.at(date);
	}

	@Test
	public void canNotBeNullTotal() {
		assertThrows(RuntimeException.class, () -> PaymentTransaction.at(null, discount, amountToPay, noteReferral),
				PaymentTransaction.TOTAL_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotBeNullDiscount() {
		assertThrows(RuntimeException.class, () -> PaymentTransaction.at(total, null, amountToPay, noteReferral),
				PaymentTransaction.DISCOUNT_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotBeNullAmountToPay() {
		assertThrows(RuntimeException.class, () -> PaymentTransaction.at(total, discount, null, noteReferral),
				PaymentTransaction.AMOUNT_TO_PAY_CAN_NOT_BE_NULL);
	}

	@Test
	public void canNotBeNullNoteReferral() {
		assertThrows(RuntimeException.class, () -> PaymentTransaction.at(total, discount, amountToPay, null),
				PaymentTransaction.NOTE_TRANSACTION_CAN_NOT_BE_NULL);
	}
}

package bo.umss.app.in.referral;

import java.time.LocalDate;

import bo.umss.app.in.Transaction;
import bo.umss.app.in.codeProduct.CodeProduct;

public class Referral extends Transaction {

	public Referral(CodeProduct codeProduct, Integer amount, LocalDate localDate) {
		this.codeProduct = codeProduct;
		this.amount = amount;
		this.localDate = localDate;
	}

	public static Referral at(CodeProduct codeProduct, Integer amount, LocalDate localDate) {
		if (null == codeProduct)
			throw new RuntimeException(Transaction.CODE_PRODUCT_CAN_NOT_BE_NULL);
		if (0 >= amount)
			throw new RuntimeException(Transaction.AMOUNT_CAN_NOT_BE_LESS_THAN_ZERO);
		if(null == localDate)
			throw new RuntimeException(Transaction.DATE_CAN_NOT_BE_NULL);

		return new Referral(codeProduct, amount, localDate);
	}

	@Override
	public CodeProduct getCodeProduct() {
		return codeProduct;
	}

	@Override
	public Integer getAmount() {
		return amount;
	}

	@Override
	public LocalDate getLocalDate() {
		return localDate;
	}
}

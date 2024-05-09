package bo.umss.app.in.referral;

import bo.umss.app.in.Transaction;
import bo.umss.app.in.codeProduct.CodeProduct;

public class Referral implements Transaction {

	public static final String CODE_PRODUCT_CAN_NOT_BE_NULL = "Code product cant not be null";
	public static final String INVALID_AMOUNT_CAN_NOT_BE_GREATHER_ = "Amount can not be greather than zero or equal to zero";

	public CodeProduct codeProduct;
	public Integer amount;

	public Referral(CodeProduct codeProduct, Integer amount) {
		this.codeProduct = codeProduct;
		this.amount = amount;
	}

	public static Referral at(CodeProduct codeProduct, Integer amount) {
		if (null == codeProduct)
			throw new RuntimeException(Referral.CODE_PRODUCT_CAN_NOT_BE_NULL);
		if (0 >= amount)
			throw new RuntimeException(Referral.INVALID_AMOUNT_CAN_NOT_BE_GREATHER_);

		return new Referral(codeProduct, amount);
	}

	public CodeProduct getCodeProduct() {
		return codeProduct;
	}

	public Integer getAmount() {
		return amount;
	}

}

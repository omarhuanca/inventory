package bo.umss.app.in;

public class Referral {

	public static final String INVALID_CODE_PRODUCT = "Code product cant not be null";
	public static final String INVALID_DESCRIPTION = "Description can not be empty";
	public static final String INVALID_AMOUNT = "Amount can not be greather than zero or equal to zero";
	public static final String QUANTITY_GREATHER_THAN_AVAILABLE = "Quantity greather than avaible";
	public CodeProduct codeProduct;
	public String description;
	public Integer amount;

	public Referral(CodeProduct codeProduct, String description, Integer amount) {
		this.codeProduct = codeProduct;
		this.description = description;
		this.amount = amount;
	}

	public static Referral at(CodeProduct codeProduct, String description, Integer amount) {
		if (null == codeProduct)
			throw new RuntimeException(Referral.INVALID_CODE_PRODUCT);
		if (description.isEmpty())
			throw new RuntimeException(Referral.INVALID_DESCRIPTION);
		if (0 >= amount)
			throw new RuntimeException(Referral.INVALID_AMOUNT);

		return new Referral(codeProduct, description, amount);
	}

	public void withdraw(Integer potencialAmount) {
		
	}

}

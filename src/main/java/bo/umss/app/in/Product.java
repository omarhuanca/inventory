package bo.umss.app.in;

import java.util.List;

public abstract class Product {

	public static final String INVALID_CODE_PRODUCT = "Code product can not be null";
	public static final String INVALID_DESCRIPTION = "Description can not be null";
	public static final String INVALID_AMOUNT = "Amount can not be zero or negative";
	public static final String INVALID_PRICE_COST = "Price cost can not be zero or negative";
	public static final String INVALID_PRICE_SALE = "Price sale can not be zero or negative";
	public static final String ALREADY_CODE_PRODUCT = "Code product already exists";

	protected CodeProduct codeProduct;
	protected Integer amount;
	protected Integer priceCost;
	protected Integer priceSale;
	protected List<ChangePrice> listChangePriceCost;
	protected List<Transaction> listTransaction;

	// start method get
	public abstract CodeProduct getCodeProduct();

	public abstract Integer getAmount();

	public abstract Integer getPriceCost();

	public abstract void setPriceCost(Integer priceCost);
	
	public abstract Integer getPriceSale();

	public abstract List<ChangePrice> getListChangePriceCost();

	public abstract List<Transaction> getListTransaction();

	// start method product
	public abstract Boolean canAddAnyTransaction();

	public abstract Boolean listTransactionCompareGreatherThanZero(Integer count);

	public abstract Boolean alreadyCodeProduct();

	public abstract Boolean amountGreatherThanZero();

	public abstract void addBuy(Buy buy);

	public abstract Boolean wasAddAnyBuy(CodeProduct codeProduct);

	public abstract Boolean canIncreaseAmount(Integer potencialAmount);

	public abstract void todoIncreaseAmount(Integer potencialAmount);
}

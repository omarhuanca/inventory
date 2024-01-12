package bo.umss.app.in;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Buy implements Transaction {

	public static final String INVALID_CODE_PRODUCT = "Code product can not be null";
	public static final String INVALID_DESCRIPTION = "Description can not be null";
	public static final String INVALID_AMOUNT = "Amount can not be zero or negative";
	public static final String INVALID_PRICE_COST = "Price cost can not be zero or negative";
	public static final String INVALID_PRICE_SALE = "Price sale can not be zero or negative";

	private CodeProduct codeProduct;
	private String description;
	private Integer amount;
	private Integer priceCost;
	private Integer priceSale;
	private List<ChangePrice> listChangePriceCost;

	private Buy(CodeProduct codeProduct, String description, Integer amount, Integer priceCost, Integer priceSale) {
		this.codeProduct = codeProduct;
		this.description = description;
		this.amount = amount;
		this.priceCost = priceCost;
		this.priceSale = priceSale;
		this.listChangePriceCost = new ArrayList<>();
	}

	public static Buy at(CodeProduct codeProduct, String description, Integer amount, Integer priceCost,
			Integer priceSale) {
		if (null == codeProduct)
			throw new RuntimeException(INVALID_CODE_PRODUCT);
		if (description.isEmpty())
			throw new RuntimeException(INVALID_DESCRIPTION);
		if (0 >= amount)
			throw new RuntimeException(INVALID_AMOUNT);
		if (0 >= priceCost)
			throw new RuntimeException(INVALID_PRICE_COST);
		if (0 >= priceSale)
			throw new RuntimeException(INVALID_PRICE_SALE);

		return new Buy(codeProduct, description, amount, priceCost, priceSale);
	}

	public CodeProduct getCodeProduct() {
		return codeProduct;
	}

	public String getDescription() {
		return description;
	}

	public Integer getAmount() {
		return amount;
	}

	public Integer getPriceCost() {
		return priceCost;
	}

	public Integer getPriceSale() {
		return priceSale;
	}

	public List<ChangePrice> getListChangePriceCost() {
		return listChangePriceCost;
	}

	public Boolean amountGreatherThanZero() {
		return amount > 0;
	}

	public void changePriceBuy(Integer potencialPriceCost) {
		if (potencialPriceCost > priceCost) {
			LocalDate currentDate = LocalDate.now();
			ChangePrice changePrice = ChangePrice.at(potencialPriceCost, priceCost, currentDate);
			listChangePriceCost.add(changePrice);
		}
	}

}

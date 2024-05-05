package bo.umss.app.in.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bo.umss.app.in.Transaction;
import bo.umss.app.in.buy.Buy;
import bo.umss.app.in.changePrice.ChangePrice;
import bo.umss.app.in.codeProduct.CodeProduct;

public class Product {

	public static final String INVALID_CODE_PRODUCT = "Code product can not be null";
	public static final String INVALID_DESCRIPTION = "Description can not be null";
	public static final String INVALID_AMOUNT = "Amount can not be zero or negative";
	public static final String INVALID_PRICE_COST = "Price cost can not be zero or negative";
	public static final String INVALID_PRICE_SALE = "Price sale can not be zero or negative";
	public static final String ALREADY_CODE_PRODUCT = "Code product already exists";

	private CodeProduct codeProduct;
	private Integer amount;
	private Integer priceCost;
	private Integer priceSale;
	private List<ChangePrice> listChangePriceCost;
	private List<Transaction> listTransaction;

	public Product(CodeProduct codeProduct, Integer amount, Integer priceCost, Integer priceSale) {
		this.codeProduct = codeProduct;
		this.amount = amount;
		this.priceCost = priceCost;
		this.priceSale = priceSale;
		this.listChangePriceCost = new ArrayList<>();
		this.listTransaction = new ArrayList<>();
	}

	public static Product at(CodeProduct codeProduct, Integer amount, Integer priceCost,
			Integer priceSale) {
		if (null == codeProduct)
			throw new RuntimeException(INVALID_CODE_PRODUCT);
		if (0 >= amount)
			throw new RuntimeException(INVALID_AMOUNT);
		if (0 >= priceCost)
			throw new RuntimeException(INVALID_PRICE_COST);
		if (0 >= priceSale)
			throw new RuntimeException(INVALID_PRICE_SALE);

		return new Product(codeProduct, amount, priceCost, priceSale);
	}
	
	public CodeProduct getCodeProduct() {
		return codeProduct;
	}

	public Integer getAmount() {
		return amount;
	}

	public Integer getPriceCost() {
		return priceCost;
	}

	public void setPriceCost(Integer priceCost) {
		this.priceCost = priceCost;
	}

	public Integer getPriceSale() {
		return priceSale;
	}

	public List<ChangePrice> getListChangePriceCost() {
		return listChangePriceCost;
	}

	public List<Transaction> getListTransaction() {
		return listTransaction;
	}

	public Boolean canAddAnyTransaction() {
		return codeProduct.existCode();
	}

	public Boolean listTransactionCompareGreatherThanZero(Integer count) {
		return listTransaction.size() > count;
	}

	public Boolean alreadyCodeProduct() {
		return null != codeProduct;
	}

	public Boolean amountGreatherThanZero() {
		return amount > 0;
	}

	public void addBuy(Buy buy) {
		if (amountGreatherThanZero()) {
			Optional<Transaction> buyOptional = listTransaction.stream().filter(item -> item instanceof Buy
					&& ((Buy) item).getProduct().getCodeProduct().compareAnotherCode(buy.getProduct().getCodeProduct()))
					.findAny();
			if (buyOptional.isPresent())
				throw new RuntimeException(Product.ALREADY_CODE_PRODUCT);

			listTransaction.add(buy);
		}
	}

	public Boolean wasAddAnyBuy(CodeProduct codeProduct) {
		List<Transaction> filterBuy = listTransaction.stream()
				.filter(item -> item instanceof Buy
						&& ((Buy) item).getProduct().getCodeProduct().compareAnotherCode(codeProduct))
				.collect(Collectors.toList());

		return filterBuy.size() > 0;
	}

	public Boolean canIncreaseAmount(Integer potencialAmount) {
		return amount >= potencialAmount;
	}

	public void todoIncreaseAmount(Integer potencialAmount) {
		if (this.canIncreaseAmount(potencialAmount)) {
			amount = amount - potencialAmount;
		}
	}
}

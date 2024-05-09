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

	public static final String CODE_CAN_NOT_BE_NULL = "Code product can not be null";
	public static final String DESCRIPTION_CAN_NOT_BE_NULL = "Description can not be null";
	public static final String AMOUNT_CAN_NOT_BE_LESS_THAN = "Amount can not be zero or negative";
	public static final String PRICE_COST_CAN_NOT_BE_LESS_THAN = "Price cost can not be zero or negative";
	public static final String PRICE_SALE_CAN_NOT_BE_LESS_THAN = "Price sale can not be zero or negative";
	public static final String CODE_PRODUCT_DUPLICATE = "Code product already exists";

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
			throw new RuntimeException(CODE_CAN_NOT_BE_NULL);
		if (0 >= amount)
			throw new RuntimeException(AMOUNT_CAN_NOT_BE_LESS_THAN);
		if (0 >= priceCost)
			throw new RuntimeException(PRICE_COST_CAN_NOT_BE_LESS_THAN);
		if (0 >= priceSale)
			throw new RuntimeException(PRICE_SALE_CAN_NOT_BE_LESS_THAN);

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
				throw new RuntimeException(Product.CODE_PRODUCT_DUPLICATE);

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

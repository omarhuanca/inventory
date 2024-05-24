package bo.umss.app.in.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import bo.umss.app.in.Transaction;
import bo.umss.app.in.buy.Buy;
import bo.umss.app.in.changePrice.ChangePrice;
import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.price.Price;
import bo.umss.app.in.referral.Referral;
import bo.umss.app.in.stock.Stock;

public class Product {

	public static final String CODE_CAN_NOT_BE_NULL = "Code product can not be null";
	public static final String DESCRIPTION_CAN_NOT_BE_NULL = "Description can not be null";
	public static final String STOCK_CAN_NOT_BE_NULL = "Stock can not be null";
	public static final String CODE_PRODUCT_DUPLICATE = "Code product already exists";
	public static final String PRICE_COST_CAN_NOT_BE_NULL = "Price cost can not be null";
	public static final String PRICE_SALE_CAN_NOT_BE_NULL = "Price sale can not be null";

	private CodeProduct codeProduct;
	private Stock stock;
	private Price priceCost;
	private Price priceSale;
	private List<ChangePrice> listChangePriceCost;
	private List<Transaction> listTransaction;

	public Product(CodeProduct codeProduct, Stock stock, Price priceCost, Price priceSale) {
		this.codeProduct = codeProduct;
		this.stock = stock;
		this.priceCost = priceCost;
		this.priceSale = priceSale;
		this.listChangePriceCost = new ArrayList<>();
		this.listTransaction = new ArrayList<>();
	}

	public static Product at(CodeProduct codeProduct, Stock stock, Price priceCost, Price priceSale) {
		if (null == codeProduct)
			throw new RuntimeException(CODE_CAN_NOT_BE_NULL);
		if (null == stock)
			throw new RuntimeException(STOCK_CAN_NOT_BE_NULL);
		if (null == priceCost)
			throw new RuntimeException(PRICE_COST_CAN_NOT_BE_NULL);
		if (null == priceSale)
			throw new RuntimeException(PRICE_SALE_CAN_NOT_BE_NULL);

		return new Product(codeProduct, stock, priceCost, priceSale);
	}

	public CodeProduct getCodeProduct() {
		return codeProduct;
	}

	public Stock getStock() {
		return stock;
	}

	private void setStock(Stock potentialStock) {
		stock = potentialStock;
	}

	public Price getPriceCost() {
		return priceCost;
	}

	public void setPriceCost(Price potentialPriceCost) {
		priceCost = potentialPriceCost;
	}

	public Price getPriceSale() {
		return priceSale;
	}

	public List<ChangePrice> getListChangePriceCost() {
		return listChangePriceCost;
	}

	public List<Transaction> getListTransaction() {
		return listTransaction;
	}

	public Boolean listTransactionCompareGreatherThanZero(Integer count) {
		return listTransaction.size() > count;
	}

	public Boolean alreadyCodeProduct() {
		return null != codeProduct;
	}

	public void addBuy(Buy buy) {
		if (stock.amountGreatherThanZero()) {
			Optional<Transaction> buyOptional = listTransaction.stream().filter(item -> item instanceof Buy
					&& ((Buy) item).getCodeProduct().compareAnotherCode(buy.getCodeProduct())).findAny();
			if (buyOptional.isPresent())
				throw new RuntimeException(Product.CODE_PRODUCT_DUPLICATE);

			if(stock.verifyPotentialValueGreatherZero(buy.getAmount())) {
				stock.todoIncreaseStock(buy.getAmount());
			}
			listTransaction.add(buy);
		}
	}

	public Boolean lessThanValuePriceCost(Price potentialPriceCost) {
		return priceCost.lessThanValue(potentialPriceCost);
	}

	public void changePriceBuy(Price potentialPriceCost, Stock stock) {
		if (lessThanValuePriceCost(potentialPriceCost)
				&& priceCost.getCoin().compareCode(potentialPriceCost.getCoin())) {
			LocalDate currentDate = LocalDate.now();
			ChangePrice changePrice = ChangePrice.at(potentialPriceCost, getPriceCost(), stock, currentDate);
			getListChangePriceCost().add(changePrice);
			setPriceCost(potentialPriceCost);
		}

	}

	public Boolean canDecreaseStock(Integer amount) {
		return stock.verifyValueGreatherThanPotentialValue(amount);
	}

	public void todoDecrementStock(Integer amount) {
		stock.todoDecrementStock(amount);
	}

	public void changeMesurementStock(Stock potentialStock) {
		if(stock.compareValue(potentialStock.getValue())) {
			setStock(potentialStock);
		}
	}

	public void addReferral(Referral referral) {
		todoDecrementStock(referral.getAmount());
		listTransaction.add(referral);
	}
}

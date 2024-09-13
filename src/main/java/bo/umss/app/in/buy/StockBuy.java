package bo.umss.app.in.buy;

import java.time.LocalDate;

import bo.umss.app.in.StockTransaction;
import bo.umss.app.in.product.Product;

public class StockBuy extends StockTransaction {

	public static final String DESCRIPTION_CAN_NOT_BE_BLANK = "Description can not be empty";

	private String description;

	public StockBuy(Product codeProduct, Integer amount, LocalDate localDate, String description) {
		this.product = codeProduct;
		this.amount = amount;
		this.localDate = localDate;
		this.description = description;
	}

	public static StockBuy at(Product product, Integer amount, LocalDate localDate, String description) {
		if (null == product)
			throw new RuntimeException(StockTransaction.PRODUCT_CAN_NOT_BE_NULL);
		if (0 >= amount)
			throw new RuntimeException(StockTransaction.AMOUNT_CAN_NOT_BE_LESS_THAN_ZERO);
		if (null == localDate)
			throw new RuntimeException(StockTransaction.DATE_CAN_NOT_BE_NULL);
		if (description.isEmpty())
			throw new RuntimeException(StockBuy.DESCRIPTION_CAN_NOT_BE_BLANK);

		return new StockBuy(product, amount, localDate, description);
	}

	@Override
	public Product getProduct() {
		return product;
	}

	@Override
	public Integer getAmount() {
		return amount;
	}

	@Override
	public LocalDate getLocalDate() {
		return localDate;
	}

	public String getDescription() {
		return description;
	}
}

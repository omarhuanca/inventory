package bo.umss.app.in.referral;

import java.time.LocalDate;

import bo.umss.app.in.StockTransaction;
import bo.umss.app.in.product.Product;

public class StockReferral extends StockTransaction {

	public StockReferral(Product product, Integer amount, LocalDate localDate) {
		this.product = product;
		this.amount = amount;
		this.localDate = localDate;
	}

	public static StockReferral at(Product product, Integer amount, LocalDate localDate) {
		if (null == product)
			throw new RuntimeException(StockTransaction.PRODUCT_CAN_NOT_BE_NULL);
		if (0 >= amount)
			throw new RuntimeException(StockTransaction.AMOUNT_CAN_NOT_BE_LESS_THAN_ZERO);
		if(null == localDate)
			throw new RuntimeException(StockTransaction.DATE_CAN_NOT_BE_NULL);

		return new StockReferral(product, amount, localDate);
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
}

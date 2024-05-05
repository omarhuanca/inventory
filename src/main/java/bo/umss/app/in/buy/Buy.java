package bo.umss.app.in.buy;

import java.time.LocalDate;

import bo.umss.app.in.Transaction;
import bo.umss.app.in.changePrice.ChangePrice;
import bo.umss.app.in.product.Product;

public class Buy implements Transaction {

	public static final String INVALID_DESCRIPTION = "Description can not be empty";
	public static final String INVALID_PRODUCT = "Product can not be null";

	private String description;
	private Product product;

	public Buy(String description, Product product) {
		this.description = description;
		this.product = product;
	}

	public static Buy at(String description, Product product) {
		if (description.isEmpty())
			throw new RuntimeException(Buy.INVALID_DESCRIPTION);
		if(null == product)
			throw new RuntimeException(Buy.INVALID_PRODUCT);

		return new Buy(description, product);
	}

	public String getDescription() {
		return description;
	}

	public Product getProduct() {
		return product;
	}

	public void changePriceBuy(Integer potencialPriceCost) {
		if (potencialPriceCost > product.getPriceCost()) {
			LocalDate currentDate = LocalDate.now();
			ChangePrice changePrice = ChangePrice.at(potencialPriceCost, product.getPriceCost(), currentDate);
			product.getListChangePriceCost().add(changePrice);
			product.setPriceCost(potencialPriceCost);
		}
	}

}

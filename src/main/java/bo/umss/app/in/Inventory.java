package bo.umss.app.in;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private List<Product> listProduct;

	public Inventory() {
		this.listProduct = new ArrayList<>();
	}

	public void addProduct(Product product) {
		if (product.compareGreatherThanZero(0) && product.alreadyCodeProduct()) {
			listProduct.add(product);
		}
	}

	public Boolean compareGreatherZero(Integer size) {
		return listProduct.size() > size;
	}

}

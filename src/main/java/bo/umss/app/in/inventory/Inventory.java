package bo.umss.app.in.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bo.umss.app.in.StockTransaction;
import bo.umss.app.in.buy.StockBuy;
import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.product.Product;

public class Inventory {

	public static final String CODE_PRODUCT_DOES_NOT_ADD = "Code product was not added to any transaction";
	public static final String PRODUCT_CAN_NOT_BE_TWO_TIMES = "Product can not be add two times";
	public static final String CODE_PRODUCT_DOES_NOT_EXIST = "Code Product does not exists";
	public static final String ITEM_CAN_NOT_EXIST = "Item does not exist";

	private List<Product> listProduct;

	public Inventory() {
		this.listProduct = new ArrayList<>();
	}

	public void addProduct(Product product) {
		if (product.listTransactionCompareGreatherThanZero(0) && product.alreadyCodeProduct()) {
			List<Product> filterProduct = listProduct.stream()
					.filter(item -> item.getCodeProduct().compareAnotherCode(product.getCodeProduct()))
					.collect(Collectors.toList());
						
			if (0 < filterProduct.size()) {
				throw new RuntimeException(Inventory.PRODUCT_CAN_NOT_BE_TWO_TIMES);
			}
			listProduct.add(product);
		} else {
			throw new RuntimeException(Inventory.ITEM_CAN_NOT_EXIST);
		}
	}

	public Boolean compareListProductGreatherZero(Integer size) {
		return listProduct.size() >= size;
	}

	public void canAddReferralTransaction(CodeProduct codeProduct) {
		List<Product> listProductOptional = listProduct.stream()
				.filter(item -> item.getCodeProduct().compareAnotherCode(codeProduct))
				.collect(Collectors.toList());
		if (0 < listProductOptional.size()) {
			Optional<Product> productOptional = listProductOptional.stream().findAny();
			if (productOptional.isPresent()) {
				Product product = productOptional.get();
				List<StockTransaction> filterBuy = product.getListTransaction().stream()
						.filter(item -> item instanceof StockBuy
								&& ((StockBuy) item).getCodeProduct().compareAnotherCode(codeProduct))
						.collect(Collectors.toList());
				if (0 == filterBuy.size())
					throw new RuntimeException(Inventory.CODE_PRODUCT_DOES_NOT_ADD);
			}
		}
	}

	public Integer compareEqualListTransaction(Product product) {
		return product.getListTransaction().size();
	}
}

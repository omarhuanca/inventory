package bo.umss.app.in;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Inventory {

	public static final String QUANTITY_GREATHER_THAN_AVAILABLE = "Quantity greather than avaible.";
	public static final String NOT_REGISTER_CODE_PRODUCT = "Code product was not added to any transaction.";
	public static final String INVALID_ADD_PRODUCT_TWO_TIMES = "Product can not be add two times.";
	public static final String INVALID_CODE_PRODUCT_REFERRAL = "Code Product does not exists.";

	private List<Product> listProduct;

	public Inventory() {
		this.listProduct = new ArrayList<>();
	}

	public void addBuyTransaction(Product product) {
		if (product.listTransactionCompareGreatherThanZero(0) && product.alreadyCodeProduct()) {
			Optional<Product> productOptional = listProduct.stream()
					.filter(item -> item.getCodeProduct().compareAnotherCode(product.getCodeProduct())).findAny();
			if (productOptional.isPresent()) {
				throw new RuntimeException(Inventory.INVALID_ADD_PRODUCT_TWO_TIMES);
			}
			listProduct.add(product);
		}
	}

	public Boolean compareListProductGreatherZero(Integer size) {
		return listProduct.size() >= size;
	}

	public void withdrawReferralTransaction(Referral referral) {
		Optional<Product> productOptional = listProduct.stream()
				.filter(item -> item.getCodeProduct().compareAnotherCode(referral.getCodeProduct())).findAny();
		if (!productOptional.isPresent()) {
			throw new RuntimeException(Inventory.INVALID_CODE_PRODUCT_REFERRAL);
		}
		Product product = productOptional.get();
		if (!product.canIncreaseAmount(referral.getAmount())) {
			throw new RuntimeException(Inventory.QUANTITY_GREATHER_THAN_AVAILABLE);
		}
		product.todoIncreaseAmount(referral.getAmount());
		product.getListTransaction().add(referral);

	}

	public void canAddReferralTransaction(CodeProduct codeProduct) {
		Optional<Product> productOptional = listProduct.stream()
				.filter(item -> item.getCodeProduct().compareAnotherCode(codeProduct)).findAny();
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
			List<Transaction> filterBuy = product.getListTransaction().stream()
					.filter(item -> item instanceof Buy
							&& ((Buy) item).getProduct().getCodeProduct().getCode().equals(codeProduct.getCode()))
					.collect(Collectors.toList());
			if (0 == filterBuy.size())
				throw new RuntimeException(Inventory.NOT_REGISTER_CODE_PRODUCT);
		}
	}

	public Integer compareEqualListTransaction(Product product) {
		return product.getListTransaction().size();
	}
		
}

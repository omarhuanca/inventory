package bo.umss.app.in.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bo.umss.app.in.Product;
import bo.umss.app.in.Transaction;
import bo.umss.app.in.buy.Buy;
import bo.umss.app.in.codeProduct.CodeProduct;
import bo.umss.app.in.referral.Referral;

public class Inventory {

	public static final String QUANTITY_GREATHER_THAN_AVAILABLE = "Quantity greather than avaible.";
	public static final String NOT_REGISTER_CODE_PRODUCT = "Code product was not added to any transaction.";
	public static final String INVALID_ADD_PRODUCT_TWO_TIMES = "Product can not be add two times.";
	public static final String INVALID_CODE_PRODUCT_REFERRAL = "Code Product does not exists.";
	public static final String ITEM_CAN_NOT_EXIST = "Item does not exist";

	private List<Product> listProduct;

	public Inventory() {
		this.listProduct = new ArrayList<>();
	}

	public void addBuyTransaction(Product product) {
		if (product.listTransactionCompareGreatherThanZero(0) && product.alreadyCodeProduct()) {
			List<Product> filterProduct = listProduct.stream()
					.filter(item -> item.getCodeProduct().compareAnotherCode(product.getCodeProduct()))
					.collect(Collectors.toList());
						
			if (0 < filterProduct.size()) {
				throw new RuntimeException(Inventory.INVALID_ADD_PRODUCT_TWO_TIMES);
			}
			listProduct.add(product);
		} else {
			throw new RuntimeException(Inventory.ITEM_CAN_NOT_EXIST);
		}
	}

	public Boolean compareListProductGreatherZero(Integer size) {
		return listProduct.size() >= size;
	}

	public void withdrawReferralTransaction(Referral referral) {
		List<Product> listProductOptional = listProduct.stream()
				.filter(item -> item.getCodeProduct().compareAnotherCode(referral.getCodeProduct()))
				.collect(Collectors.toList());
		if (0 < listProductOptional.size()) {
			Optional<Product> productOptional = listProductOptional.stream().findAny();
			if (!productOptional.isPresent()) {
				throw new RuntimeException(Inventory.INVALID_CODE_PRODUCT_REFERRAL);
			}
			Product product = productOptional.get();
			if (!product.canIncreaseAmount(referral.getAmount())) {
				throw new RuntimeException(Inventory.QUANTITY_GREATHER_THAN_AVAILABLE);
			}
			product.todoIncreaseAmount(referral.getAmount());
			product.getListTransaction().add(referral);

		} else {
			throw new RuntimeException(Inventory.NOT_REGISTER_CODE_PRODUCT);
		}

	}

	public void canAddReferralTransaction(CodeProduct codeProduct) {
		List<Product> listProductOptional = listProduct.stream()
				.filter(item -> item.getCodeProduct().compareAnotherCode(codeProduct))
				.collect(Collectors.toList());
		if (0 < listProductOptional.size()) {
			Optional<Product> productOptional = listProductOptional.stream().findAny();
			if (productOptional.isPresent()) {
				Product product = productOptional.get();
				List<Transaction> filterBuy = product.getListTransaction().stream()
						.filter(item -> item instanceof Buy
								&& ((Buy) item).getProduct().getCodeProduct().compareAnotherCode(codeProduct))
						.collect(Collectors.toList());
				if (0 == filterBuy.size())
					throw new RuntimeException(Inventory.NOT_REGISTER_CODE_PRODUCT);
			}
		}
	}

	public Integer compareEqualListTransaction(Product product) {
		return product.getListTransaction().size();
	}
}

package bo.umss.app.in.plate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bo.umss.app.in.Product;
import bo.umss.app.in.Transaction;
import bo.umss.app.in.buy.Buy;
import bo.umss.app.in.changePrice.ChangePrice;
import bo.umss.app.in.codeProduct.CodeProduct;

public class Plate extends Product {

	public Plate(CodeProduct codeProduct, Integer amount, Integer priceCost, Integer priceSale) {
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

		return new Plate(codeProduct, amount, priceCost, priceSale);
	}

	@Override
	public CodeProduct getCodeProduct() {
		return codeProduct;
	}

	@Override
	public Integer getAmount() {
		return amount;
	}

	@Override
	public Integer getPriceCost() {
		return priceCost;
	}

	@Override
	public void setPriceCost(Integer priceCost) {
		this.priceCost = priceCost;
	}

	@Override
	public Integer getPriceSale() {
		return priceSale;
	}

	@Override
	public List<ChangePrice> getListChangePriceCost() {
		return listChangePriceCost;
	}

	@Override
	public List<Transaction> getListTransaction() {
		return listTransaction;
	}

	@Override
	public Boolean canAddAnyTransaction() {
		return codeProduct.existCode();
	}

	@Override
	public Boolean listTransactionCompareGreatherThanZero(Integer count) {
		return listTransaction.size() > count;
	}

	@Override
	public Boolean alreadyCodeProduct() {
		return null != codeProduct;
	}

	@Override
	public Boolean amountGreatherThanZero() {
		return amount > 0;
	}

	@Override
	public void addBuy(Buy buy) {
		if (amountGreatherThanZero()) {
			Optional<Transaction> buyOptional = listTransaction.stream()
					.filter(item -> item instanceof Buy && ((Buy) item).getProduct().getCodeProduct().getCode()
							.equals(buy.getProduct().getCodeProduct().getCode()))
					.findAny();
			if (buyOptional.isPresent())
				throw new RuntimeException(Product.ALREADY_CODE_PRODUCT);

			listTransaction.add(buy);
		}
	}

	@Override
	public Boolean wasAddAnyBuy(CodeProduct codeProduct) {
		List<Transaction> filterBuy = listTransaction.stream()
				.filter(item -> item instanceof Buy
						&& ((Buy) item).getProduct().getCodeProduct().compareAnotherCode(codeProduct))
				.collect(Collectors.toList());

		return filterBuy.size() > 0;
	}

	@Override
	public Boolean canIncreaseAmount(Integer potencialAmount) {
		return amount >= potencialAmount;
	}

	@Override
	public void todoIncreaseAmount(Integer potencialAmount) {
		if (this.canIncreaseAmount(potencialAmount)) {
			amount = amount - potencialAmount;
		}
	}
}

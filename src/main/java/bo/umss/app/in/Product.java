package bo.umss.app.in;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Product {

	private String concept;
	private CodeProduct codeProduct;
	private List<Transaction> listTransaction;

	public Product(String concept, CodeProduct codeProduct) {
		this.concept = concept;
		this.codeProduct = codeProduct;
		listTransaction = new ArrayList<>();
	}

	public Boolean verifyConceptIsNotEmpty() {
		return concept.isBlank();
	}

	public Boolean canAddAnyTransaction() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);
		return codeProduct.existCode();
	}

	public Boolean compareGreatherThanZero(Integer count) {
		return listTransaction.size() > count;
	}

	public Boolean alreadyCodeProduct() {
		return codeProduct != null;
	}

	public void addBuy(Buy buy) {
		if (buy.amountGreatherThanZero()) {
			listTransaction.add(buy);
		}
	}

	public Boolean wasAddAnyBuy(CodeProduct codeProduct) {
		List<Transaction> filterBuy = listTransaction.stream().filter(
				item -> item instanceof Buy && ((Buy) item).getCodeProduct().getCode().equals(codeProduct.getCode()))
				.collect(Collectors.toList());
		return filterBuy.size() > 0;
	}
}

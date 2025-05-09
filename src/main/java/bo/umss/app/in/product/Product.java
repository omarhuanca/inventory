package bo.umss.app.in.product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bo.umss.app.in.buy.StockBuy;
import bo.umss.app.in.changePrice.ChangePrice;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.price.Price;
import bo.umss.app.in.provider.Provider;
import bo.umss.app.in.referral.StockReferral;
import bo.umss.app.in.stock.Stock;

public class Product {

	public static final String CODE_CAN_NOT_BE_BLANK = "Code can not be blank";
	public static final String DESCRIPTION_CAN_NOT_BE_BLANK = "Description can not be blank";
	public static final String STOCK_CAN_NOT_BE_NULL = "Stock can not be null";
	public static final String CODE_PRODUCT_DUPLICATE = "Code product already exists";
	public static final String PRICE_COST_CAN_NOT_BE_NULL = "Price cost can not be null";
	public static final String PRICE_SALE_CAN_NOT_BE_NULL = "Price sale can not be null";
	public static final String LINE_CAN_NOT_BE_NULL = "Line can not be null";
	public static final String PROVIDER_CAN_NOT_BE_NULL = "Provider can not be null";
	public static final String PRICE_COST_COIN_DIFF_PRICE_SALE_COIN = "Coin diff between price cost and price sale";
	public static final String PRICE_SALE_CHEAPER_THAN_PRICE_COST = "Price sale can not be cheaper than price cost";
	
	private String code;
	private String description;
	private Stock stock;
	private Price priceCost;
	private Price priceSale;
	private Line line;
	private Provider provider;
	private List<ChangePrice> listChangePriceCost;
	private List<StockBuy> listStockBuy;
	private List<StockReferral> listStockReferral;

	public Product(String code, String description, Stock stock, Price priceCost, Price priceSale, Line line,
			Provider provider) {
		this.code = code;
		this.description = description;
		this.stock = stock;
		this.priceCost = priceCost;
		this.priceSale = priceSale;
		this.line = line;
		this.provider = provider;
		listChangePriceCost = new ArrayList<ChangePrice>();
		listStockBuy = new ArrayList<StockBuy>();
		listStockReferral = new ArrayList<StockReferral>();
	}

	public static Product at(String code, String description, Stock stock, Price priceCost, Price priceSale, Line line,
			Provider provider) {
		if (code.isEmpty())
			throw new RuntimeException(CODE_CAN_NOT_BE_BLANK);
		if (description.isEmpty())
			throw new RuntimeException(DESCRIPTION_CAN_NOT_BE_BLANK);
		if (null == stock)
			throw new RuntimeException(STOCK_CAN_NOT_BE_NULL);
		if (null == priceCost)
			throw new RuntimeException(PRICE_COST_CAN_NOT_BE_NULL);
		if (null == priceSale)
			throw new RuntimeException(PRICE_SALE_CAN_NOT_BE_NULL);
		if (!priceCost.lessThanValue(priceSale))
			throw new RuntimeException(PRICE_SALE_CHEAPER_THAN_PRICE_COST);
		if (!priceCost.compareOtherCoin(priceSale))
			throw new RuntimeException(PRICE_COST_COIN_DIFF_PRICE_SALE_COIN);
		if (null == line)
			throw new RuntimeException(LINE_CAN_NOT_BE_NULL);
		if (null == provider)
			throw new RuntimeException(PROVIDER_CAN_NOT_BE_NULL);

		return new Product(code, description, stock, priceCost, priceSale, line, provider);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String potentialCode) {
		code = potentialCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String potentialDescription) {
		description = potentialDescription;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock potentialStock) {
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

	public void setPriceSale(Price potentialPriceSale) {
		priceSale = potentialPriceSale;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line potentialLine) {
		line = potentialLine;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider potentialProvider) {
		provider = potentialProvider;
	}

	public List<ChangePrice> getListChangePriceCost() {
		return listChangePriceCost;
	}

	public List<StockBuy> getListStockBuy() {
		return listStockBuy;
	}

	public List<StockReferral> getListReferral() {
		return listStockReferral;
	}

	public void setListReferral(List<StockReferral> listReferral) {
		this.listStockReferral = listReferral;
	}

	public boolean equals(Product potentialProduct) {
		return code.equalsIgnoreCase(potentialProduct.getCode());
	}

	public Boolean listTransactionCompareGreatherThanZero(Integer count) {
		return listStockBuy.size() > count;
	}

	public void addBuy(StockBuy buy) {
		if (stock.amountGreaterThanZero()) {

			if (stock.verifyPotentialValueGreaterZero(buy.getAmount())) {
				stock.todoIncreaseStock(buy.getAmount());
			}
			listStockBuy.add(buy);
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
		return stock.verifyValueGreaterThanPotentialValue(amount);
	}

	public void todoDecrementStock(Integer amount) {
		stock.todoDecrementStock(amount);
	}

	public void changeMesurementStock(Stock potentialStock) {
		if (stock.compareOtherValue(potentialStock.getValue())) {
			setStock(potentialStock);
		}
	}

	public void addReferral(StockReferral referral) {
		todoDecrementStock(referral.getAmount());
		listStockReferral.add(referral);
	}

	public Price calculateSubtotalWithCoin() {
		return Price.at(priceSale.getValue() * stock.getValue(), priceSale.getCoin());
	}

	public Price generateSubtotal() {
		return Price.at(priceSale.getValue() * stock.getValue(), priceSale.getCoin());
	}

	public Boolean compareOtherCode(String potentialCode) {
		return code.equalsIgnoreCase(potentialCode);
	}

	public Boolean compareOtherDescription(String potentialDescription) {
		return description.equalsIgnoreCase(potentialDescription);
	}

	public Boolean compareStock(Stock potentialStock) {
		return stock.equals(potentialStock);
	}

	public Boolean comparePriceSale(Price potentialPriceSale) {
		return priceSale.equals(potentialPriceSale);
	}

	public Boolean comparePriceCost(Price potentialPriceCost) {
		return priceCost.equals(potentialPriceCost);
	}

	public Boolean compareLine(Line potentialLine) {
		return line.equals(potentialLine);
	}

	public Boolean compareProvider(Provider potentialProvider) {
		return provider.equals(potentialProvider);
	}
}

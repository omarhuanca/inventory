package bo.umss.app.in;

import java.time.LocalDate;

import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.price.Price;
import bo.umss.app.in.product.Product;
import bo.umss.app.in.provider.Provider;
import bo.umss.app.in.stock.Stock;

public class TestObjectBucket {

	public static final String JUAN_PEREZ_NAME = "Juan Perez";
	public static final String JUAN_PEREZ_NIT = "123456";
	public static final String JUAN_PEREZ_CELLPHONE = "88888888";
	public static final String PLATE_PURCHEASE_DESCRIPTION = "purchase porcelain plates";
	public static final String CUP_PURCHASE_DESCRIPTION = "purchase porcelain cupes";
	public static final String BOWL7_DESCRIPTION = "bowl7 a round plate of porcelain";
	public static final String PLATE_CODE = "PLA-1";
	public static final String PLATE_NAME = "bowl8 porcelain";
	public static final String CUP_CODE = "CUP-1";
	public static final String CUP_NAME = "cup porcelain isaylin";
	public static final String BOWL8_CODE = "PLA-2";
	public static final String BOWL8_DESCRIPTION = "bowl8 porcelain plate";
	public static final String CODE_PZA = "pza";
	public static final String CODE_DOC = "doc";
	public static final String CODE_USA = "USD";
	public static final String CODE_BS = "BS";
	public static final String POT_CODE = "POT-1";
	public static final String POT_NAME = "Set of pot";
	public static final String INVOICE_NUMBER = "654987";

	public Product createPlate() {
		Line line = Line.at(PLATE_NAME);
		Coin coin = Coin.at(CODE_BS);
		Price priceCost = Price.at(5.0, coin);
		Price priceSale = Price.at(10.0, coin);
		Measurement measurement = createMeasurement();
		Stock stock = Stock.at(10, measurement);
		Provider provider = Provider.at(JUAN_PEREZ_NAME, JUAN_PEREZ_CELLPHONE);

		return Product.at(PLATE_CODE, "PLATO ZETA BOWL 8 PORCELANA CUADRADO", stock, priceCost, priceSale, line, provider);
	}

	public Measurement createMeasurement() {
		return Measurement.at(CODE_PZA);
	}

	public Product createCup() {
		Line line = Line.at(CUP_NAME);
		Coin coin = Coin.at(CODE_BS);
		Price priceCost = Price.at(8.0, coin);
		Price priceSale = Price.at(16.0, coin);
		Measurement measurement = createMeasurement();
		Stock stock = Stock.at(10, measurement);
		Provider provider = Provider.at(JUAN_PEREZ_NAME, JUAN_PEREZ_CELLPHONE);

		return Product.at(CUP_CODE, CUP_PURCHASE_DESCRIPTION, stock, priceCost, priceSale, line, provider);
	}

	public Provider createDefaultProvider() {
		return Provider.at(JUAN_PEREZ_NAME, JUAN_PEREZ_CELLPHONE);
	}

	public LocalDate createDate() {
		return LocalDate.of(2024, 05, 15);
	}

	public Product createPot() {
		Line line = Line.at(POT_NAME);
		Coin coin = Coin.at(CODE_USA);
		Price priceCost = Price.at(205.0, coin);
		Price priceSale = Price.at(246.0, coin);
		Measurement measurement = createMeasurement();
		Stock stock = Stock.at(80, measurement);
		Provider provider = Provider.at(JUAN_PEREZ_NAME, JUAN_PEREZ_CELLPHONE);

		return Product.at(POT_CODE, "OLLA TRILLIUM INOX 3 PCS", stock, priceCost, priceSale, line, provider);
	}
}

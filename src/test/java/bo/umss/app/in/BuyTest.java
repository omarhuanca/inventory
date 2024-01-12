package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BuyTest {

	@Test
	public void test1() {
		assertThrows(RuntimeException.class, () -> Buy.at(null, "db1", 1, 5, 6), Buy.INVALID_CODE_PRODUCT);
	}

	@Test
	public void test2() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);
		assertThrows(RuntimeException.class, () -> Buy.at(codeProduct, "", 1, 5, 6), Buy.INVALID_DESCRIPTION);
	}

	@Test
	public void test3() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);
		assertThrows(RuntimeException.class, () -> Buy.at(codeProduct, "d1", 0, 5, 6), Buy.INVALID_AMOUNT);
	}

	@Test
	public void test4() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);
		assertThrows(RuntimeException.class, () -> Buy.at(codeProduct, "d1", -5, 5, 6), Buy.INVALID_AMOUNT);
	}

	@Test
	public void test5() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);
		assertThrows(RuntimeException.class, () -> Buy.at(codeProduct, "d1", 1, 0, 6), Buy.INVALID_PRICE_COST);
	}

	@Test
	public void test6() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);
		assertThrows(RuntimeException.class, () -> Buy.at(codeProduct, "d1", 1, 5, 0), Buy.INVALID_PRICE_SALE);
	}

	/*
	@Test
	public void test7() {
		Measurement measurement = Measurement.at("cm1", "nm1");
		Line line = Line.at("cl1", "nl1");
		Coin coin = Coin.at("co1", "i1");
		CodeProduct codeProduct = CodeProduct.at("c2", "description2", measurement, line, coin);
		Buy buyOne = Buy.at(codeProduct, "d1", 1, 5, 0);
		
	}
	*/
}

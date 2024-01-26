package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MeasurementTest {

	@Test
	public void test1() {
		assertThrows(RuntimeException.class, () -> Measurement.at(Measurement.CODE_PZA, ""), Measurement.INVALID_NAME);
	}

	@Test
	public void test2() {
		assertThrows(RuntimeException.class, () -> Measurement.at("", Measurement.NAME_PZA), Measurement.INVALID_CODE);
	}
}

package bo.umss.app.in.measurement;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bo.umss.app.in.TestObjectBucket;
import bo.umss.app.in.measurement.Measurement;

public class MeasurementTest {

	@Test
	public void canNotBeEmptyCode() {
		assertThrows(RuntimeException.class, () -> Measurement.at("", TestObjectBucket.NAME_PZA),
				Measurement.CODE_CAN_NOT_BE_BLANK);
	}

	@Test
	public void canNotBeEmptyName() {
		assertThrows(RuntimeException.class, () -> Measurement.at(TestObjectBucket.CODE_PZA, ""),
				Measurement.NAME_CAN_NOT_BE_BLANK);
	}
}

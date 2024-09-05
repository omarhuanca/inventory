package bo.umss.app.in.measurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.TestObjectBucket;
import bo.umss.app.in.measurement.Measurement;

public class MeasurementTest {

	private TestObjectBucket testObjectBucket;

	@BeforeEach
	public void setUp() {
		testObjectBucket = new TestObjectBucket();
	}

	@Test
	public void canNotBeEmptyName() {
		assertThrows(RuntimeException.class, () -> Measurement.at(""), Measurement.CODE_CAN_NOT_BE_BLANK);
	}

	@Test
	public void verifyWrongCompare() {
		Measurement potentialMeasurement = testObjectBucket.createMeasurement();
		Measurement potentialAnotherMeasurement = Measurement.at(TestObjectBucket.CODE_DOC);

		assertFalse(potentialMeasurement.equals(potentialAnotherMeasurement));
	}

	@Test
	public void verifyCorrectCompare() {
		Measurement potentialMeasurement = testObjectBucket.createMeasurement();
		Measurement potentialAnotherMeasurement = Measurement.at(TestObjectBucket.CODE_PZA);

		assertTrue(potentialMeasurement.equals(potentialAnotherMeasurement));
	}
}

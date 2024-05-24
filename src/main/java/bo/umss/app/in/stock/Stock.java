package bo.umss.app.in.stock;

import bo.umss.app.in.measurement.Measurement;

public class Stock {

	public static final String VALUE_CAN_NOT_BE_LESS_THAN_ZERO = "Value can not be less zero";
	public static final String MEASUREMENT_CAN_NOT_BE_NULL = "Measurement can not be null";
	public static final String AMOUNT_GREATHER_THAN_AVAILABLE = "Amount greather than avaible";

	private Integer value;
	private Measurement measurement;

	public Stock(Integer value, Measurement measurement) {
		this.value = value;
		this.measurement = measurement;
	}

	public static Stock at(Integer value, Measurement measurement) {
		if (0 >= value)
			throw new RuntimeException(VALUE_CAN_NOT_BE_LESS_THAN_ZERO);
		if (null == measurement)
			throw new RuntimeException(MEASUREMENT_CAN_NOT_BE_NULL);

		return new Stock(value, measurement);
	}

	public Integer getValue() {
		return value;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public Boolean amountGreatherThanZero() {
		return value > 0;
	}

	public Boolean verifyValueGreatherThanPotentialValue(Integer potencialValue) {
		return value >= potencialValue;
	}

	public void todoDecrementStock(Integer potencialStock) {
		if (verifyValueGreatherThanPotentialValue(potencialStock)) {
			value = value - potencialStock;
		} else {
			throw new RuntimeException(AMOUNT_GREATHER_THAN_AVAILABLE);
		}
	}

	public Boolean compareValue(Integer potentialValue) {
		return value.equals(potentialValue);
	}

	public Boolean verifyPotentialValueGreatherZero(Integer potentialValue) {
		return potentialValue > 0;
	}

	public void todoIncreaseStock(Integer potentialValue) {
		if (value > 0) {
			value = value + potentialValue;
		}
	}
}

package bo.umss.app.in.price;

import java.util.HashMap;
import java.util.Map;

import bo.umss.app.in.coin.Coin;

public class Price {

	public static final String VALUE_CAN_NOT_BE_LESS_ZERO = "Value can not be less than zero";
	public static final String COIN_CAN_NOT_BE_NULL = "Coin can not be null";

	private Integer value;
	private Coin coin;

	public Price(Integer value, Coin coin) {
		this.value = value;
		this.coin = coin;
	}

	public static Price at(Double value, Coin coin) {
		if (value <= 0)
			throw new RuntimeException(VALUE_CAN_NOT_BE_LESS_ZERO);
		if (null == coin)
			throw new RuntimeException(COIN_CAN_NOT_BE_NULL);

		return new Price((int) Math.round(value), coin);
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Double potentialValue) {
		value = (int) Math.round(potentialValue);
	}

	public Coin getCoin() {
		return coin;
	}

	public Boolean lessThanValue(Price potentialPriceCost) {
		return value < potentialPriceCost.getValue();
	}

	public Boolean compareValueLessThanPotentialValue(Integer potentialValue) {
		return value < potentialValue;
	}

	public Map<Coin, Price> addPriceSumarize(Price potentialPrice) {
		Map<Coin, Price> result = new HashMap<Coin, Price>();
		if (coin.compareCode(potentialPrice.getCoin())) {
			result.put(coin, Price.at(new Double(value) + potentialPrice.getValue(), coin));
		} else {
			result.put(potentialPrice.getCoin(), potentialPrice);
		}

		return result;
	}

	public Boolean compareOtherValue(Double potentialValue) {
		return value.equals(potentialValue);
	}

	public Double addWithOtherPrice(Price potentialPrice) {
		return new Double(value) + potentialPrice.getValue();
	}

	public Boolean isNegativeValue() {
		return 0 > value;
	}
}

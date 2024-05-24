package bo.umss.app.in.price;

import bo.umss.app.in.coin.Coin;

public class Price {

	public static final String VALUE_CAN_NOT_BE_LESS_ZERO = "Value can not be zero.";
	public static final String COIN_CAN_NOT_BE_NULL = "Coin can not be null.";

	private Integer value;
	private Coin coin;

	public Price(Integer value, Coin coin) {
		this.value = value;
		this.coin = coin;
	}

	public static Price at(Integer value, Coin coin) {
		if (0 >= value)
			throw new RuntimeException(VALUE_CAN_NOT_BE_LESS_ZERO);
		if (null == coin)
			throw new RuntimeException(COIN_CAN_NOT_BE_NULL);

		return new Price(value, coin);
	}

	public Integer getValue() {
		return value;
	}

	public Coin getCoin() {
		return coin;
	}

	public Boolean lessThanValue(Price potentialPriceCost) {
		return value < potentialPriceCost.getValue();
	}
}

package bo.umss.app.in.discount;

import bo.umss.app.in.coin.Coin;

public class Discount {

	public static final String VALUE_CAN_NOT_LESS_THAN_ZERO = "Value can not be less than zero";
	public static final String COIN_CAN_NOT_BE_NULL = "Coin can not be null";

	private Integer value;
	private Coin coin;

	public Discount(Integer value, Coin coin) {
		this.value = value;
		this.coin = coin;
	}

	public static Discount at(Integer value, Coin coin) {
		if (-1 > value)
			throw new RuntimeException(VALUE_CAN_NOT_LESS_THAN_ZERO);
		if (null == coin)
			throw new RuntimeException(COIN_CAN_NOT_BE_NULL);

		return new Discount(value, coin);
	}

	public Integer getValue() {
		return value;
	}

	public Coin getCoin() {
		return coin;
	}

}

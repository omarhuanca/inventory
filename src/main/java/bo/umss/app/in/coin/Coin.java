package bo.umss.app.in.coin;

public class Coin {

	public static final String CODE_CAN_NOT_BE_EMPTY = "Code can not be blank";
	public static final String INITIAL_CAN_NOT_BE_BLANK = "Initial can not be blank";
	public static final String CODE_USA = "coin1";
	public static final String NAME_USA = "USD";
	public static final String CODE_BS = "coin2";
	public static final String NAME_BS = "BS";

	private String code;
	private String initial;

	private Coin(String code, String initial) {
		this.code = code;
		this.initial = initial;
	}

	public static Coin at(String code, String initial) {
		if (code.isEmpty())
			throw new RuntimeException(CODE_CAN_NOT_BE_EMPTY);
		if (initial.isEmpty())
			throw new RuntimeException(INITIAL_CAN_NOT_BE_BLANK);

		return new Coin(code, initial);
	}

	public String getCode() {
		return code;
	}

	public String getInitial() {
		return initial;
	}

	public Boolean compareCode(Coin potentialCoin) {
		return code.equals(potentialCoin.getCode());
	}
}

package bo.umss.app.in.coin;

public class Coin {

	public static final String INVALID_CODE = "Code can not be blank";
	public static final String INVALID_INITIAL = "Initial can not be blank";
	public static final String CODE_USA = "coin1";
	public static final String NAME_USA = "USD";

	private String code;
	private String initial;

	private Coin(String code, String initial) {
		this.code = code;
		this.initial = initial;
	}

	public static Coin at(String code, String initial) {
		if (code.isEmpty())
			throw new RuntimeException(INVALID_CODE);
		if (initial.isEmpty())
			throw new RuntimeException(INVALID_INITIAL);

		return new Coin(code, initial);
	}

	public String getCode() {
		return code;
	}

	public String getInitial() {
		return initial;
	}
}

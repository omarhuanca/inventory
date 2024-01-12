package bo.umss.app.in;

public class CodeProduct {

	public static final String INVALID_CODE = "Code can not be blank";
	public static final String INVALID_DESCRIPTION = "Description can not be blank";
	public static final String INVALID_MEASUREMENT = "Measurement can not be null";
	public static final String INVALID_LINE = "Line can not be null";
	public static final String INVALID_COIN = "Coin can not be null";

	private String code;
	private String description;
	private Measurement measurement;
	private Line line;
	private Coin coin;

	private CodeProduct(String code, String description, Measurement measurement, Line line, Coin coin) {
		this.code = code;
		this.description = description;
		this.measurement = measurement;
		this.line = line;
		this.coin = coin;
	}

	public static CodeProduct at(String code, String description, Measurement measurement, Line line, Coin coin) {
		if (code.isEmpty())
			throw new RuntimeException(INVALID_CODE);
		if (description.isEmpty())
			throw new RuntimeException(INVALID_DESCRIPTION);
		if(null == measurement)
			throw new RuntimeException(INVALID_MEASUREMENT);
		if(null == line)
			throw new RuntimeException(INVALID_LINE);
		if(null == coin)
			throw new RuntimeException(INVALID_COIN);

		return new CodeProduct(code, description, measurement, line, coin);
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public Line getLine() {
		return line;
	}

	public Coin getCoin() {
		return coin;
	}

	public Boolean existCode() {
		return true;
	}

}

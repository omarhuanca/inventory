package bo.umss.app.in.codeProduct;

import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;

public class CodeProduct {

	public static final String INVALID_CODE = "Code can not be blank";
	public static final String INVALID_DESCRIPTION = "Description can not be blank";
	public static final String INVALID_MEASUREMENT = "Measurement can not be null";
	public static final String INVALID_LINE = "Line can not be null";
	public static final String INVALID_COIN = "Coin can not be null";
	public static final String DEFINE_CODE_PRODUCT = "PLA-1";
	public static final String DEFINE_DESCRIPTION = "bowl8 porcelain plate";

	private String code;
	private String description;
	private Measurement measurement;
	private Line line;
	private Coin coin;

	public CodeProduct(String code, String description, Measurement measurement, Line line, Coin coin) {
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
		return code.isEmpty();
	}

	public Boolean compareAnotherCode(CodeProduct otherCodeProduct) {
		return code.equals(otherCodeProduct.getCode());
	}

}

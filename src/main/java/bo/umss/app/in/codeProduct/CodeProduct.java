package bo.umss.app.in.codeProduct;

import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.provider.Provider;

public abstract class CodeProduct {

	public static final String INVALID_CODE = "Code can not be blank";
	public static final String INVALID_DESCRIPTION = "Description can not be blank";
	public static final String INVALID_MEASUREMENT = "Measurement can not be null";
	public static final String INVALID_LINE = "Line can not be null";
	public static final String INVALID_COIN = "Coin can not be null";
	public static final String DEFINE_CODE_PRODUCT = "PLA-1";
	public static final String DEFINE_DESCRIPTION = "bowl8 porcelain plate";

	protected String code;
	protected String description;
	protected Measurement measurement;
	protected Line line;
	protected Coin coin;

	public abstract String getCode();

	public abstract String getDescription();

	public abstract Measurement getMeasurement();

	public abstract Line getLine();

	public abstract Coin getCoin();

	public abstract Boolean existCode();

	public abstract Boolean compareAnotherCode(CodeProduct otherCodeProduct);

	public abstract Provider getProvider();

}

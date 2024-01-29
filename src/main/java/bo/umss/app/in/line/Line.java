package bo.umss.app.in.line;

public class Line {

	public static final String INVALID_CODE = "Code can not be blank";
	public static final String INVALID_NAME = "Name can not be blank";
	public static final String CODE_PLATE = "pla1";
	public static final String NAME_PLATE = "bowl8 porcelain";
	public static final String CODE_CUP = "cup-1";
	public static final String NAME_CUP = "cup porcelain isaylin";

	private String code;
	private String name;

	public Line(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Line at(String code, String name) {
		if (code.isEmpty())
			throw new RuntimeException(INVALID_CODE);
		if (name.isEmpty())
			throw new RuntimeException(INVALID_NAME);

		return new Line(code, name);
	}

	public static String getInvalidName() {
		return INVALID_NAME;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}

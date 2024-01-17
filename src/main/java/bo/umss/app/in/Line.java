package bo.umss.app.in;

public class Line {

	public static final String INVALID_CODE = "Code can not be blank";
	public static final String INVALID_NAME = "Name can not be blank";

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

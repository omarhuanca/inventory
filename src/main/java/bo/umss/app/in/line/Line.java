package bo.umss.app.in.line;

public class Line {

	public static final String CODE_CAN_NOT_BE_BLANK = "Code can not be blank";
	public static final String NAME_CAN_NOT_BE_BLANK = "Name can not be blank";
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
			throw new RuntimeException(CODE_CAN_NOT_BE_BLANK);
		if (name.isEmpty())
			throw new RuntimeException(NAME_CAN_NOT_BE_BLANK);

		return new Line(code, name);
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}

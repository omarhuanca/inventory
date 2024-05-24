package bo.umss.app.in.measurement;

public class Measurement {

	public static final String CODE_CAN_NOT_BE_BLANK = "Code can not be blank";
	public static final String NAME_CAN_NOT_BE_BLANK = "Name can not be blank";
	public static final String CODE_PZA = "pza";
	public static final String NAME_PZA = "piece";
	public static final String CODE_DOC = "doc";
	public static final String NAME_DOC = "dozen";
	

	private String code;
	private String name;

	public Measurement(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Measurement at(String code, String name) {
		if (code.isEmpty())
			throw new RuntimeException(CODE_CAN_NOT_BE_BLANK);
		if (name.isEmpty())
			throw new RuntimeException(NAME_CAN_NOT_BE_BLANK);

		return new Measurement(code, name);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

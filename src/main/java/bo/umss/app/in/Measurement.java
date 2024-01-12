package bo.umss.app.in;

public class Measurement {

	public static final String INVALID_CODE = "Code can not be blank";
	public static final String INVALID_NAME = "Name can not be blank";

	private String code;
	private String name;

	private Measurement(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Measurement at(String code, String name) {
		if (code.isEmpty())
			throw new RuntimeException(INVALID_CODE);
		if (name.isEmpty())
			throw new RuntimeException(INVALID_NAME);

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

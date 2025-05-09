package bo.umss.app.in.line;

public class Line {

	public static final String NAME_CAN_NOT_BE_BLANK = "Name can not be blank";

	private String name;

	public Line(String name) {
		this.name = name;
	}

	public static Line at(String name) {
		if (name.isEmpty())
			throw new RuntimeException(NAME_CAN_NOT_BE_BLANK);

		return new Line(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String potentialName) {
		name = potentialName;
	}

	public Boolean compareOtherName(String potentialName) {
		return name.equalsIgnoreCase(potentialName);
	}
}

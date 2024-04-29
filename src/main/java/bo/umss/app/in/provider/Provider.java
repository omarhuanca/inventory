package bo.umss.app.in.provider;

public class Provider {

	public static final String INVALID_NAME = "Name can not be blank.";
	public static final String INVALID_PHONE_NUMBER = "Phone number can not be blank.";

	private String name;
	private String phoneNumber;

	public Provider(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public static Provider at(String name, String phoneNumber) {
		if (name.isEmpty())
			throw new RuntimeException(INVALID_NAME);
		if (phoneNumber.isEmpty())
			throw new RuntimeException(INVALID_PHONE_NUMBER);
		return new Provider(name, phoneNumber);
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

}

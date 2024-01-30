package bo.umss.app.in.client;

public class GymClient {

	public static final String INVALID_FIRST_NAME = "Firstname can not be empty.";
	public static final String INVALID_LAST_NAME = "Lastname can not be empty.";

	private String firstname;
	private String lastname;

	public GymClient(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public static GymClient at(String firstname, String lastname) {
		if (firstname.isEmpty())
			throw new RuntimeException(GymClient.INVALID_FIRST_NAME);
		if (lastname.isEmpty())
			throw new RuntimeException(GymClient.INVALID_LAST_NAME);

		return new GymClient(firstname, lastname);
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}
}

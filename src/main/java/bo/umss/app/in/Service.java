package bo.umss.app.in;

public class Service {

	public static final String INVALID_GYM_PRICE = "Gym price is invalid.";
	private static final String INVALID_GYM_DATE = "Gym date is null.";

	private Integer priceService;
	private GymDate gymDate;

	public Service(Integer priceService, GymDate gymDate) {
		this.priceService = priceService;
		this.gymDate = gymDate;
	}

	public static Service at(Integer priceService, GymDate gymDate) {
		if (0 >= priceService)
			throw new RuntimeException(Service.INVALID_GYM_PRICE);
		if (null == gymDate)
			throw new RuntimeException(Service.INVALID_GYM_DATE);

		return new Service(priceService, gymDate);
	}

	public Integer getPriceService() {
		return priceService;
	}

	public GymDate getGymDate() {
		return gymDate;
	}
}

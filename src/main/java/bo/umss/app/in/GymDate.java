package bo.umss.app.in;

import java.time.LocalDate;
import java.time.Period;

public class GymDate {

	public static final String INVALID_START_DATE = "Start date can not be null.";
	public static final String INVALID_END_DATE = "End date can not be null.";
	public static final String INVALID_START_DATE_GREATHER_THAN_END_DATE = "Stat date can not be greater than end date.";
	public static final String INVALID_END_DATE_LESS_THAN_START_DATE = "End date can not be less than the start date.";

	private LocalDate startDate;
	private LocalDate endDate;

	public GymDate(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public static GymDate at(LocalDate startDate, LocalDate endDate) {
		if (null == startDate)
			throw new RuntimeException(GymDate.INVALID_START_DATE);
		if (null == endDate)
			throw new RuntimeException(GymDate.INVALID_END_DATE);
		return new GymDate(startDate, endDate);
	}

	public LocalDate getStarDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void validateCorrectMonth() {
		Period period = Period.between(startDate, endDate);
		if (0 >= period.getMonths())
			throw new RuntimeException();

	}
}

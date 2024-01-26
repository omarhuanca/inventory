package bo.umss.app.in;

import java.time.LocalDate;

public class ChangePrice {

	public static final String INVALID_CURRENT_PRICE = "Current price can not be zero";
	public static final String INVALID_OLD_PRICE = "Old price can not be zero";
	public static final String INVALID_OLD_GRATHER_CURRENT_PRICE = "Old price can not be greather than current price";

	private Integer currentPrice;
	private Integer oldPrice;
	private LocalDate currentDate;

	public ChangePrice(Integer currentPrice, Integer oldPrice, LocalDate currentDate) {
		this.currentPrice = currentPrice;
		this.oldPrice = oldPrice;
		this.currentDate = currentDate;
	}

	public static ChangePrice at(Integer currentPrice, Integer oldPrice, LocalDate currentDate) {
		if (0 == currentPrice)
			throw new RuntimeException(INVALID_CURRENT_PRICE);
		if (0 == oldPrice)
			throw new RuntimeException(INVALID_OLD_PRICE);
		if (currentPrice < oldPrice)
			throw new RuntimeException(INVALID_OLD_GRATHER_CURRENT_PRICE);

		return new ChangePrice(currentPrice, oldPrice, currentDate);
	}

	public Integer getCurrentPrice() {
		return currentPrice;
	}

	public Integer getOldPrice() {
		return oldPrice;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

}

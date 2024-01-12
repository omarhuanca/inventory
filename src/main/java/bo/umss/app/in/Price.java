package bo.umss.app.in;

public class Price {

	private Double currentValue;
	private Double oldValud;

	public Price(Double currentValue, Double oldValud) {
		this.currentValue = currentValue;
		this.oldValud = oldValud;
	}

	public Double getCurrentValue() {
		return currentValue;
	}

	public Double getOldValud() {
		return oldValud;
	}
	
}

package bo.umss.app.in.codeProduct;

import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.provider.Provider;

public class NotProvidedProvider extends CodeProduct {

	public String DEFAULT_PROVIDER_NAME = "Pepe";
	public String DEFAULT_PROVIDER_PHONE_NUMBER = "71476576";

	public NotProvidedProvider(String code, String description, Measurement measurement, Line line, Coin coin) {
		this.code = code;
		this.description = description;
		this.measurement = measurement;
		this.line = line;
		this.coin = coin;
	}

	public static NotProvidedProvider at(String code, String description, Measurement measurement, Line line,
			Coin coin) {
		if (code.isEmpty())
			throw new RuntimeException(INVALID_CODE);
		if (description.isEmpty())
			throw new RuntimeException(INVALID_DESCRIPTION);
		if (null == measurement)
			throw new RuntimeException(INVALID_MEASUREMENT);
		if (null == line)
			throw new RuntimeException(INVALID_LINE);
		if (null == coin)
			throw new RuntimeException(INVALID_COIN);

		return new NotProvidedProvider(code, description, measurement, line, coin);
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Measurement getMeasurement() {
		return measurement;
	}

	@Override
	public Line getLine() {
		return line;
	}

	@Override
	public Coin getCoin() {
		return coin;
	}

	@Override
	public Boolean existCode() {
		return code.isEmpty();
	}

	@Override
	public Boolean compareAnotherCode(CodeProduct otherCodeProduct) {
		return code.equals(otherCodeProduct.getCode());
	}

	@Override
	public Provider getProvider() {
		return new Provider(DEFAULT_PROVIDER_NAME, DEFAULT_PROVIDER_PHONE_NUMBER);
	}

}

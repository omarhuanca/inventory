package bo.umss.app.in.codeProduct;

import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.provider.Provider;

public class ProvidedProvider extends CodeProduct {

	public static final String PROVIDER_CAN_NOT_BE_NULL = "Provider can not be null.";
	private Provider provider;

	public ProvidedProvider(String code, String description, Measurement measurement, Line line, Coin coin,
			Provider provider) {
		this.code = code;
		this.description = description;
		this.measurement = measurement;
		this.line = line;
		this.coin = coin;
		this.provider = provider;
	}

	public static ProvidedProvider at(String code, String description, Measurement measurement, Line line, Coin coin,
			Provider provider) {
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
		if (null == provider)
			throw new RuntimeException(PROVIDER_CAN_NOT_BE_NULL);

		return new ProvidedProvider(code, description, measurement, line, coin, provider);

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

	public Provider getProvider() {
		return provider;
	}

}

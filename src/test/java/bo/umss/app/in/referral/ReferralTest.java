package bo.umss.app.in.referral;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bo.umss.app.in.codeProduct.NotProvidedProvider;
import bo.umss.app.in.coin.Coin;
import bo.umss.app.in.line.Line;
import bo.umss.app.in.measurement.Measurement;
import bo.umss.app.in.referral.Referral;

public class ReferralTest {

	@Test
	public void verifyCodeProductCanNotBeNull() {
		assertThrows(RuntimeException.class, () -> Referral.at(null, 1), Referral.INVALID_CODE_PRODUCT);
	}

	@Test
	public void verifyAmountCanNotSmallerThanZero() {
		Measurement measurement = Measurement.at(Measurement.CODE_PZA, Measurement.NAME_PZA);
		Line line = Line.at(Line.CODE_PLATE, Line.NAME_PLATE);
		Coin coin = Coin.at(Coin.CODE_USA, Coin.NAME_USA);
		NotProvidedProvider notProvidedProvider = NotProvidedProvider.at("PLA-1", "bowl8 a round plate of porcelain",
				measurement, line, coin);

		assertThrows(RuntimeException.class, () -> Referral.at(notProvidedProvider, -1), Referral.INVALID_AMOUNT);
	}

}

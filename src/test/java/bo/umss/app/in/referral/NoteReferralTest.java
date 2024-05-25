package bo.umss.app.in.referral;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bo.umss.app.in.NoteTransaction;

public class NoteReferralTest {

	public LocalDate date;

	@BeforeEach
	public void setUp() {
		date = LocalDate.of(2024, 05, 15);
	}

	@Test
	public void canNotBeNullDate() {
		assertThrows(RuntimeException.class, () -> NoteReferral.at(null), NoteTransaction.DATE_CAN_NOT_BE_NULL);
	}

	@Test
	public void verifySizeEmptyAfterCreateNoteReferral() {
		NoteReferral noteReferral = NoteReferral.at(date);
		assertTrue(noteReferral.compareIsEqualSize(0));
	}
}

package bo.umss.app.in.referral;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bo.umss.app.in.NoteTransaction;
import bo.umss.app.in.product.Product;

public class NoteReferral extends NoteTransaction {

	public NoteReferral(LocalDate date) {
		this.date = date;
		listProductOutput = new ArrayList<>();
	}

	public static NoteReferral at(LocalDate date) {
		if (null == date)
			throw new RuntimeException(NoteTransaction.DATE_CAN_NOT_BE_NULL);

		return new NoteReferral(date);
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public List<Product> getListProductOutPut() {
		return listProductOutput;
	}

	public Boolean compareIsEqualSize(Integer potentialSize) {
		return listProductOutput.size() == potentialSize;
	}
}

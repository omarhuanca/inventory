package bo.umss.app.in;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class LineTest {

	@Test
	public void test1() {
		assertThrows(RuntimeException.class, ()->Line.at(Line.CODE_PLATE, ""), Line.INVALID_NAME);
	}
	
	@Test
	public void test2() {
		assertThrows(RuntimeException.class, ()->Line.at("", Line.NAME_PLATE), Line.INVALID_CODE);
	}
}

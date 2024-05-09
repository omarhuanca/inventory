package bo.umss.app.in.line;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bo.umss.app.in.line.Line;

public class LineTest {

	@Test
	public void test1() {
		assertThrows(RuntimeException.class, ()->Line.at(Line.CODE_PLATE, ""), Line.NAME_CAN_NOT_BE_BLANK);
	}
	
	@Test
	public void test2() {
		assertThrows(RuntimeException.class, ()->Line.at("", Line.NAME_PLATE), Line.CODE_CAN_NOT_BE_BLANK);
	}
}

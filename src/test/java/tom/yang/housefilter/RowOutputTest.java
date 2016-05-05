package tom.yang.housefilter;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.core.RowOutput;

public class RowOutputTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testNormal() throws IOException {
		RowOutput o = new RowOutput();

		List<HouseRow> rows = new ArrayList<HouseRow>();

		HouseRow row1 = new HouseRow();
		row1.getCells().add(new HouseCell("a", 1));
		row1.getCells().add(new HouseCell("b", 2));
		row1.getCells().add(new HouseCell("c", 3));
		row1.getCells().add(new HouseCell("d", 4));
		HouseRow row2 = new HouseRow();
		row2.getCells().add(new HouseCell("a", 1));
		row2.getCells().add(new HouseCell("b", 2));
		row2.getCells().add(new HouseCell("c", 3));
		row2.getCells().add(new HouseCell("d", 4));
		rows.add(row1);
		rows.add(row2);

		String s = o.printHouseRows(rows);

		Assert.assertEquals("a,b,c,d\r\na,b,c,d\r\n", s);

	}

}
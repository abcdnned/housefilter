package tom.yang.housefilter;



import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.core.RowSorter;

public class RowSorterTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testNormal() {
		RowSorter sorter=new RowSorter();

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
		row1.setRowWeight(1);
		row2.setRowWeight(3);
		HouseRow row3 = new HouseRow();
		row3.setRowWeight(4);
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);

		sorter.sortRows(rows);

		Assert.assertEquals(rows.get(0), row3);
		Assert.assertEquals(rows.get(1), row2);
		Assert.assertEquals(rows.get(2), row1);

	}

}

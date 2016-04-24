package tom.yang.housefilter;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.rowfilter.FirstRowIndexFilter;


public class FirstRowIndexFilterTest {

	private static HouseRow emptyRow;
	private static HouseRow row;
	private static HouseRow wrongRow;

	@BeforeClass
	public static void setup(){
		row=new HouseRow();
		row.getCells().add(new HouseCell("1", 1));
		row.getCells().add(new HouseCell("v1", 2));

		wrongRow=new HouseRow();
		wrongRow.getCells().add(new HouseCell("k1", 1));
		wrongRow.getCells().add(new HouseCell("v1", 2));

		emptyRow=new HouseRow();
	}

	@Test
	public void emptyTest(){
		final FirstRowIndexFilter filter=new FirstRowIndexFilter();
		Assert.assertFalse(filter.filterHouseRow(emptyRow));
	}

	@Test
	public void NormalTest(){
		final FirstRowIndexFilter filter=new FirstRowIndexFilter();
		Assert.assertTrue(filter.filterHouseRow(row));
	}

	@Test
	public void wrongTest(){
		final FirstRowIndexFilter filter=new FirstRowIndexFilter();
		Assert.assertFalse(filter.filterHouseRow(wrongRow));
	}
}

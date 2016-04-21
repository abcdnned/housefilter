package tom.yang.housefilter;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.rowfilter.FirstRowIndexFilter;


public class FirstRowIndexFilterTest {

	private static HouseRow row;
	private static HouseRow wrongRow;
	private static HouseRow emptyRow;

	@BeforeClass
	public static void setup(){
		row=new HouseRow();
		row.getCells().add("1");
		row.getCells().add("v1");

		wrongRow=new HouseRow();
		wrongRow.getCells().add("k2");
		wrongRow.getCells().add("v2");

		emptyRow=new HouseRow();
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

	@Test
	public void emptyTest(){
		final FirstRowIndexFilter filter=new FirstRowIndexFilter();
		Assert.assertFalse(filter.filterHouseRow(emptyRow));
	}
}

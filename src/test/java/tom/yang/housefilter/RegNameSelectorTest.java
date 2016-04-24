package tom.yang.housefilter;


import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.selector.RegNameSelector;

public class RegNameSelectorTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testConstructorNull() {
		thrown.expect(NullPointerException.class);
		new RegNameSelector(null);
	}

	@Test
	public void testNormal() {
		HouseRow row = new HouseRow();
		row.getCells().add(new HouseCell("a", 1));
		row.getCells().add(new HouseCell("bcd", 2));
		row.getCells().add(new HouseCell("789", 3));

		RegNameSelector selector = new RegNameSelector("[a-z]*");
		List<HouseCell> cells = selector.selectCells(row);

		Assert.assertEquals(2, cells.size());

		Assert.assertEquals("a", cells.get(0).getValue());
		Assert.assertEquals("bcd", cells.get(1).getValue());
	}


}

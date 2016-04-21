package tom.yang.housefilter;

import org.junit.Assert;
import org.junit.Test;

import tom.yang.housefilter.condition.HasCellValueCondition;
import tom.yang.housefilter.core.HouseRow;

public class HasCellValueConditionTest {
	@Test
	public void testNormal(){
		final String cellValue="condition";
		final HouseRow row=new HouseRow();
		row.getCells().add("value");
		row.getCells().add("condition");
		final HasCellValueCondition c=new HasCellValueCondition(cellValue);
		Assert.assertTrue(c.match(row));
	}

	@Test
	public void testEmpty(){
		final String cellValue="condition";
		final HouseRow row=new HouseRow();
		final HasCellValueCondition c=new HasCellValueCondition(cellValue);
		Assert.assertFalse(c.match(row));
	}

	@Test
	public void testWrong(){
		final String cellValue="condition";
		final HouseRow row=new HouseRow();
		row.getCells().add("rownfds");
		final HasCellValueCondition c=new HasCellValueCondition(cellValue);
		Assert.assertFalse(c.match(row));
	}
}

package tom.yang.housefilter;

import org.junit.Assert;
import org.junit.Test;

import tom.yang.housefilter.condition.HasCellValueCondition;
import tom.yang.housefilter.core.ConditionContext;
import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;

public class HasCellValueConditionTest {

	@Test
	public void testNormal(){
		final String cellValue="condition";

		final HouseRow row=new HouseRow();
		final ConditionContext context=new ConditionContext();
		context.setRow(row);

		row.getCells().add(new HouseCell("value", 1));
		row.getCells().add(new HouseCell("condition", 2));
		final HasCellValueCondition c=new HasCellValueCondition(cellValue);
		Assert.assertTrue(c.match(context));
	}

	@Test
	public void testWrong(){
		final String cellValue="condition";

		final HouseRow row=new HouseRow();
		row.getCells().add(new HouseCell("rownfds", 1));
		final ConditionContext context = new ConditionContext();
		context.setRow(row);

		final HasCellValueCondition c=new HasCellValueCondition(cellValue);
		Assert.assertFalse(c.match(context));
	}
}

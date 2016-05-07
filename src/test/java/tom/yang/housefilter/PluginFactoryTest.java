package tom.yang.housefilter;


import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.condition.HasCellValueCondition;
import tom.yang.housefilter.condition.WeightCondition;
import tom.yang.housefilter.core.ConditionContext;
import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.factory.PluginFactory;
import tom.yang.housefilter.rowfilter.FirstRowIndexFilter;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;

public class PluginFactoryTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testCreateFirstRowFilter()
			throws InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		final IHouseRowFilter filter = PluginFactory.FILTER_FACTORY.create("FirstRowIndexFilter", null);
		Assert.assertTrue(filter instanceof FirstRowIndexFilter);
	}

	@Test
	public void testCreateHasCellValueCondition()
			throws InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		final WeightCondition condition = PluginFactory.CONDITION_FACTORY.create("HasCellValueCondition",
				"condition");
		Assert.assertTrue(condition instanceof HasCellValueCondition);
		final ConditionContext ctx = new ConditionContext();
		final HouseRow row = new HouseRow();
		final HouseCell cell = new HouseCell("condition", 1);
		row.getCells().add(cell);
		ctx.setRow(row);

		Assert.assertTrue(condition.match(ctx));
	}


}

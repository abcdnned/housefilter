package tom.yang.housefilter;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.condition.WeightCondition;
import tom.yang.housefilter.core.ConditionContext;
import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.core.WeightCaculator;
import tom.yang.housefilter.core.WeightItem;
import tom.yang.housefilter.weight.ICellWeight;

public class WeightCaculatorTest {


	private class AllRightCondition implements WeightCondition {

		@Override
		public boolean match(ConditionContext context) {
			return true;
		}

	}

	private class TenWeight implements ICellWeight{

		@Override
		public int getWeight(HouseCell cellValue) {
			return 10;
		}

	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testNormal() {
		HouseRow row = new HouseRow();
		row.getCells().add(new HouseCell("a", 1));
		row.getCells().add(new HouseCell("b", 2));
		row.getCells().add(new HouseCell("c", 3));

		List<WeightCondition> conditions = new ArrayList<WeightCondition>();
		conditions.add(new AllRightCondition());
		ICellWeight weight = new TenWeight();

		WeightItem item = new WeightItem();
		item.setConditions(conditions);
		item.setWeight(weight);
		List<WeightItem> items = new ArrayList<WeightItem>();
		items.add(item);

		WeightCaculator caculator = new WeightCaculator();
		int w = caculator.caculatorRow(items, row);

		Assert.assertEquals(30, w);
	}

	@Test
	public void testNull() {
		thrown.expect(NullPointerException.class);

		WeightCaculator caculator = new WeightCaculator();
		caculator.caculatorRow(null, null);
	}

}


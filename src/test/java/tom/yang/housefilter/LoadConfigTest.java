package tom.yang.housefilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.condition.WeightCondition;
import tom.yang.housefilter.config.ConfigLoader;
import tom.yang.housefilter.config.WeightConfig;
import tom.yang.housefilter.core.ConditionContext;
import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.core.WeightItem;
import tom.yang.housefilter.rowfilter.CellValueFilter;
import tom.yang.housefilter.rowfilter.FirstRowIndexFilter;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;

public class LoadConfigTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testLoadRowFilter() throws FileNotFoundException, IOException {
		ConfigLoader loader = new ConfigLoader();
		File tf = new File(getClass().getResource("test.hf").getFile());
		WeightConfig config = loader.load(tf);
		List<IHouseRowFilter> filters = config.getFilters();
		Assert.assertTrue(filters.stream().anyMatch(f -> (f instanceof FirstRowIndexFilter)));
		Assert.assertTrue(filters.stream().anyMatch(f -> (f instanceof CellValueFilter)));

		HouseRow row = new HouseRow();
		row.getCells().add(new HouseCell("¶þ·¿", 1));

		HouseRow wrongRow = new HouseRow();
		wrongRow.getCells().add(new HouseCell("fdsafda", 1));

		filters.forEach(f -> {
			if (f instanceof CellValueFilter) {
				Assert.assertTrue(f.filterHouseRow(row));
				Assert.assertFalse(f.filterHouseRow(wrongRow));
			}
		});
	}

	@Test
	public void testLoadWeightItem() throws FileNotFoundException, IOException {
		ConfigLoader loader = new ConfigLoader();
		File tf = new File(getClass().getResource("test.hf").getFile());
		WeightConfig config = loader.load(tf);

		List<WeightItem> items = config.getWeightItems();
		Assert.assertEquals(1, items.size());

		WeightItem item = items.get(0);
		Assert.assertEquals(500, item.getWeight().getWeight(null));

		List<WeightCondition> conditions = item.getConditions();
		Assert.assertEquals(2, conditions.size());


		HouseRow row = new HouseRow();
		row.getCells().add(new HouseCell("318Åª6ºÅ", 1));

		ConditionContext ctx = new ConditionContext();
		ctx.setRow(row);

		Assert.assertTrue(conditions.get(0).match(ctx) || conditions.get(1).match(ctx));
	}

	@Test
	public void testNull() {

	}

}

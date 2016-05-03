package tom.yang.housefilter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.config.ConfigLoader;
import tom.yang.housefilter.config.WeightConfig;
import tom.yang.housefilter.rowfilter.CellValueFilter;
import tom.yang.housefilter.rowfilter.FirstRowIndexFilter;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;

public class LoadConfigTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testLoadRowFilter() throws FileNotFoundException, IOException {
		ConfigLoader loader=new ConfigLoader();
		File tf = new File(getClass().getResource("test.hf").getFile());
		WeightConfig config = loader.load(tf);
		List<IHouseRowFilter> filters = config.getFilters();
		Assert.assertTrue(filters.stream().anyMatch(f -> (f instanceof FirstRowIndexFilter)));
		Assert.assertTrue(filters.stream().anyMatch(f -> (f instanceof CellValueFilter)));
	}

	@Test
	public void testLoadWeightItem() {

	}

	@Test
	public void testNull() {

	}

}

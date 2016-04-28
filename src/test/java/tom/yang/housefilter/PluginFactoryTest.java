package tom.yang.housefilter;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.factory.PluginFactory;
import tom.yang.housefilter.rowfilter.FirstRowIndexFilter;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;

public class PluginFactoryTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testCreateFirstRowFilter() throws InstantiationException, IllegalAccessException {
		IHouseRowFilter filter = PluginFactory.FILTER_FACTORY.create("FirstRowIndexFilter");
		Assert.assertTrue(filter instanceof FirstRowIndexFilter);
	}


}

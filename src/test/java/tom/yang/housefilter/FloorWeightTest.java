package tom.yang.housefilter;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.weight.FloorWeight;

public class FloorWeightTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testNormal() {
		FloorWeight fw = new FloorWeight("10,9,15");
		HouseCell cell = new HouseCell("1304", 1);
		Assert.assertEquals(130, fw.getWeight(cell));
		Assert.assertEquals(0, fw.getWeight(new HouseCell("508", 1)));
	}

}

package tom.yang.housefilter;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.util.RegUtils;

public class RegularUtilsTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testNormal() {
		Assert.assertTrue(RegUtils.match("a", "[a-z]"));
		Assert.assertFalse(RegUtils.match("1", "[a-z]"));
		Assert.assertTrue(RegUtils.match("fdsafe1432(, reg)df", ".*"));
		Assert.assertTrue(RegUtils.match("fdsavvvv123", "fdsavvvv123"));
	}

	@Test
	public void testNull() {
		thrown.expect(NullPointerException.class);
		RegUtils.match(null, "fdsf");
	}

	@Test
	public void testRegNull() {
		thrown.expect(NullPointerException.class);
		RegUtils.match("fdsfs", null);
	}

}

package tom.yang.housefilter.rowfilter;


import org.apache.commons.lang.math.NumberUtils;

import tom.yang.housefilter.core.HouseRow;

public class FirstRowIndexFilter implements IHouseRowFilter {

	private static final int FIRST_ROW=0;

	@Override
	public boolean filterHouseRow(final HouseRow row) {
		if(row.getCells().size()>0){
			final String string = row.getCells().get(FIRST_ROW).getValue();
			return NumberUtils.isNumber(string);
		}
		return false;
	}

}

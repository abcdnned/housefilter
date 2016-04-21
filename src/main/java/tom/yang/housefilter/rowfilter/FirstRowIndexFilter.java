package tom.yang.housefilter.rowfilter;

import tom.yang.housefilter.core.HouseRow;

public class FirstRowIndexFilter implements IHouseRowFilter {

	private static final int FIRST_ROW=0;

	public boolean filterHouseRow(final HouseRow row) {
		if(row.getCells().size()>0){
			final String string = row.getCells().get(FIRST_ROW);
			try{
				final int id=Integer.valueOf(string);
			}catch(final Throwable ignore){
				return false;
			}
			return true;
		}
		return false;
	}

}

package tom.yang.housefilter.rowfilter;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;

public class ExcludeFilter implements IHouseRowFilter {

	private final String con;

	public ExcludeFilter(final String arg) {
		if(arg==null){
			throw new NullPointerException();
		}
		con=arg;
	}

	@Override
	public boolean filterHouseRow(final HouseRow row) {
		for(final HouseCell cell:row.getCells()){
			if(cell.getValue().equals(con)){
				return false;
			}
		}
		return true;
	}

}

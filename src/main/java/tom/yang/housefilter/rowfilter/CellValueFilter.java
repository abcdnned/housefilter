package tom.yang.housefilter.rowfilter;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;


public class CellValueFilter implements IHouseRowFilter {

	private String filter;

	public CellValueFilter(String value) {
		filter = value;
	}

	@Override
	public boolean filterHouseRow(HouseRow row) {
		for (HouseCell cell : row.getCells()) {
			if (cell.getValue().equals(filter)) {
				return true;
			}
		}
		return false;
	}

}

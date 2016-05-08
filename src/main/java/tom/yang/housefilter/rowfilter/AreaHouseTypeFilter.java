package tom.yang.housefilter.rowfilter;

import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.util.HouseRowUtils;


public class AreaHouseTypeFilter implements IHouseRowFilter {

	private String con;

	private int type;

	private int typeId;

	public AreaHouseTypeFilter(String arg) {
		String[] ss = HouseRowUtils.splitArgs(arg, 3);
		con = ss[0];
		type = Integer.valueOf(ss[1]);
		typeId = Integer.valueOf(ss[2]) - 1;
	}

	@Override
	public boolean filterHouseRow(HouseRow row) {
		if (HouseRowUtils.hasCellValue(row, con)) {
			if (HouseRowUtils.getHouseType(row.getCells().get(typeId).getValue()) == type) {
				return false;
			}
		}
		return true;
	}

}

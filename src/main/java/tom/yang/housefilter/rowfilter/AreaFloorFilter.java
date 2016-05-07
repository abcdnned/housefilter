package tom.yang.housefilter.rowfilter;

import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.util.HouseRowUtils;

public class AreaFloorFilter implements IHouseRowFilter {

	private String condition;
	private int highest;
	private int lowest;
	private int floorId;

	public AreaFloorFilter(final String arg) {
		if(arg!=null){
			final String[] ss = arg.split(",");
			if(ss.length<4){
				throw new IllegalArgumentException();
			}
			condition=ss[0];
			lowest=Integer.valueOf(ss[1]);
			highest=Integer.valueOf(ss[2]);
			floorId=Integer.valueOf(ss[3])-1;
		}
	}

	@Override
	public boolean filterHouseRow(final HouseRow row) {
		if(HouseRowUtils.hasCellValue(row, condition)){
			final int floor=Integer.valueOf(HouseRowUtils.getFloor(row.getCells().get(floorId).getValue()));
			return floor<=highest&&floor>=lowest;
		}
		return true;
	}

}

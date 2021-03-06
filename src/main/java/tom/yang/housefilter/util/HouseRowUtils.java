package tom.yang.housefilter.util;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;

public class HouseRowUtils {
	public static int getFloor(final String floorString){
		int floor=Integer.valueOf(floorString.substring(0, floorString.indexOf('0')));
		if(floorString.charAt(floorString.indexOf('0')+1)=='0'){
			return floor*10;
		}
		return floor;
	}

	public static int getHouseType(final String floorString){
		return Double.valueOf(floorString.substring(floorString.indexOf('0')+1,floorString.length())).intValue();
	}

	public static boolean hasCellValue(final HouseRow row,final String value){
		for(final HouseCell cell:row.getCells()){
			if(cell.getValue().equals(value)){
				return true;
			}
		}
		return false;
	}

	public static String[] splitArgs(final String args,final int min){
		if(args==null){
			throw new NullPointerException();
		}
		final String[] ss=args.split(",");
		if(ss.length<min){
			throw new IllegalArgumentException();
		}
		return ss;
	}
}

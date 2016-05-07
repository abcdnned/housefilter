package tom.yang.housefilter.weight;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.util.HouseRowUtils;


public class FloorWeight implements ICellWeight {

	private int factor = 1;

	private int highest = 100;

	private int lowest = 0;

	public FloorWeight(final String arg) {
		final String[] args = arg.split(",");
		for (int i = 0; i < args.length; ++i) {
			switch (i) {
			case 0:
				factor = Integer.valueOf(args[i]);
				break;
			case 1:
				lowest = Integer.valueOf(args[i]);
				break;
			case 2:
				highest = Integer.valueOf(args[i]);
				break;
			}
		}
	}

	@Override
	public int getWeight(final HouseCell cell) {
		try{
			final int floor = HouseRowUtils.getFloor(cell.getValue());
			if (floor <= highest && floor >= lowest) {
				return floor * factor;
			}
		}catch(final Throwable ignore){
		}
		return 0;
	}

}

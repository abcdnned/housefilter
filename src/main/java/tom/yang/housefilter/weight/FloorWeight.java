package tom.yang.housefilter.weight;

import tom.yang.housefilter.core.HouseCell;


public class FloorWeight implements ICellWeight {

	private int factor = 1;

	private int highest = 100;

	private int lowest = 0;

	public FloorWeight(String arg) {
		String[] args = arg.split(",");
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
	public int getWeight(HouseCell cell) {
		int floor = Integer.valueOf(cell.getValue().substring(0, cell.getValue().indexOf('0')));
		if (floor <= highest && floor >= lowest) {
			return floor * factor;
		}
		return 0;
	}

}

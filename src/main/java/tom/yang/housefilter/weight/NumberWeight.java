package tom.yang.housefilter.weight;

import tom.yang.housefilter.core.HouseCell;


public class NumberWeight implements ICellWeight {

	private int n;

	public NumberWeight(int n) {
		this.n = n;
	}

	@Override
	public int getWeight(HouseCell cell) {
		return n;
	}

}

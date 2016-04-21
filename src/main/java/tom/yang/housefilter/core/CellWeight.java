package tom.yang.housefilter.core;

import tom.yang.housefilter.condition.WeightCondition;

public class CellWeight {
	int weight;
	WeightCondition wc;

	public int getWeight() {
		return weight;
	}

	public void setWeight(final int weight) {
		this.weight = weight;
	}

	public WeightCondition getWc() {
		return wc;
	}

	public void setWc(final WeightCondition wc) {
		this.wc = wc;
	}
}

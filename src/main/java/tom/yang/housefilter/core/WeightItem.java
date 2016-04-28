package tom.yang.housefilter.core;

import java.util.List;

import tom.yang.housefilter.condition.WeightCondition;
import tom.yang.housefilter.weight.ICellWeight;

public class WeightItem {
	private List<WeightCondition> conditions;

	private ICellWeight weight;

	public List<WeightCondition> getConditions() {
		return conditions;
	}

	public ICellWeight getWeight() {
		return weight;
	}

	public void setConditions(List<WeightCondition> conditions) {
		this.conditions = conditions;
	}

	public void setWeight(ICellWeight weight) {
		this.weight = weight;
	}
}

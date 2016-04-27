package tom.yang.housefilter.core;

import java.util.List;

import tom.yang.housefilter.condition.WeightCondition;
import tom.yang.housefilter.selector.ICellSelector;
import tom.yang.housefilter.weight.ICellWeight;

public class WeightItem {
	private ICellSelector selector;
	private ICellWeight weight;

	public ICellSelector getSelector() {
		return selector;
	}

	public void setSelector(ICellSelector selector) {
		this.selector = selector;
	}

	public ICellWeight getWeight() {
		return weight;
	}

	public void setWeight(ICellWeight weight) {
		this.weight = weight;
	}

	public List<WeightCondition> getConditions() {
		return conditions;
	}

	public void setConditions(List<WeightCondition> conditions) {
		this.conditions = conditions;
	}

	private List<WeightCondition> conditions;
}

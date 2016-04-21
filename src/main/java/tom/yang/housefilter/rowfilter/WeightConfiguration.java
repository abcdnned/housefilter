package tom.yang.housefilter.rowfilter;

import java.util.Map;

import tom.yang.housefilter.core.CellWeight;

public class WeightConfiguration {

	private  Map<String, CellWeight> weights ;

	public Map<String, CellWeight> getWeights() {
		return weights;
	}

	public void setWeights(final Map<String, CellWeight> weights) {
		this.weights = weights;
	}

}

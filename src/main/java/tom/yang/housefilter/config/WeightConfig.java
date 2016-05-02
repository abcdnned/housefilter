package tom.yang.housefilter.config;

import java.util.ArrayList;
import java.util.List;

import tom.yang.housefilter.core.WeightItem;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;

public class WeightConfig {

	private List<IHouseRowFilter> filters = new ArrayList<IHouseRowFilter>();

	private List<WeightItem> weightItems = new ArrayList<WeightItem>();

	public List<IHouseRowFilter> getFilters() {
		return filters;
	}

	public List<WeightItem> getWeightItems() {
		return weightItems;
	}

	public void setFilters(List<IHouseRowFilter> filters) {
		this.filters = filters;
	}

	public void setWeightItems(List<WeightItem> weightItems) {
		this.weightItems = weightItems;
	}
}

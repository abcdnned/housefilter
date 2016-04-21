package tom.yang.housefilter.condition;

import tom.yang.housefilter.core.HouseRow;

public interface WeightCondition {
	boolean match(HouseRow rowContext);
}

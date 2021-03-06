package tom.yang.housefilter.condition;

import tom.yang.housefilter.core.ConditionContext;
import tom.yang.housefilter.core.HouseCell;

public class HasCellValueCondition implements WeightCondition {
	private final String condition;

	public HasCellValueCondition(final String condition) {
		if(condition==null){
			throw new IllegalArgumentException("condition can't be null.");
		}
		this.condition=condition;
	}
	@Override
	public boolean match(final ConditionContext context) {
		for(final HouseCell cell:context.getRow().getCells()){
			if(cell.getValue().equals(condition)){
				return true;
			}
		}
		return false;
	}

}

package tom.yang.housefilter.condition;

import tom.yang.housefilter.core.ConditionContext;

public class IfCellCondition implements WeightCondition {

	private final String condition;

	public IfCellCondition(final String condition) {
		if(condition==null){
			throw new IllegalArgumentException("condition can't be null.");
		}
		this.condition=condition;
	}

	@Override
	public boolean match(final ConditionContext context) {
		if(context==null||context.getCellValue()==null){
			return false;
		}
		return context.getCellValue().getValue().equals(condition);
	}

}

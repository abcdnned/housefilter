package tom.yang.housefilter.condition;

import tom.yang.housefilter.core.ConditionContext;

public class ColumnCondition implements WeightCondition {
	private final  int con;

	public ColumnCondition(final String condition) {
		con=Integer.valueOf(condition)-1;
	}
	@Override
	public boolean match(final ConditionContext context) {
		return context.getColumnNum()==con;
	}

}

package tom.yang.housefilter.condition;

import tom.yang.housefilter.core.ConditionContext;
import tom.yang.housefilter.util.HouseRowUtils;

public class HouseTypeCondition implements WeightCondition {

	private final Integer type;
	private final Integer col;

	public HouseTypeCondition(final String arg) {
		final String[] ss=HouseRowUtils.splitArgs(arg, 2);
		type=Integer.valueOf(ss[0]);
		col=Integer.valueOf(ss[1])-1;
	}

	@Override
	public boolean match(final ConditionContext context) {
		if(context.getColumnNum()==col){
			return type==HouseRowUtils.getHouseType(context.getCellValue().getValue());
		}
		return false;
	}

}

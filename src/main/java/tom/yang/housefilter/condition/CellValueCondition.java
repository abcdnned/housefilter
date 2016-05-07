package tom.yang.housefilter.condition;

import tom.yang.housefilter.core.ConditionContext;
import tom.yang.housefilter.util.HouseRowUtils;

public class CellValueCondition implements WeightCondition {

	private final String con;
	private final Integer id;
	private final String cell;

	public CellValueCondition(final String args) {
		final String[] ss = HouseRowUtils.splitArgs(args, 3);
		cell=ss[0];
		id=Integer.valueOf(ss[1])-1;
		con=ss[2];
	}

	@Override
	public boolean match(final ConditionContext context) {
		if(context.getCellValue().getValue().equals(cell)){
			return context.getRow().getCells().get(id).getValue().equals(con);
		}
		return false;
	}

}

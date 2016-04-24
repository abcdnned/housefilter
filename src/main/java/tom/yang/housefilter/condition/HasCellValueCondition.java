package tom.yang.housefilter.condition;

import java.util.List;

import tom.yang.housefilter.core.ConditionContext;
import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;

public class HasCellValueCondition implements WeightCondition {

	private final String condition;

	public HasCellValueCondition(final String condition) {
		if(condition==null){
			throw new IllegalArgumentException("condition can't be null.");
		}
		this.condition=condition;
	}

	@Override
	public boolean match(ConditionContext context) {
		HouseRow row = context.getRow();
		if (row != null) {
			List<HouseCell> cells = row.getCells();
			if (cells != null) {
				return cells.stream().anyMatch(cell -> cell.getValue().equals(condition));
			}
		}
		return false;
	}

}

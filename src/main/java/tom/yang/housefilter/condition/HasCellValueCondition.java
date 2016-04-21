package tom.yang.housefilter.condition;

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
	public boolean match(final HouseRow rowContext) {
		return rowContext.getCells().stream().anyMatch(cell->cell.equals(condition));
	}

}

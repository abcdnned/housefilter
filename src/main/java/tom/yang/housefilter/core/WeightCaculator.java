package tom.yang.housefilter.core;

import java.util.List;

import tom.yang.housefilter.condition.WeightCondition;

public class WeightCaculator {

	public int caculatorRow(List<WeightItem> wis, final HouseRow row) {
		int result=0;
		for (WeightItem item : wis) {
			List<HouseCell> cells = item.getSelector().selectCells(row);
			for (HouseCell cell : cells) {
				int w = item.getWeight().getWeight(cell);
				boolean pass = true;
				for (WeightCondition condition : item.getConditions()) {
					ConditionContext context = new ConditionContext();
					context.setWeight(w);
					context.setCellValue(cell);
					context.setColumnNum(cell.getCol());
					context.setRow(row);
					if (!condition.match(context)) {
						pass = false;
						break;
					}
				}
				if (pass) {
					result += w;
				}
			}
		}
		return result;
	}
}

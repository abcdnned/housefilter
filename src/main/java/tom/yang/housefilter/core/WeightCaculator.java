package tom.yang.housefilter.core;

import java.util.List;

import tom.yang.housefilter.condition.WeightCondition;

public class WeightCaculator {

	public int caculatorRow(final List<WeightItem> wis, final HouseRow row) {
		int result=0;
		for (final WeightItem item : wis) {
			if(item!=null){
				for (final HouseCell cell : row.getCells()) {
					try{
						final int w = item.getWeight().getWeight(cell);
						boolean pass = true;
						for (final WeightCondition condition : item.getConditions()) {
							if(condition!=null){
								final ConditionContext context = new ConditionContext();
								context.setWeight(w);
								context.setCellValue(cell);
								context.setColumnNum(cell.getCol());
								context.setRow(row);
								if (!condition.match(context)) {
									pass = false;
									break;
								}
							}
						}
						if (pass) {
							result += w;
						}
					}catch(final Throwable ignore){
						ignore.printStackTrace();
					}
				}
			}
		}
		return result;
	}
}

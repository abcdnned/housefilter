package tom.yang.housefilter.core;

import java.util.Comparator;
import java.util.List;

public class RowSorter {

	public void sortRows(List<HouseRow> rows) {
		rows.sort(new Comparator<HouseRow>() {

			@Override
			public int compare(HouseRow o1, HouseRow o2) {
				return o2.getRowWeight() - o1.getRowWeight();
			}
		});
	}
}

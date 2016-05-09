package tom.yang.housefilter.weight;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.util.HouseRowUtils;
import tom.yang.housefilter.util.RegUtils;

public class RegularSpecficWeight implements ICellWeight {

	private final String reg;

	private final int w;

	public RegularSpecficWeight(final String arg) {
		final String[] ss=HouseRowUtils.splitArgs(arg, 2);
		reg=ss[0];
		w=Integer.valueOf(ss[1]);
	}

	@Override
	public int getWeight(final HouseCell cell) {
		if(RegUtils.match(cell.getValue(), reg)){
			return w;
		}
		return 0;
	}

}

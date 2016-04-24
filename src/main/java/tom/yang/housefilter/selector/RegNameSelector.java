package tom.yang.housefilter.selector;

import java.util.List;
import java.util.stream.Collectors;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.core.ICellSelector;
import tom.yang.housefilter.util.RegUtils;

public class RegNameSelector implements ICellSelector {

	private String regexpr;

	public RegNameSelector(String regexpr) {
		if (regexpr == null) {
			throw new NullPointerException();
		}
		this.regexpr = regexpr;
	}

	@Override
	public List<HouseCell> selectCells(HouseRow row) {
		return row.getCells().stream().filter(c -> RegUtils.match(c.getValue(), regexpr)).collect(Collectors.toList());
	}

}

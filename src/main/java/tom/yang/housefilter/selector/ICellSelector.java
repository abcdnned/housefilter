package tom.yang.housefilter.selector;

import java.util.List;

import tom.yang.housefilter.core.HouseCell;
import tom.yang.housefilter.core.HouseRow;

public interface ICellSelector {

	List<HouseCell> selectCells(HouseRow row);
}

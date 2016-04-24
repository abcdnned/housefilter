package tom.yang.housefilter.core;

import java.util.List;

public interface ICellSelector {

	List<HouseCell> selectCells(HouseRow row);
}

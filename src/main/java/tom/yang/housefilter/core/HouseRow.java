package tom.yang.housefilter.core;

import java.util.ArrayList;
import java.util.List;

public class HouseRow {

	private final List<HouseCell> cells = new ArrayList<HouseCell>();

	private String id;

	private int rowWeight;

	public List<HouseCell> getCells() {
		return cells;
	}

	public void addCell(final String value){
		cells.add(new HouseCell(value,cells.size()+1));
	}

	public String getId() {
		return id;
	}

	public int getRowWeight() {
		return rowWeight;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public void setRowWeight(final int rowWeight) {
		this.rowWeight = rowWeight;
	}
}

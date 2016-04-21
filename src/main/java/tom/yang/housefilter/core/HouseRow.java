package tom.yang.housefilter.core;

import java.util.ArrayList;
import java.util.List;

public class HouseRow {

	private final List<String> cells=new ArrayList<String>();

	private String id;

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	private int rowWeight;

	public int getRowWeight() {
		return rowWeight;
	}

	public void setRowWeight(final int rowWeight) {
		this.rowWeight = rowWeight;
	}

	public List<String> getCells() {
		return cells;
	}
}

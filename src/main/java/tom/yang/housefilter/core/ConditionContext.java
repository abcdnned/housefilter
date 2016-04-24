package tom.yang.housefilter.core;

public class ConditionContext {
	private HouseCell cellValue;
	private int columnNum;
	private HouseRow row;
	private int weight;

	public HouseCell getCellValue() {
		return cellValue;
	}

	public int getColumnNum() {
		return columnNum;
	}

	public HouseRow getRow() {
		return row;
	}

	public int getWeight() {
		return weight;
	}

	public void setCellValue(HouseCell cellValue) {
		this.cellValue = cellValue;
	}

	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	public void setRow(HouseRow row) {
		this.row = row;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}

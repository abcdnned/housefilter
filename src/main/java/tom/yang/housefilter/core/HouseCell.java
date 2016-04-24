package tom.yang.housefilter.core;

public class HouseCell {

	private int col;

	private String value;

	public HouseCell() {

	}

	public HouseCell(String value, int col) {
		setCol(col);
		setValue(value);
	}

	public int getCol() {
		return col;
	}

	public String getValue() {
		return value;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

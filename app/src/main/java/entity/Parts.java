package entity;

public class Parts {
	private int _id;
	private String pname;
	private int pimage;
	private int pinventory;
	private float poldprice;
	private float pnewprice;
	private String pbrand;
	private String preferral;

	public Parts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parts(int _id, String pname, int pimage, int pinventory,
			float poldprice, float pnewprice, String pbrand, String preferral) {
		super();
		this._id = _id;
		this.pname = pname;
		this.pimage = pimage;
		this.pinventory = pinventory;
		this.poldprice = poldprice;
		this.pnewprice = pnewprice;
		this.pbrand = pbrand;
		this.preferral = preferral;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPimage() {
		return pimage;
	}

	public void setPimage(int pimage) {
		this.pimage = pimage;
	}

	public int getPinventory() {
		return pinventory;
	}

	public void setPinventory(int pinventory) {
		this.pinventory = pinventory;
	}

	public float getPoldprice() {
		return poldprice;
	}

	public void setPoldprice(float poldprice) {
		this.poldprice = poldprice;
	}

	public float getPnewprice() {
		return pnewprice;
	}

	public void setPnewprice(float pnewprice) {
		this.pnewprice = pnewprice;
	}

	public String getPbrand() {
		return pbrand;
	}

	public void setPbrand(String pbrand) {
		this.pbrand = pbrand;
	}

	public String getPreferral() {
		return preferral;
	}

	public void setPreferral(String preferral) {
		this.preferral = preferral;
	}

}

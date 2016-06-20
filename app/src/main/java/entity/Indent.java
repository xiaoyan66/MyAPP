package entity;

public class Indent {
	private int _id;
	private int iimage;
	private String iname;
	private float iprice;
	private int icount;
	private int uid;
	private String inumber;
	private int dstate;

	public Indent(int iimage, String iname, float iprice, int icount, int uid,
			String inumber, int dstate) {
		super();
		this.iimage = iimage;
		this.iname = iname;
		this.iprice = iprice;
		this.icount = icount;
		this.uid = uid;
		this.inumber = inumber;
		this.dstate = dstate;
	}

	public Indent(int _id, int iimage, String iname, float iprice, int icount,
			int uid, String inumber, int dstate) {
		super();
		this._id = _id;
		this.iimage = iimage;
		this.iname = iname;
		this.iprice = iprice;
		this.icount = icount;
		this.uid = uid;
		this.inumber = inumber;
		this.dstate = dstate;
	}

	public Indent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getDstate() {
		return dstate;
	}

	public void setDstate(int dstate) {
		this.dstate = dstate;
	}

	public String getInumber() {
		return inumber;
	}

	public void setInumber(String inumber) {
		this.inumber = inumber;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getIimage() {
		return iimage;
	}

	public void setIimage(int iimage) {
		this.iimage = iimage;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public float getIprice() {
		return iprice;
	}

	public void setIprice(float iprice) {
		this.iprice = iprice;
	}

	public int getIcount() {
		return icount;
	}

	public void setIcount(int icount) {
		this.icount = icount;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}

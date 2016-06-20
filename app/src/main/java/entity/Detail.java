package entity;

public class Detail {
	private int _id;
	private String inumber;
	private float dallprice;
	private String dtime;
	private int dstate;
	private int uid;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getInumber() {
		return inumber;
	}

	public void setInumber(String inumber) {
		this.inumber = inumber;
	}

	public float getDallprice() {
		return dallprice;
	}

	public void setDallprice(float dallprice) {
		this.dallprice = dallprice;
	}

	public String getDtime() {
		return dtime;
	}

	public void setDtime(String dtime) {
		this.dtime = dtime;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getDstate() {
		return dstate;
	}

	public void setDstate(int dstate) {
		this.dstate = dstate;
	}

}

package entity;

public class Collect {
	private int _id;
	private int uid;
	private int gid;
	private String gname;
	private float gprice;
	private int gsource;
	private int gimage;

	public Collect(int _id, int uid, int gid, String gname, float gprice,
			int gsource, int gimage) {
		super();
		this._id = _id;
		this.uid = uid;
		this.gid = gid;
		this.gname = gname;
		this.gprice = gprice;
		this.gsource = gsource;
		this.gimage = gimage;
	}

	public Collect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getGimage() {
		return gimage;
	}

	public void setGimage(int gimage) {
		this.gimage = gimage;
	}

	public int getGsource() {
		return gsource;
	}

	public void setGsource(int gsource) {
		this.gsource = gsource;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public float getGprice() {
		return gprice;
	}

	public void setGprice(float gprice) {
		this.gprice = gprice;
	}

}

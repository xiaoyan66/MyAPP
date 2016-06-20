package entity;

public class Brand {
	private int _id;
	private String bname;
	private String breferral;
	private int bimage;

	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Brand(int _id, String bname, String breferral, int bimage) {
		super();
		this._id = _id;
		this.bname = bname;
		this.breferral = breferral;
		this.bimage = bimage;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBreferral() {
		return breferral;
	}

	public void setBreferral(String breferral) {
		this.breferral = breferral;
	}

	public int getBimage() {
		return bimage;
	}

	public void setBimage(int bimage) {
		this.bimage = bimage;
	}

}

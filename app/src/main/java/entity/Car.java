package entity;

public class Car {
	private int _id;
	private int sid;
	private String cname;
	private int cimage;
	private String crank;
	private String cengine;
	private String cgearbox;
	private String cstructure;
	private float coldprice;
	private float cnewprice;

	public Car(int _id, int sid, String cname, int cimage, String crank,
			String cengine, String cgearbox, String cstructure,
			float coldprice, float cnewprice) {
		super();
		this._id = _id;
		this.sid = sid;
		this.cname = cname;
		this.cimage = cimage;
		this.crank = crank;
		this.cengine = cengine;
		this.cgearbox = cgearbox;
		this.cstructure = cstructure;
		this.coldprice = coldprice;
		this.cnewprice = cnewprice;
	}

	public Car() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public int getCimage() {
		return cimage;
	}

	public void setCimage(int cimage) {
		this.cimage = cimage;
	}

	public String getCrank() {
		return crank;
	}

	public void setCrank(String crank) {
		this.crank = crank;
	}

	public String getCengine() {
		return cengine;
	}

	public void setCengine(String cengine) {
		this.cengine = cengine;
	}

	public String getCgearbox() {
		return cgearbox;
	}

	public void setCgearbox(String cgearbox) {
		this.cgearbox = cgearbox;
	}

	public String getCstructure() {
		return cstructure;
	}

	public void setCstructure(String cstructure) {
		this.cstructure = cstructure;
	}

	public float getColdprice() {
		return coldprice;
	}

	public void setColdprice(float coldprice) {
		this.coldprice = coldprice;
	}

	public float getCnewprice() {
		return cnewprice;
	}

	public void setCnewprice(float cnewprice) {
		this.cnewprice = cnewprice;
	}

}

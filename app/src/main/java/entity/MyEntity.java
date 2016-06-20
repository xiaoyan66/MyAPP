package entity;

public class MyEntity {

	private int myid;
	private int myimage;
	private String myname;
	private float myprice;
	private int mycount;

	private boolean checked;

	public int getMyid() {
		return myid;
	}

	public void setMyid(int myid) {
		this.myid = myid;
	}

	public int getMyimage() {
		return myimage;
	}

	public void setMyimage(int myimage) {
		this.myimage = myimage;
	}

	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

	public float getMyprice() {
		return myprice;
	}

	public void setMyprice(float myprice) {
		this.myprice = myprice;
	}

	public int getMycount() {
		return mycount;
	}

	public void setMycount(int mycount) {
		this.mycount = mycount;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}

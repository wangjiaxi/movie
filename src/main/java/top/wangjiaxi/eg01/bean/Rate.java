package top.wangjiaxi.eg01.bean;

public class Rate implements Comparable<Rate> {
	private int uid;
	private float rate;

	public Rate() {
		super();
	}

	public Rate(int uid, float rate) {
		super();
		this.uid = uid;
		this.rate = rate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "[uid=" + uid + ", rate=" + rate + "]";
	}

	@Override
	public int compareTo(Rate o) {
		if (o.getRate() == this.getRate()) {
			return 0;
		} else if (o.getRate() > this.getRate()) {
			return 1;
		} else {
			return -1;
		}
	}

}

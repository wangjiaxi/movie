package top.wangjiaxi.eg01.bean;

public class RateMovie implements Comparable<RateMovie> {
	private int movie;
	private float rate;

	public RateMovie() {
		super();
	}

	public RateMovie(int movie, float rate) {
		super();
		this.movie = movie;
		this.rate = rate;
	}

	public int getMovie() {
		return movie;
	}

	public void setMovie(int movie) {
		this.movie = movie;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "[movie=" + movie + ", rate=" + rate + "]";
	}

	@Override
	public int compareTo(RateMovie o) {
		if (o.getRate() == this.getRate()) {
			return 0;
		} else if (o.getRate() > this.getRate()) {
			return 1;
		} else {
			return -1;
		}
	}
}

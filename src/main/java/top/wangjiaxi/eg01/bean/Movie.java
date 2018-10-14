package top.wangjiaxi.eg01.bean;

/**
 * 电影评分信息
 * @author Administrator
 *
 */
public class Movie implements Comparable<Movie> {
	private int movie;
	private int rate;
	private long timeStamp;
	private int uid;

	public Movie() {
		super();
	}

	public Movie(int movie, int rate, long timeStamp, int uid) {
		super();
		this.movie = movie;
		this.rate = rate;
		this.timeStamp = timeStamp;
		this.uid = uid;
	}

	public int getMovie() {
		return movie;
	}

	public void setMovie(int movie) {
		this.movie = movie;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "[movie=" + movie + ", rate=" + rate + ", timeStamp=" + timeStamp + ", uid=" + uid + "]";
	}

	@Override
	public int compareTo(Movie o) {
		return o.getRate() - this.getRate();
	}

}

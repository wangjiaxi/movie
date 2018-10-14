package top.wangjiaxi.eg01.bean;

public class HotMovie implements Comparable<HotMovie> {
	private int movie;
	private int count;
 
	public HotMovie() {
		super();
	}
 
	public HotMovie(int movie, int count) {
		super();
		this.movie = movie;
		this.count = count;
	}
 
	public int getMovie() {
		return movie;
	}
 
	public void setMovie(int movie) {
		this.movie = movie;
	}
 
	public int getCount() {
		return count;
	}
 
	public void setCount(int count) {
		this.count = count;
	}
 
	@Override
	public String toString() {
		return "HotMovie [movie=" + movie + ", count=" + count + "]";
	}
 
	@Override
	public int compareTo(HotMovie o) {
		return o.getCount()-this.getCount();
	}


}

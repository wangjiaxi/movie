package top.wangjiaxi.eg01.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;

import top.wangjiaxi.eg01.bean.HotMovie;
import top.wangjiaxi.eg01.bean.Movie;
import top.wangjiaxi.eg01.bean.Rate;
import top.wangjiaxi.eg01.bean.RateMovie;

/**
 * 需求： 
 * 1.每个用户评分最高的3部电影评分信息 
 * 2.每个用户的uid和评分的平均值 
 * 3.最大方(评分平均值高)的3个用户的uid和评分平均值
 * 4.最热门的3部电影id和评价次数 
 * 5.评价最高的3部电影id和评分均值
 * 并将统计结果输出到结果文件中(或数据库中)
 * 
 * @author Administrator
 *
 */
public class Test {
	public static void main(String[] args) {
		// 保存uid对应的电影评分信息
		HashMap<Integer, ArrayList<Movie>> map = new HashMap<Integer, ArrayList<Movie>>();

		// 保存movie对应的电影评分信息
		HashMap<Integer, ArrayList<Movie>> map2 = new HashMap<Integer, ArrayList<Movie>>();

		// 把rating.txt中的数据读取出来，封装到java对象中
		try (BufferedReader br = new BufferedReader(new FileReader("D:/data/rating.json"));) {
			String line;
			while ((line = br.readLine()) != null) {
				// 把读取的数据转换成Movie对象
				Movie movie = JSON.parseObject(line, Movie.class);

				// 判断map集合中是否存有对应uid电影评分信息
				if (map.containsKey(movie.getUid())) {
					// 取出用户对应电影评分信息的集合
					map.get(movie.getUid()).add(movie);
				} else {
					// 如果不包含用户的评分信息就自己创建一个集合然后放进map集合中
					ArrayList<Movie> list = new ArrayList<Movie>();
					list.add(movie);
					map.put(movie.getUid(), list);
				}

				// 判断map2集合中是否有对应movie对应的电影评分信息
				if (map2.containsKey(movie.getMovie())) {
					// 如果有就拿出对应的集合，然后把当前的电影评分对象添加进去
					map2.get(movie.getMovie()).add(movie);
				} else {
					// 创建一个存放电影信息的list集合
					ArrayList<Movie> list = new ArrayList<Movie>();
					// 把电影评分对象添加进list集合中
					list.add(movie);
					// 再把movie对应的list集合添加进map2集合中
					map2.put(movie.getMovie(), list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 1.每个用户评分最高的3部电影评分信息
		// 把每个用户的电影信息集合降序排序
		for (Entry<Integer, ArrayList<Movie>> entry : map.entrySet()) {
			ArrayList<Movie> list = entry.getValue();
			Collections.sort(list);
		}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:/data/movie-q1.txt"));) {
			for (Entry<Integer, ArrayList<Movie>> entry : map.entrySet()) {
				bw.write(entry.getKey() + "：");
				bw.newLine();
				ArrayList<Movie> list = entry.getValue();
				bw.write(list.get(0).toString());
				bw.newLine();
				bw.write(list.get(1).toString());
				bw.newLine();
				bw.write(list.get(2).toString());
				bw.newLine();
			}
			System.out.println("movie-q1.txt保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("movie-q1.txt保存失败");
		}

		// 2.每个用户的uid和评分的平均值
		ArrayList<Rate> avgRateList = getAvgRateList(map);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:/data/movie-q2.txt"));) {
			for (Rate rate : avgRateList) {
				bw.write(rate.toString());
				bw.newLine();
			}
			System.out.println("movie-q2.txt保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("movie-q2.txt保存失败");
		}

		// 3.最大方(评分平均值高)的3个用户的uid和评分平均值
		Collections.sort(avgRateList);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:/data/movie-q3.txt"));) {
			for (int i = 0; i < 3; i++) {
				Rate rate = avgRateList.get(i);
				bw.write(rate.toString());
				bw.newLine();
			}
			System.out.println("movie-q3.txt保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("movie-q3.txt保存失败");
		}

		// 4.最热门的3部电影id和评价次数
		ArrayList<HotMovie> hotMovieList = getHotMovieList(map2);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:/data/movie-q4.txt"));) {
			for (int i = 0; i < 3; i++) {
				HotMovie hotMovie = hotMovieList.get(i);
				bw.write(hotMovie.toString());
				bw.newLine();
			}
			System.out.println("movie-q4.txt保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("movie-q4.txt保存失败");
		}

		// 5.评价最高的3部电影id和评分均值
		ArrayList<RateMovie> rateMovieList = tetRateMovieList(map2);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:/data/movie-q5.txt"));) {
			for (int i = 0; i < 3; i++) {
				RateMovie rateMovie = rateMovieList.get(i);
				bw.write(rateMovie.toString());
				bw.newLine();
			}
			System.out.println("movie-q5.txt保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("movie-q5.txt保存失败");
		}
	}

	private static ArrayList<RateMovie> tetRateMovieList(HashMap<Integer, ArrayList<Movie>> map2) {
		// 创建保存电影的历史评价分对象集合
		ArrayList<RateMovie> list = new ArrayList<RateMovie>();

		// 遍历电影对应的电影评分信息集合
		for (Entry<Integer, ArrayList<Movie>> entry : map2.entrySet()) {
			Integer movie = entry.getKey();// 电影的id
			ArrayList<Movie> list2 = entry.getValue();// 电影评分信息集合
			float rate = 0;
			for (Movie m : list2) {
				rate += m.getRate();
			}
			rate /= list2.size();
			list.add(new RateMovie(movie, rate));
		}

		Collections.sort(list);
		return list;
	}

	private static ArrayList<HotMovie> getHotMovieList(HashMap<Integer, ArrayList<Movie>> map2) {
		// 创建保存电影被评论次数对象的集合
		ArrayList<HotMovie> list = new ArrayList<HotMovie>();

		// 遍历电影对应的电影评分信息集合
		for (Entry<Integer, ArrayList<Movie>> entry : map2.entrySet()) {
			Integer movie = entry.getKey();// 电影的id
			ArrayList<Movie> list2 = entry.getValue();// 电影评分信息集合
			list.add(new HotMovie(movie, list2.size()));
		}

		Collections.sort(list);
		return list;
	}

	private static ArrayList<Rate> getAvgRateList(HashMap<Integer, ArrayList<Movie>> map) {
		// 存储用户评分的平均值信息
		ArrayList<Rate> list = new ArrayList<Rate>();
		// DecimalFormat df = new DecimalFormat("#.00");
		// df.format(number)
		// 遍历map集合，算出每一个用户的平均值，然后存储到list集合中
		for (Entry<Integer, ArrayList<Movie>> entry : map.entrySet()) {
			Integer uid = entry.getKey();
			float rate = 0;
			// 遍历电影评分信息然后累加评分
			ArrayList<Movie> list2 = entry.getValue();
			for (Movie m : list2) {
				rate += m.getRate();
			}
			rate /= list2.size();
			list.add(new Rate(uid, rate));
		}
		return list;
	}
}

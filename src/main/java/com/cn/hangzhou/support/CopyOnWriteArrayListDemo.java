package com.cn.hangzhou.support;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * http://ifeve.com/java-copy-on-write/
 * https://my.oschina.net/jielucky/blog/167198
 * Copy-On-Write简称COW，是一种用于程序设计中的优化策
 * CopyOnWrite的缺点
 * 1、内存占用问题
 * 2、数据一致性问题
 * CopyOnWrite并发容器用于读多写少的并发场景。比如白名单，黑名单，商品类目的访问和更新场景
 * @author Administrator
 *
 */
public class CopyOnWriteArrayListDemo {

	/**
	 * 读线程
	 *
	 */
	private static class ReadTask implements Runnable {
		List<String> list;

		public ReadTask(List<String> list) {
			this.list = list;
		}

		public void run() {
			for (String str : list) {
				System.out.println(str);
			}
		}
	}
	/**
	 * 写线程
	 *
	 */
	private static class WriteTask implements Runnable {
		List<String> list;
		int index;

		public WriteTask(List<String> list, int index) {
			this.list = list;
			this.index = index;
		}

		public void run() {
//			list.remove(index);
			list.add(index, "write_" + index);
		}
	}

	public void run() {
		final int NUM = 5;
//		List<String> list = new ArrayList<String>();
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 0; i < NUM; i++) {
			list.add("main_" + i);
		}
		ExecutorService executorService = Executors.newFixedThreadPool(NUM);
		for (int i = 0; i < NUM; i++) {
			executorService.execute(new ReadTask(list));
			executorService.execute(new WriteTask(list, i));
		}
		executorService.shutdown();
	}

	public static void main(String[] args) {
		new CopyOnWriteArrayListDemo().run();
	}
}












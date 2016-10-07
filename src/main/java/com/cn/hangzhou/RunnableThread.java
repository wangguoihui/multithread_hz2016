package com.cn.hangzhou;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Administrator
 *
 */
public class RunnableThread {
	
	public static Integer NUM = 10;
		
	public static Integer normal_count = 1;
	AtomicInteger count = new AtomicInteger(0);
	AtomicInteger count_a = new AtomicInteger(0);
	AtomicInteger count_b = new AtomicInteger(0);
	
	class ThreadA implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < NUM; i++) {
			    try {
	                Thread.sleep((int) Math.random() * 10);
			    } catch (InterruptedException e) {
	                e.printStackTrace();
			    }
			    String space = "	";
			    if(i%2 == 0){
			       space = "";
			    }
			    String name = Thread.currentThread().getName();
			    if("thread_A".equals(name)){
				   count_a.addAndGet(1);
			    }
			    if("thread_B".equals(name)){
				   count_b.addAndGet(1);
			    }
			    if(i == NUM -1){
				   System.out.println("FINISH," + name + "运行  :  " +count_a);
		           System.out.println("FINISH," + name + "运行  :  " +count_b);
			    }
			    System.out.println(space + name + "运行  :  " + count.addAndGet(1));
			}
		}
	}
	
//	public static void main(String args[]){
//
//		//同一个线程实例
//		RunnableThread out = new RunnableThread();
//		ThreadA thread = out.new ThreadA();
//		new Thread(thread,"thread_A").start();
//		new Thread(thread,"thread_B").start();
//	}
	
	class ThreadB implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < NUM; i++) {
			    try {
	                Thread.sleep((int) Math.random() * 10);
			    } catch (InterruptedException e) {
	                e.printStackTrace();
			    }
			    String space = "	";
			    if(i%2 == 0){
			       space = "";
			    }
			    String name = Thread.currentThread().getName();
			    if("thread_A".equals(name)){
				   count_a.addAndGet(1);
			    }
			    if("thread_B".equals(name)){
				   count_b.addAndGet(1);
			    }
			    if(i == NUM -1){
				   if("thread_A".equals(name)){
					   System.out.println("FINISH," + name + "运行  :  " +count_a);
				   }
				   if("thread_B".equals(name)){
					   System.out.println("FINISH," + name + "运行  :  " +count_b);
				   }
			    }
			    System.out.println(space + name + "运行  :  " + normal_count++);
			}
		}
	}
	
	public static void main(String args[]){

		//同一个线程实例
		RunnableThread out = new RunnableThread();
		ThreadB thread = out.new ThreadB();
		new Thread(thread,"thread_A").start();
		new Thread(thread,"thread_B").start();
	}
	
	class ThreadC extends Thread {
		
		private String name;
		
		public ThreadC(String name) {
		       this.name = name;
		}

		@Override
		public void run() {
			for (int i = 0; i < 1000; i++) {
				String space = "	";
				if(i%2 == 0){
					space = "";
				}
	            System.out.println(space+name + "运行  :  " + i);
	            try {
	                sleep((int) Math.random() * 10);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
			}
		}
	}
}






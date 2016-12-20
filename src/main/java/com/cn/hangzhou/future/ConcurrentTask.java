package com.cn.hangzhou.future;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 多线程尝试执行同一个任务，只有一个线程获得执行的机会
 * https://my.oschina.net/mononite/blog/144329
 * @author smile
 * 
 * 	private final ConcurrentMap<String, Future<ExpensiveObj>> cache = new ConcurrentHashMap<>();

	public ExpensiveObj get(final String key) {
	    Future<ExpensiveObj> future = cache.get(key);
	    if (future == null) {
	        Callable<ExpensiveObj> callable = new Callable<ExpensiveObj>() {
	            @Override
	            public ExpensiveObj call() throws Exception {
	                return new ExpensiveObj(key); //昂贵的成本
	            }
	        };
	        FutureTask<ExpensiveObj> task = new FutureTask<>(callable);
	
	        future = cache.putIfAbsent(key, task);
	        if (future == null) {
	            future = task;
	            task.run();
	        }
	    }
	
	    try {
	        return future.get();
	    } catch (Exception e) {
	        cache.remove(key);
	        throw new RuntimeException(e);
	    }
	}
 *
 */
public class ConcurrentTask {

    private final ConcurrentMap<Object, Future<String>> taskCache = new ConcurrentHashMap<Object, Future<String>>();
    
    static CyclicBarrier start = new CyclicBarrier(11);

    private String executionTask(final String taskName) throws ExecutionException, InterruptedException {
        while (true) {
            Future<String> future = taskCache.get(taskName); //1.1,2.1
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    public String call() throws InterruptedException {
                        //......
                    	TimeUnit.SECONDS.sleep(2);//昂贵成本的任务，任务只会执行一次
                        System.out.println(Thread.currentThread().getName()+"@"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        return taskName;
                    }
                };
                //1.2创建任务
                FutureTask<String> futureTask = new FutureTask<String>(task);
                future = taskCache.putIfAbsent(taskName, futureTask); //1.3 //线程安全
                System.out.println(future);
                if (future == null) {
                    future = futureTask;
                    futureTask.run(); //1.4执行任务
                }
            }

            try {
                return future.get(); //1.5,2.2线程在此等待任务执行完成
            } catch (CancellationException e) {
                taskCache.remove(taskName, future);
            }
        }
    }
    
    public static void main(String args[]){

		final ConcurrentTask concurrentTask = new ConcurrentTask();
		for(int i = 0; i < 10; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						start.await();
						System.out.println(concurrentTask.executionTask("test"));
					} catch (ExecutionException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		try {
			start.await();//同时进行
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
    }
}


















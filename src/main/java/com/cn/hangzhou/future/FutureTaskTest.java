package com.cn.hangzhou.future;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureTaskTest {

	private static final int       LOOP_COUNT = 5;

 	private static final int       THREAD_COUNT = 2;
 	
    private static List<Future<String>> fuList = new ArrayList<Future<String>>();

    private static ExecutorService threadPool   = Executors.newFixedThreadPool(THREAD_COUNT);
     
    public static void main(String[] args) {
    	
        for (int i = 0; i < LOOP_COUNT; i++) {
        	Future<String> future = threadPool.submit(new Callable<String>() {
                public String call() throws Exception {
               	
                	System.out.println("starting #"+Thread.currentThread().getName()+"@"
                			+new SimpleDateFormat("HH:mm:ss").format(new Date()));
               	
                	TimeUnit.SECONDS.sleep(2);
                	
                	String str = "ended #"+Thread.currentThread().getName()+"@"
                			+new SimpleDateFormat("HH:mm:ss").format(new Date());
//                	System.out.println(str);
                	
                   return str;
                }
            });
            fuList.add(future);
        }
        
        for (Future<String> future : fuList) {
        	try {
				future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
        
        System.out.println(1);
        System.out.println(2);

    }
}

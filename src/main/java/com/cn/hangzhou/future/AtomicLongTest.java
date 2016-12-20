package com.cn.hangzhou.future;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 
 * @author smile
 *
 */
public class AtomicLongTest {

	private final ConcurrentMap<String, AtomicLong> wordCounts = new ConcurrentHashMap<>();

	public long increase(String word) {
	    AtomicLong number = wordCounts.get(word);
	    if (number == null) {
	        AtomicLong newNumber = new AtomicLong(0); //多线程下，一个key可能会多次执行
	        number = wordCounts.putIfAbsent(word, newNumber);
	        System.out.println(number);
	        if (number == null) {
	            number = newNumber;
	        }
	    }
	    return number.incrementAndGet();
	}
    
    public static void main(String args[]){
    	
		final AtomicLongTest concurrentTask = new AtomicLongTest();
		for(int i = 0; i < 20; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(concurrentTask.increase("test"));
				}
			}).start();
		}
    }
}


















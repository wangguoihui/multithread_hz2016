package com.cn.hangzhou;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class ReadWriteLock {
    private static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static final Lock                   r   = rwl.readLock();
    private static final Lock                   w   = rwl.writeLock();
    private static final int         thread_count   = 10;

    public static final void get() {
        r.lock();
        try {
            try {
                System.out.println(Thread.currentThread().getName()+"@"
                		+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        } finally {
            r.unlock();
        }
    }

    public static final void put() {
        w.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"@"
            		+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
			TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
            w.unlock();
        }
    }
    
    public static void main(String args[]){
    	for(int i = 0; i < thread_count; i++){
    		new Thread(new Runnable() {
				@Override
				public void run() {
					get();
				}
			},"readLock"+i).start();
    		
    		new Thread(new Runnable() {
				@Override
				public void run() {
					put();
				}
			},"writeLock"+i).start();
    	}
    }
}

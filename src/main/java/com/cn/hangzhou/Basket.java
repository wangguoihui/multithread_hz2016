package com.cn.hangzhou;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * http://blog.csdn.net/lingzhm/article/details/47864857
 * 基于锁机制的生产者消费者模式
 * @author Administrator
 *
 */

public class Basket {
	
	public static Integer max = 1;
    LinkedList<Integer> manTous = new LinkedList<Integer>();  
	Lock lock = new ReentrantLock();
    Condition full = lock.newCondition(); 
    Condition empty = lock.newCondition();
	
    public void push(Integer num) {
    	 lock.lock();  
         try {  
             while(max == manTous.size()){  
                 System.out.println("篮子是满的，待会儿再生产...");  
                 full.await();  
             }  
             manTous.add(num);  
             empty.signal();  
         } catch (InterruptedException e) {  
             e.printStackTrace();  
         }finally{  
             lock.unlock();  
         }  
    }
    
	public Integer pop(){  
        Integer num = 0;
        lock.lock();  
        try {  
            while(manTous.size() == 0){  
                System.out.println("篮子是空的，待会儿再吃...");  
                empty.await();  
            }  
            num = manTous.removeFirst();  
            full.signal();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }finally{  
            lock.unlock();     
        }  
        return num;
    }  
    
    //生产者  
    class Product implements Runnable {  
        Basket basket; 
        public Product(Basket basket) {  
            this.basket = basket;  
        }  
        public void run() {  
            for (int i = 0; ; i++) {  
                basket.push(i);  
                System.out.println("生产了"+i);  
                try {  
                    Thread.sleep((int)(Math.random()*2000));  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    } 
    
    //消费者  
    class Consumer implements Runnable {  
        Basket basket;  
        public Consumer(Basket basket) {  
            this.basket = basket;  
        }  
        public void run() {  
            for ( ; ; ) {  
                try {  
                    Thread.sleep((int)(Math.random()*2000));  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
                Integer m = basket.pop();  
                System.out.println("消费了"+m);  
            }  
        }  
    } 
    
    public static void main(String[] args) {  
        Basket basket = new Basket();  
        Product p = basket.new Product(basket);  
        Consumer c = basket.new Consumer(basket);  
        Consumer c1 = basket.new Consumer(basket);  
        new Thread(p).start();  
        new Thread(c).start();  
        new Thread(c1).start();  
    }  
}













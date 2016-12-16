package com.cn.hangzhou.support;

import java.util.Arrays;

/**
 * 	ArrayList 数组扩容
 	ArrayList的数组扩容是如何考虑溢出的?
	https://segmentfault.com/q/1010000006093623
	http://stackoverflow.com/questions/1647260/java-dynamic-array-sizes
	当数组要溢出的时候，就加上数组的1/2，然后和数组最大的常量进行比对，如果超出数组的最大size，
	就新申请一个Integer.MAX_VALUE的数组，然后把之前旧数组复制过来。
	其中最难理解的是>>1,其实相当于除以2。
 * @author Administrator
 *
 */
public class ArrayCopy {
	
	public static void main(String args[]){
		
		Object elementData[] = {1,2,3,4,5};
		int newCapacity = 5;
		System.out.println(elementData.length);
		elementData = Arrays.copyOf(elementData, (newCapacity<<1));
		for (Object object : elementData) {
			System.out.println(object);
		}
		System.out.println(elementData.length);
        
        
        int oldItems[] = new int[10];
        for (int i=0; i<10; i++) {
          oldItems[i] = i+10;
        }
        for (Object object : oldItems) {
			System.out.println(object);
		}
        int newItems[] = new int[20];
        System.arraycopy(oldItems, 0, newItems, 0, 10);
        oldItems = newItems;
        
        System.out.println("size: "+oldItems.length);
        for (Object object : newItems) {
			System.out.println(object);
		}
	}

}

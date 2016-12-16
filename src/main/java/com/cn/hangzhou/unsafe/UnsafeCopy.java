package com.cn.hangzhou.unsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布-逃逸
 * @author Administrator
 *
 */
public class UnsafeCopy {
	
	private List<String> array;
	
	public List<String> setArray(List<String> array) {
		return this.array = array;
	}
	
	public static void main(String args[]){
		UnsafeCopy unsafeCopy = new UnsafeCopy();
		List<String> array = new ArrayList<String>(){
			
			private static final long serialVersionUID = 1L;
			{
//				add("sarah");
//				add("rihana");
			}
		};
		unsafeCopy.setArray(array);
		System.out.println(unsafeCopy.array);
	}
}

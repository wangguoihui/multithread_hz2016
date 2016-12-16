package com.cn.hangzhou.guava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.HashMultiset;

public class MultisetTest {

	List<String> tickets = new ArrayList<String>(){
		private static final long serialVersionUID = 1L;
		{
			add("123");
			add("456");
			add("789");
			add("123");
		}
	};
	
	public static void main(String args[]){
		new MultisetTest().multiSet();
		new MultisetTest().normal();
	}
	
	public void multiSet(){
		HashMultiset<String> multiSet = HashMultiset.create(); 
		multiSet.addAll(tickets); 
		System.out.println("count: "+multiSet.count("123"));
	}
	
	public void normal(){
		Map<String,Integer> map = new HashMap<String,Integer>();
		for (String key : tickets) {
		    Integer count = map.get(key); 
			map.put(key, count == null ? 1 : count+1);
		}
		System.out.println("count: "+map.get("123"));
	}
}














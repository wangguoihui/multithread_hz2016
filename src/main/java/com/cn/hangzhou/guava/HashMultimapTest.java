package com.cn.hangzhou.guava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import com.cn.hangzhou.model.Ticket;
import com.google.common.collect.HashMultimap;

/**
 * 	Map<String,String> hmap = new HashMap<String,String>();
		for (Map.Entry<String, String> entry : hmap.entrySet()) {
		System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
	}
	guava api:  https://google.github.io/guava/releases/18.0/api/docs/
				https://github.com/google/
				https://developers.google.com/?hl=zh-cn
 * @author Administrator
 *
 */
public class HashMultimapTest {

	List<Ticket> tickets = new ArrayList<Ticket>(){
		
		private static final long serialVersionUID = 1L;
		{
			Ticket ticket1 = new Ticket();
			ticket1.setCandidate("candicate0");
			ticket1.setVoter("voter0");
			add(ticket1);
			Ticket ticket2 = new Ticket();
			ticket2.setCandidate("candicate0");
			ticket2.setVoter("voter1");
			add(ticket2);
				
		}
	};
	
	public static void main(String args[]){
		new HashMultimapTest().multiSet();
		new HashMultimapTest().normal();
	}
	
	public void multiSet(){
		HashMultimap<String, String> map = HashMultimap.create(); 
		for(Ticket ticket: tickets) { 
		   map.put(ticket.getCandidate(), ticket.getVoter()); 
		}
		for(String key : map.keySet()) {
		    System.out.println(key+":"+map.get(key).size());
		}	
	}	
	
	public void normal(){
		//Key is candidate name, its value is his voters 
		HashMap<String, HashSet<String>> hMap = new HashMap<String, HashSet<String>>(); 
		for(Ticket ticket: tickets){ 
		   HashSet<String> set = hMap.get(ticket.getCandidate()); 
		   if(set == null){ 
		       set = new HashSet<String>(); 
		       hMap.put(ticket.getCandidate(), set); 
		   } 
		   set.add(ticket.getVoter()); 
		}
		for(String key : hMap.keySet()) {
		    System.out.println(key+":"+hMap.get(key).size());
		}	
	}
}





















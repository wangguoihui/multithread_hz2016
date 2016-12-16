package com.cn.hangzhou.sort;

import com.cn.hangzhou.model.Girl;

public class CompareTest {

	public static void main(String args[]){
		new CompareTest().test();
	}
	
	public void test(){
		Girl g1 = new Girl("lisa", 174.00, "nice");
	    Girl g2 = new Girl("lisa", 175.00, "beauty");
	    //两个girl的face不相同
	    System.out.println(g1.compareTo(g2) == 0);//false
	}
	
}

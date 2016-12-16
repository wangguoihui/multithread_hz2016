package com.cn.hangzhou.singleton;

/**
 * 
 */
	class SubClass extends SuperClass {
		static {
			System.out.println("subclass init");
		}
	}
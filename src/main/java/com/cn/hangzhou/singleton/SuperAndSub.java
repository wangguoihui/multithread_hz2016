package com.cn.hangzhou.singleton;

/**
 * 
 */
	class SuperAndSub {
		
		public static void main(String[] args) {
			System.out.println(SubClass.value);// 被动应用1
			SuperClass[] sca = new SubClass[10];// 被动引用2
			System.out.println(sca);
		}
	}
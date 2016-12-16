package com.cn.hangzhou.singleton;

/**
 * 类的加载时机和类的加载过程
 * 被动引用例子
 * 1、子类调用父类的静态变量，子类不会被初始化。只有父类被初始化。对于静态字段，只有直接定义这个字段的类才会被初始化.
 * 2、通过数组定义来引用类，不会触发类的初始化
 * 3、访问类的常量，不会初始化类
 */
	class SuperClass {
		static {
			System.out.println("superclass init");
		}
		public static int value = 123;
	}
	
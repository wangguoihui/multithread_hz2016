package com.cn.hangzhou.unsafe;

/**
 * 发布-逃逸
 * @author Administrator
 *
 */
public class UnsafeStates {

	private String[] states = new String[]{"AK","AL"};
	
	public String[] getStates() {
		return states;
	}
}

package com.cn.hangzhou.model;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;

public class Girl implements Comparable<Girl> {

	private String name;
	 
	private double height;
	 
	private String face;
	
	public Girl(String name, double height, String face) {
        this.name = name;
        this.height = height;
        this.face = face;
    }
	
	public String getName() {
			return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

    //传统方法我们这样比较
//	@Override
//	public int compareTo(Girl girl) {
//		int c1 = name.compareTo(girl.name);
//        if (c1 != 0){
//            System.out.println("两个girl的name不相同");
//            return c1;
//        }
//        int c2 = Double.compare(height, girl.height);
//        if (c2 != 0){
//            System.out.println("两个girl的height不相同");
//            return c2;
//        }
//        int c3 = face.compareTo(girl.face);
//        if(c3 !=0)
//            System.out.println("两个girl的face不相同");
//        return c3;
//	}
	
	//使用Guava提供的ComparisonChain我们这样比较
	@Override
	public int compareTo(Girl girl) {
	    return ComparisonChain.start()
	            .compare(name, girl.name)
	            .compare(height, girl.height, new Comparator<Double>(){
					@Override
					public int compare(Double o1, Double o2) {
						int c2 = Double.compare(o1, o2);
				        if (c2 != 0){
				            System.out.println("两个girl的height不相同");
				        }
				        return c2;
					}})
	            .compare(face, girl.face, new Comparator<String>(){
					@Override
					public int compare(String o1, String o2) {
						int c3 = o1.compareTo(o2);
				        if(c3 !=0){
				            System.out.println("两个girl的face不相同");
				        }
						return c3;
					}})
	            .result();
	}
}


















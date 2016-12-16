package com.cn.hangzhou.sort;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUtil {

	public void arraySort(File[] files){
		Arrays.sort(files,new Comparator<File>() {
			@Override
			public int compare(File pre, File beh) {
				if(pre.lastModified() > beh.lastModified()){
					return 1;
				}else if(pre.lastModified() < beh.lastModified()){
					return -1;
				}else{
					return 0;
				}
			}   
		});
	}
	
	public void listSort(List<File> files){
		Collections.sort(files, new Comparator<File>(){    
		    @Override    
		    public int compare(File f1, File f2) {    
		    	if(f1.lastModified() > f2.lastModified()){
					return 1;
				}else if(f1.lastModified() < f2.lastModified()){
					return -1;
				}else{
					return 0;
				}
		}});
	}
}

package com.cn.hangzhou.guava;

import java.awt.RenderingHints.Key;
import java.util.concurrent.TimeUnit;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sun.corba.se.impl.orbutil.graph.Graph;

public class GuavaCache {

	public static void main(String args[]){
		
		LoadingCache<Key, Graph> graphs = CacheBuilder.newBuilder()
				        .maximumSize(1000)
				        .expireAfterWrite(10, TimeUnit.MINUTES)
//				        .removalListener(MY_LISTENER)
				        .build(
				            new CacheLoader<Key, Graph>() {
				                public Graph load(Key key) {
				                    return null;
				                }
				        });

	}
}

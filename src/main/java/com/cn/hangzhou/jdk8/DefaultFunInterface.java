package com.cn.hangzhou.jdk8;

/**
 * Created by Administrator on 2016-12-16.
 */
public interface DefaultFunInterface {

    //默认方法 count
    default int count() {
        return 1;
    }
}

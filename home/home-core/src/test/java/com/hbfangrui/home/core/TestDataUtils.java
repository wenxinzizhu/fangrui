package com.hbfangrui.home.core;

import java.util.Random;

/**
 * Created by tao.li on 2016/4/29.
 */
public abstract class TestDataUtils {
    public static String randomString(){
        return String.valueOf(new Random().nextLong());
    }

}

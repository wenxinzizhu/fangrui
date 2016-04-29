package com.hbfangrui.home.web.util;


/**
 * Created by tao.li on 2016/4/23.
 */
public class ResourcesUtil {
    private ResourcesUtil(){}
    public static String getJsHref(String name){
        return getResourceHref(name);
    }

    public static String getCssHref(String name){
        return getResourceHref("css/" + name);
    }

    public static String getImageHref(String name){
        return getResourceHref(name);
    }

    public static String getFileHref(String dataPath) {
        return getResourceHref(dataPath);
    }

    private static String getResourceHref(String name) {
        if (name.startsWith("http")){
            return name;
        }
        if (name.startsWith("/resources")) {
            return name;
        } else if (name.startsWith("/")) {
            return "/resources" + name;
        }else {
            return "/resources/" + name;
        }
    }
}

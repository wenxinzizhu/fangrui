package com.hbfangrui.user.base.model;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by taoli on 15/10/30.
 */
public abstract class UserChecker {

    public static void checkUserId(UserId userId){
        Preconditions.checkArgument(userId != null, "user id can not be null");
    }
    public static void checkUserName(String name){
        Preconditions.checkArgument(StringUtils.isNotEmpty(name), "user name can not be null");
    }
    public static void checkPassword(String password){
        Preconditions.checkArgument(StringUtils.isNotEmpty(password), "password can not be null");
    }
    public static void checkAvatar(String avatar){
        Preconditions.checkArgument(StringUtils.isNotEmpty(avatar), "avatar can not be null");
    }

    public static void checkGender(Gender gender){
        Preconditions.checkArgument(gender != null, "gender can not be null");
    }
}

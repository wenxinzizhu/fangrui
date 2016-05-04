package com.hbfangrui.user.base.model;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.ddd.domain.model.support.Email;

/**
 * Created by taoli on 15/11/1.
 */
public class UserEmailsChecker {
    public static void checkOwner(UserId owner){
        Preconditions.checkArgument(owner != null, "owner can not be null");
    }

    public static void checkEmail(Email email){
        Preconditions.checkArgument(email != null, "email can not be null");
    }

    public static void checkStatus(UserEmailStatus status){
        Preconditions.checkArgument(status != null, "status can not be null");
    }
}

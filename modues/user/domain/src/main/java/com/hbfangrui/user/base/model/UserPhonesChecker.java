package com.hbfangrui.user.base.model;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.ddd.domain.model.support.Phone;

/**
 * Created by taoli on 15/11/1.
 */
public class UserPhonesChecker {
    public static void checkOwner(UserId owner){
        Preconditions.checkArgument(owner != null, "owner can not be null");
    }

    public static void checkPhone(Phone phone){
        Preconditions.checkArgument(phone != null, "phone can not be null");
    }

    public static void checkStatus(UserPhoneStatus status){
        Preconditions.checkArgument(status != null, "status can not be null");
    }
}

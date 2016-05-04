package com.hbfangrui.user.command.domain.exception;

import com.hbfangrui.base.ddd.domain.exception.BusinessException;
import com.hbfangrui.user.command.domain.model.UserPhone;

/**
 * Created by taoli on 15/11/1.
 */
public class PhoneIsDefaultException extends BusinessException {
    public PhoneIsDefaultException(UserPhone userPhone) {
        super(userPhone.getPhone() + " is default for " + userPhone.getOwner());
    }
}

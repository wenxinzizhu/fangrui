package com.hbfangrui.user.command.domain.exception;


import com.hbfangrui.base.ddd.domain.exception.BusinessException;
import com.hbfangrui.base.ddd.domain.model.support.Phone;

/**
 * Created by taoli on 15/11/1.
 */
public class PhoneExistedException extends BusinessException {
    public PhoneExistedException(Phone phone) {
        super(phone.toString() + " existed");
    }
}

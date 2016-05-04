package com.hbfangrui.user.command.domain.exception;

import com.hbfangrui.base.ddd.domain.exception.BusinessException;
import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;

/**
 * Created by taoli on 15/11/1.
 */
public class PhoneActivatedByOtherException extends BusinessException {
    public PhoneActivatedByOtherException(Phone phone, UserId other) {
        super(phone + " activated by " + other);
    }
}

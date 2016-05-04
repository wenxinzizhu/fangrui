package com.hbfangrui.user.command.domain.exception;

import com.hbfangrui.base.ddd.domain.exception.BusinessException;
import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;

/**
 * Created by taoli on 15/11/1.
 */
public class EmailActivatedByOtherException extends BusinessException {
    public EmailActivatedByOtherException(Email email, UserId other) {
        super(email + " activated by " + other);
    }
}

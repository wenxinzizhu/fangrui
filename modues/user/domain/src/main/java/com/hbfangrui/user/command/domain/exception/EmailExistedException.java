package com.hbfangrui.user.command.domain.exception;


import com.hbfangrui.base.ddd.domain.exception.BusinessException;
import com.hbfangrui.base.ddd.domain.model.support.Email;

/**
 * Created by taoli on 15/11/1.
 */
public class EmailExistedException extends BusinessException {
    public EmailExistedException(Email email) {
        super(email.toString() + " existed");
    }
}

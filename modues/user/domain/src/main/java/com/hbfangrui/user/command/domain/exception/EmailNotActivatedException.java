package com.hbfangrui.user.command.domain.exception;


import com.hbfangrui.base.ddd.domain.exception.BusinessException;
import com.hbfangrui.base.ddd.domain.model.support.Email;

/**
 * Created by taoli on 15/11/1.
 */
public class EmailNotActivatedException extends BusinessException {
    public EmailNotActivatedException(Email email) {
        super("email " + email + " not activated");
    }
}

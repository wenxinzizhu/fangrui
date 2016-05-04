package com.hbfangrui.user.command.domain.exception;

import com.hbfangrui.base.ddd.domain.exception.BusinessException;
import com.hbfangrui.user.command.domain.model.UserEmail;

/**
 * Created by taoli on 15/11/1.
 */
public class EmailIsDefaultException extends BusinessException {
    public EmailIsDefaultException(UserEmail userEmail) {
        super(userEmail.getEmail() + " is default for " + userEmail.getOwner());
    }
}

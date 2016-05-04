package com.hbfangrui.user.command.domain.exception;

import com.hbfangrui.base.ddd.domain.exception.BusinessException;
import com.hbfangrui.user.base.model.UserId;

/**
 * Created by taoli on 15/10/31.
 */
public class UserNotExistException extends BusinessException {
    public UserNotExistException(UserId userId){
        super("User " + userId.getId() + " Not Found" );
    }
}

package com.hbfangrui.user.command.domain.model;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserEmailStatus;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserEmailBase;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by taoli on 15/11/1.
 */
@Entity
@Table(name = "t_user_email")
public class UserEmail extends UserEmailBase{
    private UserEmail(){

    }

    private UserEmail(UserId owner, Email email, UserEmailStatus status){
        setOwner(owner);
        setEmail(email);
        setStatus(status);
    }

    protected static UserEmail apply(UserId owner, Email email, UserEmailStatus status) {
        return new UserEmail(owner, email, status);
    }

    public void active() {
        setStatus(getStatus().active(true));
    }
}

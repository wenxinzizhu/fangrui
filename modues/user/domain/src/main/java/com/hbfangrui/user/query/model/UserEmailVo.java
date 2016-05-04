package com.hbfangrui.user.query.model;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserEmailStatus;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserEmailBase;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by taoli on 15/11/4.
 */
@Entity
@Table(name = "t_user_email")
public class UserEmailVo extends UserEmailBase
    implements Serializable{

    public UserEmailVo(UserId userId, Email email, UserEmailStatus status) {
        setOwner(userId);
        setEmail(email);
        setStatus(status);
    }

    public static  UserEmailVo apply(UserId userId, Email email, UserEmailStatus status) {
        return new UserEmailVo(userId, email, status);
    }
}

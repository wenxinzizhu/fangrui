package com.hbfangrui.user.command.domain.model;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserPhoneBase;
import com.hbfangrui.user.base.model.UserPhoneStatus;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by taoli on 15/11/1.
 */
@Entity
@Table(name = "t_user_phone")
public class UserPhone extends UserPhoneBase {
    private UserPhone(){

    }

    private UserPhone(UserId owner, Phone phone, UserPhoneStatus status){
        setOwner(owner);
        setPhone(phone);
        setStatus(status);
    }

    protected static UserPhone apply(UserId owner, Phone phone, UserPhoneStatus status) {
        return new UserPhone(owner, phone, status);
    }

    public void active() {
        setStatus(getStatus().active(true));
    }
}

package com.hbfangrui.user.query.model;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserPhoneBase;
import com.hbfangrui.user.base.model.UserPhoneStatus;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by taoli on 15/11/4.
 */
@Entity
@Table(name = "t_user_phone")
public class UserPhoneVo extends UserPhoneBase
    implements Serializable{

    public UserPhoneVo(UserId userId, Phone phone, UserPhoneStatus status) {
        setOwner(userId);
        setPhone(phone);
        setStatus(status);
    }

    public static UserPhoneVo apply(UserId userId, Phone phone, UserPhoneStatus status) {
        return new UserPhoneVo(userId, phone, status);
    }
}

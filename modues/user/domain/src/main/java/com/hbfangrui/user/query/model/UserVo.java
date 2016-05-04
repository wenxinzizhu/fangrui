package com.hbfangrui.user.query.model;

import com.hbfangrui.user.base.model.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by tao.li on 2015/10/30.
 */
@Entity
@Table(name = "t_user")
public class UserVo extends UserBase {
    private UserVo(){

    }

    private UserVo(UserId userId, UserStatus userStatus, Gender gender, String name, UserPassword password, String avatar){
        setId(userId);
        setStatus(userStatus);
        setGender(gender);
        setName(name);
        setPassword(password);
        setAvatar(avatar);
    }

    public static UserVo apply(UserId userId, UserStatus userStatus, Gender gender, String name, UserPassword password, String avatar){
        return new UserVo(userId, userStatus, gender, name, password, avatar);
    }
}

package com.hbfangrui.user.command.domain.event.user;

import com.hbfangrui.user.base.model.Gender;
import com.hbfangrui.user.base.model.UserStatus;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserPassword;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by taoli on 15/10/30.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserCreatedEvent extends UserEvent {
    private UserStatus userStatus;
    private Gender gender;
    private String name;
    private UserPassword password;
    private String avatar;

    private UserCreatedEvent(UserId userId, int version, Gender gender,  UserStatus userStatus, String name, UserPassword password, String avatar) {
        super(userId, version);
        this.gender = gender;
        this.userStatus = userStatus;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
    }

    public static UserCreatedEvent apply(UserId userId, int version, Gender gender, UserStatus userStatus, String name, UserPassword password, String avatar){
        return new UserCreatedEvent(userId, version, gender, userStatus, name, password, avatar);
    }
}

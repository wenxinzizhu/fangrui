package com.hbfangrui.user.command.domain.event.user;

import com.hbfangrui.user.base.model.Gender;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by taoli on 15/10/31.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserChangedEvent extends UserEvent {
    private Gender gender;
    private String name;
    private String avatar;
    public UserChangedEvent(UserId id, int version, Gender gender,  String name, String avatar) {
        super(id, version);
        this.gender = gender;
        this.name = name;
        this.avatar = avatar;
    }

    public static UserChangedEvent apply(UserId userId, int version, Gender gender, String name, String avatar){
        return new UserChangedEvent(userId, version, gender, name, avatar);
    }
}

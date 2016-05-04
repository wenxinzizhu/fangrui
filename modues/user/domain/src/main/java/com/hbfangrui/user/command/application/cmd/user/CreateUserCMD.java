package com.hbfangrui.user.command.application.cmd.user;

import com.hbfangrui.user.base.model.Gender;
import com.hbfangrui.user.base.model.UserChecker;
import com.hbfangrui.user.base.model.UserId;
import lombok.Value;

/**
 * Created by taoli on 15/10/30.
 */
@Value
public class CreateUserCMD extends UserCMD{
    private final Gender gender;
    private final String name;
    private final String pwd;
    private final String avatar;

    private CreateUserCMD(UserId id, Gender gender, String name, String pwd, String avatar){
        super(id);
        UserChecker.checkGender(gender);
        UserChecker.checkUserName(name);
        UserChecker.checkPassword(pwd);
        UserChecker.checkAvatar(avatar);

        this.gender = gender;
        this.name = name;
        this.pwd = pwd;
        this.avatar = avatar;
    }

    public static CreateUserCMD apply(UserId userId, Gender gender, String name, String pwd, String avatar){
        return new CreateUserCMD(userId, gender, name, pwd, avatar);
    }

}

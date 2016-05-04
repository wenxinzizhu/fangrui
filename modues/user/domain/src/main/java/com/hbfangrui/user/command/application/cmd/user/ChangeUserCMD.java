package com.hbfangrui.user.command.application.cmd.user;

import com.hbfangrui.user.base.model.Gender;
import com.hbfangrui.user.base.model.UserChecker;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

import java.util.Optional;

/**
 * Created by taoli on 15/10/30.
 */
@Data
public class ChangeUserCMD extends UserCMD{
    private Optional<Gender> gender;
    private Optional<String> name;
    private Optional<String> avatar;

    private ChangeUserCMD(UserId userId){
        super(userId);
    }

    private void setName(Optional<String> name){
        this.name = name;
    }

    private void setAvatar(Optional<String> avatar){
        this.avatar = avatar;
    }

    public static ChangeUserCMDBuilder builder(UserId userId){
        return new ChangeUserCMDBuilder(userId);
    }

    public static class ChangeUserCMDBuilder{
        private ChangeUserCMD cmd;
        private ChangeUserCMDBuilder(UserId userId){
            UserChecker.checkUserId(userId);
            this.cmd = new ChangeUserCMD(userId);
        }

        public ChangeUserCMDBuilder name(String name){
            UserChecker.checkUserName(name);
            this.cmd.setName(Optional.of(name));
            return this;
        }

        public ChangeUserCMDBuilder avatar(String avatar){
            UserChecker.checkAvatar(avatar);
            this.cmd.setAvatar(Optional.of(avatar));
            return this;
        }

        public ChangeUserCMDBuilder gender(Gender gender){
            UserChecker.checkGender(gender);
            this.cmd.setGender(Optional.of(gender));
            return this;
        }

        public ChangeUserCMD build(){
            return this.cmd;
        }
    }
}

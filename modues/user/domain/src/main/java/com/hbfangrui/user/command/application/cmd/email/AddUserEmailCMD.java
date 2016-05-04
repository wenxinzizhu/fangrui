package com.hbfangrui.user.command.application.cmd.email;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * Created by taoli on 15/11/1.
 */
@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AddUserEmailCMD extends UserEmailCMD{
    private AddUserEmailCMD(UserId userId, Email email) {
        super(userId, email);
    }

    public static AddUserEmailCMD apply(UserId owner, Email email) {
        return new AddUserEmailCMD(owner, email);
    }

    public static AddUserEmailCMD apply(UserId owner, String email){
        return apply(owner, Email.apply(email));
    }
}

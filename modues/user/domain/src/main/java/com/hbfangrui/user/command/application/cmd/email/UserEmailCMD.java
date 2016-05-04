package com.hbfangrui.user.command.application.cmd.email;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.cqrs.application.cmd.AbstractDomainCommand;
import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/1.
 */
@Data
public abstract class UserEmailCMD extends AbstractDomainCommand<UserId> {
    private Email email;
    protected UserEmailCMD(UserId userId, Email email) {
        super(userId);
        Preconditions.checkArgument(email!=null, "email can not be null");
        this.email = email;
    }
}

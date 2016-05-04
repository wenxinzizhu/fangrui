package com.hbfangrui.user.command.application.cmd.phone;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.cqrs.application.cmd.AbstractDomainCommand;
import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/11/14.
 */
@Data
public abstract class UserPhoneCMD extends AbstractDomainCommand<UserId> {
    private final Phone phone;
    protected UserPhoneCMD(UserId userId, Phone phone) {
        super(userId);
        Preconditions.checkArgument(phone != null, "phone can not be null");
        this.phone = phone;
    }
}

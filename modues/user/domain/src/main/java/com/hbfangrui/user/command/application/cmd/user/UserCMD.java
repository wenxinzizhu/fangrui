package com.hbfangrui.user.command.application.cmd.user;

import com.hbfangrui.base.cqrs.application.cmd.AbstractDomainCommand;
import com.hbfangrui.user.base.model.UserChecker;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

/**
 * Created by taoli on 15/10/31.
 */
@Data
abstract class UserCMD extends AbstractDomainCommand<UserId> {
    protected UserCMD(UserId userId){
        super(userId);
        UserChecker.checkUserId(userId);
    }
}

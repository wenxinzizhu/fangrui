package com.hbfangrui.user.command.domain.event.phone;

import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.event.support.AbstractDomainEvent;
import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import lombok.Data;

import static com.hbfangrui.user.base.model.UserPhonesChecker.checkOwner;
import static com.hbfangrui.user.base.model.UserPhonesChecker.checkPhone;


/**
 * Created by taoli on 15/11/1.
 */
@Data
public abstract class UserPhonesEvent extends AbstractDomainEvent<UserId>
    implements DomainEvent<UserId> {
    private final UserId id;
    private final Phone phone;
    public UserPhonesEvent(UserId id, int version, Phone phone) {
        super(id, version);
        checkOwner(id);
        checkPhone(phone);
        this.id = id;
        this.phone = phone;
    }
}

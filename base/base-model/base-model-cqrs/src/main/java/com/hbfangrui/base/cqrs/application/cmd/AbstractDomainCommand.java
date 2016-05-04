package com.hbfangrui.base.cqrs.application.cmd;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.ddd.domain.model.Identifier;
import com.hbfangrui.base.ddd.domain.model.support.AbstractValueObject;

/**
 * Created by taoli on 15/10/31.
 */
public abstract class AbstractDomainCommand<ID extends Identifier>
        extends AbstractValueObject
        implements DomainCommand<ID>{
    private final ID id;

    public AbstractDomainCommand(ID id) {
        Preconditions.checkArgument(id != null, "id can not be null");
        this.id = id;
    }

    public ID getId() {
        return this.id;
    }
}

package com.hbfangrui.base.ddd.domain.model.event.support;

import com.hbfangrui.base.ddd.domain.model.Identifier;
import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import com.hbfangrui.base.ddd.domain.model.support.AbstractValueObject;
import lombok.Data;

import java.util.Date;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by tao.li on 2015/10/26.
 */
@Data
public abstract class AbstractDomainEvent<ID extends Identifier> extends AbstractValueObject implements DomainEvent<ID> {
    private ID id;
    private int version;
    private Date occurredTime;

    private AbstractDomainEvent() {
    }

    protected AbstractDomainEvent(ID id, int version) {
        this(id, version, new Date());
    }

    protected AbstractDomainEvent(ID id, int version, Date occurredTime) {
        setId(id);
        setVersion(version);
        setOccurredTime(occurredTime);
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public int eventVersion() {
        return this.version;
    }

    @Override
    public Date occurredOn() {
        return this.occurredTime;
    }

    private void setVersion(int version) {
        checkArgument(version >= 0, "version must gt 0");
        this.version = version;
    }

    private void setOccurredTime(Date date) {
        checkArgument(date != null, "occurred time can not be null");
        this.occurredTime = date;
    }

    private void setId(ID id) {
        checkArgument(id != null, "id can not be null");
        this.id = id;
    }
}

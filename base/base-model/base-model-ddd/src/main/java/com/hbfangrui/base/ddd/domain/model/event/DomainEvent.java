package com.hbfangrui.base.ddd.domain.model.event;

import com.hbfangrui.base.ddd.domain.model.Identifier;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tao.li on 2015/10/26.
 */
public interface DomainEvent<ID extends Identifier> extends Serializable{
    ID getId();

    Date occurredOn();

    int eventVersion();
}

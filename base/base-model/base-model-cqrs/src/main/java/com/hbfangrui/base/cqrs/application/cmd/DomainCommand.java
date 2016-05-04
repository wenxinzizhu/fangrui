package com.hbfangrui.base.cqrs.application.cmd;

import com.hbfangrui.base.ddd.domain.model.Identifier;

/**
 * Created by taoli on 15/10/31.
 */
public interface DomainCommand<ID extends Identifier> extends Command{
    ID getId();
}

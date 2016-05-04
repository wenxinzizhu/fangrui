package com.hbfangrui.base.cqrs.repository;

import com.hbfangrui.base.ddd.domain.model.Aggregate;
import com.hbfangrui.base.ddd.domain.model.Identifier;
import com.hbfangrui.base.ddd.domain.repository.Repository;

/**
 * Created by tao.li on 2016/5/4.
 */
public interface CommandRepository<ID extends Identifier, A extends Aggregate<ID>> extends Repository<ID, A> {
}

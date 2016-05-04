package com.hbfangrui.base.ddd.domain.model.es;

import com.hbfangrui.base.ddd.domain.model.Identifier;
import com.hbfangrui.base.ddd.domain.repository.Repository;

import java.util.Optional;

/**
 * Created by tao.li on 2015/10/26.
 */
public interface ESRepository<ID extends Identifier, A extends ESAggregate<ID>> extends Repository<ID,A>{
    Optional<A> load(ID id, int version);
}

package com.hbfangrui.base.ddd.domain.repository;

import com.hbfangrui.base.ddd.domain.model.Aggregate;
import com.hbfangrui.base.ddd.domain.model.Identifier;

import java.util.Optional;

/**
 * Created by tao.li on 2015/10/26.
 */
public interface Repository<ID extends Identifier, A extends Aggregate<ID>> {
    void save(A a);

    void remove(A a);

    void removeById(ID id);

    Optional<A> load(ID id);
}

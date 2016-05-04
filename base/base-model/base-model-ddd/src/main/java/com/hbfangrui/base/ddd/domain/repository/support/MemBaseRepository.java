package com.hbfangrui.base.ddd.domain.repository.support;

import com.google.common.collect.Maps;
import com.hbfangrui.base.ddd.domain.model.Aggregate;
import com.hbfangrui.base.ddd.domain.model.Identifier;
import com.hbfangrui.base.ddd.domain.repository.Repository;

import java.util.Map;
import java.util.Optional;

/**
 * Created by taoli on 15/10/31.
 */
public class MemBaseRepository<ID extends Identifier, A extends Aggregate<ID>> implements Repository<ID, A> {
    private final Map<ID, A> data = Maps.newHashMap();
    @Override
    public void save(A a) {
        this.data.put(a.getId(), a);
    }

    @Override
    public void remove(A a) {
        removeById(a.getId());
    }

    @Override
    public void removeById(ID id) {
        this.data.remove(id);
    }

    @Override
    public Optional<A> load(ID id) {
        A a = this.data.get(id);
        if (a != null){
            return Optional.of(a);
        }else {
            return Optional.empty();
        }
    }
    protected Map<ID, A> getData(){
        return this.data;
    }
}

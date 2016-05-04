package com.hbfangrui.base.infra.jpa.command;

import java.util.Optional;

/**
 * Created by taoli on 15/10/31.
 */
public abstract class JpaCommandRepository<ID, A> {
    public void save(A a) {
        getDao().save(a);
    }

    public void remove(A a) {
        getDao().delete(a);
    }

    public void removeById(ID id){
        Optional<A> a = load(id);
        if (a.isPresent()){
            remove(a.get());
        }
    }

    public Optional<A> load(ID id) {
        return this.getDao().getById(id);
    }

    protected abstract JpaCommandDao<ID, A> getDao();
}

package com.hbfangrui.base.infra.jpa.query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Created by taoli on 15/10/31.
 */
public abstract class SimpleJpaQueryRepositorySupport<ID, VO> {
    protected abstract JpaQueryDao<ID, VO> getDao();
    public void save(VO vo) {
        this.getDao().save(vo);
    }

    public CompletableFuture<Optional<VO>> getById(ID id) {
        return this.getDao().getById(id).thenApply(vo-> vo == null ? Optional.<VO>empty() : Optional.of(vo));
    }

    public CompletableFuture<List<VO>> getByIds(Collection<ID> ids) {
        return  this.getDao().getByIdIn(ids);
    }
}

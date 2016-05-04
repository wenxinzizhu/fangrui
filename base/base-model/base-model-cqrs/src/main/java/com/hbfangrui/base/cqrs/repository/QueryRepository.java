package com.hbfangrui.base.cqrs.repository;

import com.hbfangrui.base.ddd.domain.repository.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Created by taoli on 15/11/4.
 */
public interface QueryRepository<ID, VO> {
    void save(VO vo);

    CompletableFuture<Optional<VO>> getById(ID id);

    CompletableFuture<List<VO>> getByIds(Collection<ID> ids);
}

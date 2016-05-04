package com.hbfangrui.base.infra.jpa.query;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by taoli on 15/10/31.
 */
public interface JpaQueryDao<ID, A> extends JpaRepository<A, Long> {

    CompletableFuture<A> getById(ID id);


    CompletableFuture<List<A>> getByIdIn(Collection<ID> ids);
}

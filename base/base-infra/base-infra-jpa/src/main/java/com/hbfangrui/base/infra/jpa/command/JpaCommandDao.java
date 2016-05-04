package com.hbfangrui.base.infra.jpa.command;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * Created by taoli on 15/10/31.
 */
public interface JpaCommandDao<ID, A> extends JpaRepository<A, Long> {
    Optional<A> getById(ID id);
}

package com.hbfangrui.base.ddd.domain.model;

import java.util.function.Supplier;

/**
 * Created by taoli on 15/10/31.
 */
public interface IdentifierGenerator<ID extends Identifier> extends Supplier<ID> {
}

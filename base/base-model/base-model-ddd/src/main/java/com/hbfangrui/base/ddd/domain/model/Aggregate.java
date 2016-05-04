package com.hbfangrui.base.ddd.domain.model;

import java.io.Serializable;

/**
 * Created by tao.li on 2015/10/26.
 */
public interface Aggregate<ID extends Identifier> extends Serializable{
    ID getId();

    int getVersion();
}

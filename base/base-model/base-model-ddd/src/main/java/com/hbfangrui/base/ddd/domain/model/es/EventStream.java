package com.hbfangrui.base.ddd.domain.model.es;


import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;

import java.util.List;

/**
 * Created by tao.li on 2015/10/26.
 */
public interface EventStream {
    List<? extends DomainEvent> events();

    boolean isNotEmpty();
}

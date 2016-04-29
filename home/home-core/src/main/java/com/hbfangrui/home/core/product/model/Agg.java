package com.hbfangrui.home.core.product.model;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * Created by tao.li on 2016/4/29.
 */
public interface Agg<A extends Agg<A>> {
    void acceptCommand(Consumer<A> command);

    void acceptCommand(Collection<Consumer<A>> commands);
}

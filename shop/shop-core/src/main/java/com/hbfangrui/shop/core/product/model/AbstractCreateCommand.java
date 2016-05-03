package com.hbfangrui.shop.core.product.model;

/**
 * Created by tao.li on 2016/4/29.
 */
public abstract class AbstractCreateCommand<A extends Agg<A>, ID>
        extends AbstractCommand<A, ID>
        implements CreateCommand<A, ID>{

    public AbstractCreateCommand(ID id) {
        super(id);
    }

    abstract A createInstance(ID id);

    @Override
    public final A create() {
        A a = createInstance(getId());
        accept(a);
        return a;
    }
}

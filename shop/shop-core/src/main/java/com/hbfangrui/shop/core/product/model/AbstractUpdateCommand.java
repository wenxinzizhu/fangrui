package com.hbfangrui.shop.core.product.model;

/**
 * Created by tao.li on 2016/4/29.
 */
public abstract class AbstractUpdateCommand<A extends Agg<A>, ID>
        extends AbstractCommand<A, ID>
        implements UpdateCommand<A, ID> {

    protected AbstractUpdateCommand(ID id) {
        super(id);
    }
}

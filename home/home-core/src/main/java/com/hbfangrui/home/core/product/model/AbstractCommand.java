package com.hbfangrui.home.core.product.model;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by tao.li on 2016/4/29.
 */
abstract class AbstractCommand<A extends Agg<A>, ID>
        implements Command<A, ID> {
    private final List<Consumer<A>> commands = Lists.newArrayList();
    private final ID id;

    protected AbstractCommand(ID id) {
        this.id = id;
    }

    @Override
    public final void accept(A a) {
        a.acceptCommand(this.commands);
    }

    @Override
    public final ID getId() {
        return this.id;
    }

    public void addCommands(Consumer<A> command) {
        this.commands.add(command);
    }
}

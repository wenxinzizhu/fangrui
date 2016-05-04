package com.hbfangrui.base.ddd.domain.model.es.model;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.ddd.domain.model.es.ESAggregate;
import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tao.li on 2015/10/26.
 */
public class ESTestAggregate implements ESAggregate<ESTestAggregateId> {
    private final List<DomainEvent> events = new ArrayList<>();
    private ESTestAggregateId id;
    private boolean actived = false;
    private String name;

    @Override
    public ESTestAggregateId getId() {
        return id;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public void publish(DomainEvent event){
        this.events.add(event);
    }

    public void active(){
        publish(new ActivedEvent());
    }

    public void changeName(String name){
        Preconditions.checkArgument(StringUtils.isNotEmpty(name), "name can not be null");
        publish(new NameChangedEvent(name));
    }

    private void handle(ActivedEvent event){
        this.actived = true;
    }

    private void handle(NameChangedEvent event){
        this.name = event.getName();
    }

    public List<DomainEvent> getEvents(){
        return this.events;
    }

    public boolean isActived() {
        return this.actived;
    }

    public String getName() {
        return this.name;
    }
}

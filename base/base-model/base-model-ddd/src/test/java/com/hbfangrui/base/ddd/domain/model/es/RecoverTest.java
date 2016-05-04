package com.hbfangrui.base.ddd.domain.model.es;

import com.hbfangrui.base.ddd.domain.model.es.model.ActivedEvent;
import com.hbfangrui.base.ddd.domain.model.es.model.ESTestAggregate;
import com.hbfangrui.base.ddd.domain.model.es.model.NameChangedEvent;
import com.hbfangrui.base.ddd.domain.model.event.DomainEvent;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tao.li on 2015/10/26.
 */
public class RecoverTest {

    @Test
    public void testRecover(){
        {
            List<DomainEvent> events = new ArrayList<>();
            events.add(new ActivedEvent());
            ESTestAggregate testAggregate = new ESTestAggregate();
            testAggregate.recover(new TestEventStream(events));
            Assert.assertTrue(testAggregate.isActived());
        }
        {
            String name = "testName";
            ESTestAggregate testAggregate = new ESTestAggregate();
            testAggregate.recover(new TestEventStream(Arrays.asList(new NameChangedEvent(name))));
            Assert.assertEquals(name, testAggregate.getName());
        }

        {
            String name = "testName";
            ESTestAggregate testAggregate = new ESTestAggregate();
            testAggregate.recover(new TestEventStream(Arrays.asList(new ActivedEvent(), new NameChangedEvent(name))));
            Assert.assertTrue(testAggregate.isActived());
            Assert.assertEquals(name, testAggregate.getName());
        }
    }

    private class TestEventStream implements EventStream {
        private final List<? extends DomainEvent> events;

        private TestEventStream(List<? extends DomainEvent> events) {
            this.events = events;
        }

        @Override
        public List<? extends DomainEvent> events() {
            return events;
        }

        @Override
        public boolean isNotEmpty() {
            return !events.isEmpty();
        }
    }
}

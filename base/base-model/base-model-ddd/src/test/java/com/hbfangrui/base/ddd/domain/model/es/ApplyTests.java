package com.hbfangrui.base.ddd.domain.model.es;

import com.hbfangrui.base.ddd.domain.model.es.model.ActivedEvent;
import com.hbfangrui.base.ddd.domain.model.es.model.ESTestAggregate;
import com.hbfangrui.base.ddd.domain.model.es.model.NameChangedEvent;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by tao.li on 2015/10/26.
 */
public class ApplyTests {

    @Test
    public void activeTest(){
        ESTestAggregate ESTestAggregate = new ESTestAggregate();
        ESTestAggregate.active();
        Assert.assertTrue(ESTestAggregate.isActived());
        Assert.assertEquals(1, ESTestAggregate.getEvents().size());
        Assert.assertEquals(ActivedEvent.class, ESTestAggregate.getEvents().get(0).getClass());
    }

    @Test
    public void testChangeName(){
        String name = "name1";
        ESTestAggregate ESTestAggregate = new ESTestAggregate();
        ESTestAggregate.changeName(name);

        Assert.assertEquals(name, ESTestAggregate.getName());
        Assert.assertEquals(1, ESTestAggregate.getEvents().size());
        Assert.assertEquals(NameChangedEvent.class, ESTestAggregate.getEvents().get(0).getClass());
    }

    @Test
    public void test(){
        ESTestAggregate ESTestAggregate = new ESTestAggregate();
        ESTestAggregate.active();
        Assert.assertTrue(ESTestAggregate.isActived());
        Assert.assertEquals(1, ESTestAggregate.getEvents().size());
        Assert.assertEquals(ActivedEvent.class, ESTestAggregate.getEvents().get(0).getClass());

        String name = "name1";
        ESTestAggregate.changeName(name);
        Assert.assertEquals(name, ESTestAggregate.getName());
        Assert.assertEquals(2, ESTestAggregate.getEvents().size());
        Assert.assertEquals(NameChangedEvent.class, ESTestAggregate.getEvents().get(1).getClass());
    }
}

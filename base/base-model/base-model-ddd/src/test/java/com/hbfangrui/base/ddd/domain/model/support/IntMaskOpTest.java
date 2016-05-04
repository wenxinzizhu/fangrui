package com.hbfangrui.base.ddd.domain.model.support;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by tao.li on 2015/10/30.
 */
public class IntMaskOpTest {

    @Test
    public void testAll() throws Exception {
        IntMaskOp maskOp = IntMaskOp.MASK_1;
        Assert.assertFalse(maskOp.isSet(0));
        int value = maskOp.set(0);
        Assert.assertTrue(maskOp.isSet(value));
        int value1 = maskOp.unset(value);
        Assert.assertFalse(maskOp.isSet(value1));
    }

}
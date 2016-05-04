package com.hbfangrui.base.ddd.domain.model.support;

/**
 * Created by tao.li on 2015/10/30.
 */
public class LongMaskOp {
    private final long maskCode;

    public LongMaskOp(long maskCode) {
        this.maskCode = maskCode;
    }

    public long set(long value){
        return value | this.maskCode;
    }
    public long unset(long value){
        return value & ~this.maskCode;
    }
    public boolean isSet(long value){
        return (value & this.maskCode) == this.maskCode;
    }
}

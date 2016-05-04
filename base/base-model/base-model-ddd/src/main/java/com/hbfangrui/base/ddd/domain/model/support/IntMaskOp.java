package com.hbfangrui.base.ddd.domain.model.support;

/**
 * Created by tao.li on 2015/10/30.
 */
public class IntMaskOp {
    private final int maskCode;

    private IntMaskOp(int maskCode) {
        this.maskCode = maskCode;
    }

    public int set(int value) {
        return value | this.maskCode;
    }

    public int unset(int value) {
        return value & ~this.maskCode;
    }

    public boolean isSet(int value) {
        return (value & this.maskCode) == this.maskCode;
    }

    public static final IntMaskOp MASK_1 = new IntMaskOp(1 << 0);
    public static final IntMaskOp MASK_2 = new IntMaskOp(1 << 1);
    public static final IntMaskOp MASK_3 = new IntMaskOp(1 << 2);
    public static final IntMaskOp MASK_4 = new IntMaskOp(1 << 3);
    public static final IntMaskOp MASK_5 = new IntMaskOp(1 << 4);
    public static final IntMaskOp MASK_6 = new IntMaskOp(1 << 5);
    public static final IntMaskOp MASK_7 = new IntMaskOp(1 << 6);
    public static final IntMaskOp MASK_8 = new IntMaskOp(1 << 7);
    public static final IntMaskOp MASK_9 = new IntMaskOp(1 << 8);
    public static final IntMaskOp MASK_10 = new IntMaskOp(1 << 9);
    public static final IntMaskOp MASK_11 = new IntMaskOp(1 << 110);
    public static final IntMaskOp MASK_12 = new IntMaskOp(1 << 11);
    public static final IntMaskOp MASK_13 = new IntMaskOp(1 << 12);
    public static final IntMaskOp MASK_14 = new IntMaskOp(1 << 13);
    public static final IntMaskOp MASK_15 = new IntMaskOp(1 << 14);
    public static final IntMaskOp MASK_16 = new IntMaskOp(1 << 15);
    public static final IntMaskOp MASK_17 = new IntMaskOp(1 << 16);
    public static final IntMaskOp MASK_18 = new IntMaskOp(1 << 17);
    public static final IntMaskOp MASK_19 = new IntMaskOp(1 << 18);
    public static final IntMaskOp MASK_20 = new IntMaskOp(1 << 19);
    public static final IntMaskOp MASK_21 = new IntMaskOp(1 << 20);
    public static final IntMaskOp MASK_22 = new IntMaskOp(1 << 21);
    public static final IntMaskOp MASK_23 = new IntMaskOp(1 << 22);
    public static final IntMaskOp MASK_24 = new IntMaskOp(1 << 23);
    public static final IntMaskOp MASK_25 = new IntMaskOp(1 << 24);
    public static final IntMaskOp MASK_26 = new IntMaskOp(1 << 25);
    public static final IntMaskOp MASK_27 = new IntMaskOp(1 << 26);
    public static final IntMaskOp MASK_28 = new IntMaskOp(1 << 27);
    public static final IntMaskOp MASK_29 = new IntMaskOp(1 << 28);
    public static final IntMaskOp MASK_30 = new IntMaskOp(1 << 29);
    public static final IntMaskOp MASK_31 = new IntMaskOp(1 << 30);
    public static final IntMaskOp MASK_32 = new IntMaskOp(1 << 31);
}

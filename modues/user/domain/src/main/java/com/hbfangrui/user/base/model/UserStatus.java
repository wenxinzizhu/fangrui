package com.hbfangrui.user.base.model;

import com.hbfangrui.base.ddd.domain.model.support.IntMaskOp;
import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by tao.li on 2015/10/30.
 */
@Embeddable
@Data
public class UserStatus {
    private static final IntMaskOp ACTIVATED = IntMaskOp.MASK_1;

    private static final IntMaskOp CANCELED = IntMaskOp.MASK_31;
    private static final IntMaskOp DISABLED = IntMaskOp.MASK_32;

    private int status;
    private UserStatus(){

    }

    private UserStatus(int status){
        this.status = status;
    }

    public boolean isActivated(){
        return ACTIVATED.isSet(status);
    }

    public UserStatus active(boolean activate){
        if (activate){
            return apply(ACTIVATED.set(this.status));
        }else {
            return apply(ACTIVATED.unset(this.status));
        }
    }

    public boolean isDisabled(){
        return DISABLED.isSet(this.status);
    }

    public boolean isEnable(){
        return !isDisabled();
    }

    public UserStatus disable(boolean disable){
        if (disable){
            return apply(DISABLED.set(this.status));
        }else {
            return apply(DISABLED.unset(this.status));
        }
    }

    public boolean isCanceled() {
        return CANCELED.isSet(this.status);
    }

    public UserStatus cancel(boolean cancel) {
        if (cancel){
            return apply(CANCELED.set(this.status));
        }else {
            return apply(CANCELED.unset(this.status));
        }
    }


    public static UserStatus apply(int status){
        return new UserStatus(status);
    }
}

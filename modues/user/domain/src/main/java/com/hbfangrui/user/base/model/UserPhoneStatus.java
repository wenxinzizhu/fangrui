package com.hbfangrui.user.base.model;

import com.hbfangrui.base.ddd.domain.model.support.IntMaskOp;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by taoli on 15/11/1.
 */
@Embeddable
@Data
public class UserPhoneStatus {
    private static final IntMaskOp ACTIVATED = IntMaskOp.MASK_1;

    @Column(name = "status", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private int status;

    private UserPhoneStatus(){

    }
    private UserPhoneStatus(int status){
        this.status = status;
    }

    public UserPhoneStatus active(boolean activated){
        if (activated){
            return apply(ACTIVATED.set(getStatus()));
        }else {
            return apply(ACTIVATED.unset(getStatus()));
        }
    }

    public boolean isActivated(){
        return ACTIVATED.isSet(getStatus());
    }


    public static UserPhoneStatus apply(int status) {
        return new UserPhoneStatus(status);
    }


}

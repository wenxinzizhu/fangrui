package com.hbfangrui.user.base.model;

import com.hbfangrui.base.ddd.domain.model.support.Model;
import com.hbfangrui.base.ddd.domain.model.support.Phone;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by taoli on 15/11/1.
 */
@MappedSuperclass
@Data
public abstract class UserPhoneBase extends Model implements Serializable{
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @AttributeOverride(name = "id", column = @Column(name = "owner", nullable = false, updatable = false))
    @Embedded
    @Setter(AccessLevel.PROTECTED)
    private UserId owner;

    @Embedded
    @Setter(AccessLevel.PROTECTED)
    private Phone phone;

    @Embedded
    @Setter(AccessLevel.PRIVATE)
    private UserPhoneStatus status;

    protected void setOwner(UserId owner){
        UserPhonesChecker.checkOwner(owner);
        this.owner = owner;
    }

    protected void setPhone(Phone phone){
        UserPhonesChecker.checkPhone(phone);
        this.phone = phone;
    }

    protected void setStatus(UserPhoneStatus status){
        UserPhonesChecker.checkStatus(status);
        this.status = status;
    }

}

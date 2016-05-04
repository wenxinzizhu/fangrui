package com.hbfangrui.user.base.model;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.base.ddd.domain.model.support.Model;
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
public abstract class UserEmailBase extends Model implements Serializable{
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
    private Email email;

    @Embedded
    @Setter(AccessLevel.PRIVATE)
    private UserEmailStatus status;

    protected void setOwner(UserId owner){
        UserEmailsChecker.checkOwner(owner);
        this.owner = owner;
    }

    protected void setEmail(Email email){
        UserEmailsChecker.checkEmail(email);
        this.email = email;
    }

    protected void setStatus(UserEmailStatus status){
        UserEmailsChecker.checkStatus(status);
        this.status = status;
    }

}

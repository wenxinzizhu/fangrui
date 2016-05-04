package com.hbfangrui.user.base.model;

import com.hbfangrui.base.ddd.domain.model.support.Model;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tao.li on 2015/10/30.
 */
@MappedSuperclass
@Data
public abstract class UserBase extends Model implements Serializable{
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, updatable = false))
    @Embedded
    @Setter(AccessLevel.PROTECTED)
    private UserId id;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "name", nullable = false)
    private String name;

    @Setter(AccessLevel.PROTECTED)
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Setter(AccessLevel.PROTECTED)
    @AttributeOverride(name = "status", column = @Column(name = "status", nullable = false))
    @Embedded
    private UserStatus status;

    @Setter(AccessLevel.PROTECTED)
    @Embedded
    private UserPassword password;

    @Column(name = "avatar", nullable = false)
    @Setter(AccessLevel.PROTECTED)
    private String avatar;
}

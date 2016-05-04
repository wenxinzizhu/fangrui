package com.hbfangrui.base.ddd.domain.model.support;

import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tao.li on 2015/10/30.
 */
@MappedSuperclass
public class Model {

    @Version
    @Column(name = "version", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private int version = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Setter(AccessLevel.PRIVATE)
    private Date createdAt;

    @Column(name = "modified_at", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private Date modifiedAt;


    public int getVersion() {
        return version;
    }

    @PrePersist
    private void prePersist() {
        Date date = new Date();
        setCreatedAt(date);
        setModifiedAt(date);
    }

    @PreUpdate
    private void preUpdate() {
        Date date = new Date();
        setModifiedAt(modifiedAt);
    }
}

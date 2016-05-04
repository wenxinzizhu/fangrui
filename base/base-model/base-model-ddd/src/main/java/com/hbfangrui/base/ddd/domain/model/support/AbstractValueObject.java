package com.hbfangrui.base.ddd.domain.model.support;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by tao.li on 2015/10/26.
 */
public abstract class AbstractValueObject {

    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other){
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}

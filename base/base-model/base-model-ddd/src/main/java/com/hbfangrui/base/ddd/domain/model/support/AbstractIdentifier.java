package com.hbfangrui.base.ddd.domain.model.support;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.ddd.domain.model.Identifier;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.MappedSuperclass;


/**
 * Created by tao.li on 2015/10/26.
 */
@Data
@MappedSuperclass
public abstract class AbstractIdentifier implements Identifier {
    private String id;
    protected AbstractIdentifier(){
    }

    protected AbstractIdentifier(String id){
        setId(id);
    }

    @Override
    public String id() {
        return id;
    }

    private void setId(String id){
        Preconditions.checkArgument(StringUtils.isNotEmpty(id), "id can not be null");
        this.id = id;
    }
}

package com.hbfangrui.user.base.model;

import com.hbfangrui.base.ddd.domain.model.support.AbstractIdentifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Embeddable;

/**
 * Created by tao.li on 2015/10/30.
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Embeddable
public class UserId extends AbstractIdentifier {
    private UserId() {
    }

    private UserId(String id) {
        super(id);
    }

    public static final UserId apply(String id){
        return new UserId(id);
    }

}

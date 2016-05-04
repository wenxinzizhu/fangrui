package com.hbfangrui.base.ddd.domain.model.support;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.ddd.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by taoli on 15/11/14.
 */
@Data
@Embeddable
public class Phone extends AbstractValueObject implements ValueObject{
    @Column(name = "phone_num", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private String phone;

    private Phone(){

    }

    private Phone(String phone){
        Preconditions.checkArgument(StringUtils.isNotEmpty(phone), "phone can not be null");
        Preconditions.checkArgument(isPhone(phone), phone + " is not phone");
        this.phone = phone;
    }

    public static Phone apply(String phone){
        return new Phone(phone);
    }

    public static boolean isPhone(String phone){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}

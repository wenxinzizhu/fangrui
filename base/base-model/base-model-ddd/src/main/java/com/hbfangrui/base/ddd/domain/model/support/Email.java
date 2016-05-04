package com.hbfangrui.base.ddd.domain.model.support;

import com.google.common.base.Preconditions;
import com.hbfangrui.base.ddd.domain.model.ValueObject;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by taoli on 15/11/1.
 */
@Data
@Embeddable
public class Email extends AbstractValueObject implements ValueObject{
    @Column(name = "email_domain", updatable = false, nullable = false)
    private String domain;

    @Column(name = "email_name", updatable = false, nullable = false)
    private String name;

    private Email(){

    }
    private Email(String email){
        Preconditions.checkArgument(StringUtils.isNotEmpty(email), "email can not be null");
        Preconditions.checkArgument(isEmail(email), "not email");
        String[] es = email.split("@");
        setName(es[0]);
        setDomain(es[1]);
    }

    public static Email apply(String email){
        return new Email(email);
    }
    private void setName(String name){
        Preconditions.checkArgument(StringUtils.isNotEmpty(name), "name can not be null");
        this.name = name;
    }
    private void setDomain(String domain){
        Preconditions.checkArgument(StringUtils.isNotEmpty(domain), "domain can bot be null");
        this.domain = domain;
    }


    public static boolean isEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        Pattern p = Pattern.compile("\\w+([-+._]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public String toString(){
        return "UserEmail[" + getName() + "@" + getDomain() + "]";
    }
}

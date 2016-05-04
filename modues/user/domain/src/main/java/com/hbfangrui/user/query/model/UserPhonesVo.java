package com.hbfangrui.user.query.model;

import com.google.common.collect.Lists;
import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserPhoneStatus;
import com.hbfangrui.user.base.model.UserPhonesBase;
import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by taoli on 15/11/4.
 */
@Entity
@Table(name = "t_user_phones")
public class UserPhonesVo extends UserPhonesBase<UserPhoneVo>
    implements Serializable{

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "phones_id", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private List<UserPhoneVo> phones = Lists.newArrayList();
    @Override
    protected List<UserPhoneVo> getUserPhones() {
        return this.phones;
    }

    public static UserPhonesVo apply(UserId owner, List<Phone> phones) {
        UserPhonesVo userPhonesVo = new UserPhonesVo();
        userPhonesVo.setId(owner);
        userPhonesVo.setPhones(phones.stream()
                .map(phone -> UserPhoneVo.apply(owner, phone, UserPhoneStatus.apply(0)))
                .collect(toList()));
        return userPhonesVo;
    }
}

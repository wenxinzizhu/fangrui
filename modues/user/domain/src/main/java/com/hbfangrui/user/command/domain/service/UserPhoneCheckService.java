package com.hbfangrui.user.command.domain.service;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.command.domain.model.UserPhone;
import com.hbfangrui.user.command.domain.repository.UserPhonesCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by taoli on 15/11/1.
 */
@Component
public class UserPhoneCheckService {

    private UserPhonesCommandRepository userPhonesCommandRepository;

    @Autowired
    public void setUserPhonesRepository(UserPhonesCommandRepository userPhonesCommandRepository){
        this.userPhonesCommandRepository = userPhonesCommandRepository;
    }
    public Optional<UserPhone> getActivatedUserPhone(String phone){
        return getActivatedUserPhone(Phone.apply(phone));
    }

    public Optional<UserPhone> getActivatedUserPhone(Phone phone){
        List<UserPhone> userPhones = userPhonesCommandRepository.getByPhone(phone);

        return userPhones.stream().filter(userPhone->userPhone.getStatus().isActivated()).findFirst();
    }


}

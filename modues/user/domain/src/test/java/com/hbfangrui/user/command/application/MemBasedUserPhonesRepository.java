package com.hbfangrui.user.command.application;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.base.ddd.domain.repository.support.MemBaseRepository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.model.UserPhone;
import com.hbfangrui.user.command.domain.model.UserPhones;
import com.hbfangrui.user.command.domain.repository.UserPhonesCommandRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

class MemBasedUserPhonesRepository extends MemBaseRepository<UserId, UserPhones> implements UserPhonesCommandRepository {

    @Override
    public List<UserPhone> getByPhone(Phone phone) {
        return getData().values().stream()
                .filter(userPhones -> userPhones.getPhone(phone).isPresent())
                .map(userPhones -> userPhones.getPhone(phone).get())
                .collect(toList());
    }
}
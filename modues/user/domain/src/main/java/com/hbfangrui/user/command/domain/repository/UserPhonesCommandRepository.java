package com.hbfangrui.user.command.domain.repository;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.base.ddd.domain.repository.Repository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.model.UserPhone;
import com.hbfangrui.user.command.domain.model.UserPhones;

import java.util.List;

/**
 * Created by taoli on 15/11/1.
 */
public interface UserPhonesCommandRepository extends Repository<UserId, UserPhones> {
    List<UserPhone> getByPhone(Phone phone);
}

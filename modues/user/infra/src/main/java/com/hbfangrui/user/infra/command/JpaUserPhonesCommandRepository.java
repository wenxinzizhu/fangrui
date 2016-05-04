package com.hbfangrui.user.infra.command;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.base.infra.jpa.command.JpaCommandDao;
import com.hbfangrui.base.infra.jpa.command.JpaCommandRepository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.model.UserPhone;
import com.hbfangrui.user.command.domain.model.UserPhones;
import com.hbfangrui.user.command.domain.repository.UserPhonesCommandRepository;
import com.hbfangrui.user.infra.command.dao.UserPhoneDao;
import com.hbfangrui.user.infra.command.dao.UserPhonesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taoli on 15/11/3.
 */
@Repository
public class JpaUserPhonesCommandRepository extends JpaCommandRepository<UserId, UserPhones>
    implements UserPhonesCommandRepository {

    @Autowired
    private UserPhonesDao userPhonesDao;

    @Autowired
    private UserPhoneDao userPhoneDao;

    @Override
    protected JpaCommandDao<UserId, UserPhones> getDao() {
        return userPhonesDao;
    }

    @Override
    public List<UserPhone> getByPhone(Phone phone) {
        return this.userPhoneDao.getByPhone(phone);
    }
}

package com.hbfangrui.user.infra.command;

import com.hbfangrui.base.infra.jpa.command.JpaCommandDao;
import com.hbfangrui.base.infra.jpa.command.JpaCommandRepository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.model.User;
import com.hbfangrui.user.command.domain.repository.UserCommandRepository;
import com.hbfangrui.user.infra.command.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/**
 * Created by taoli on 15/10/31.
 */
@Repository
public class JpaUserCommandRepository extends JpaCommandRepository<UserId, User> implements UserCommandRepository {
    @Autowired
    private UserDao userDao;

    @Override
    protected JpaCommandDao<UserId, User> getDao() {
        return userDao;
    }
}

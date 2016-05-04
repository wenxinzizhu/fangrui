package com.hbfangrui.user.infra.command.dao;


import com.hbfangrui.base.infra.jpa.command.JpaCommandDao;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.model.User;

import java.util.Optional;

/**
 * Created by taoli on 15/10/31.
 */
public interface UserDao extends JpaCommandDao<UserId, User> {

    Optional<User> getById(UserId userId);
}

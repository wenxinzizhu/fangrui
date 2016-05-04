package com.hbfangrui.user.command.domain.repository;

import com.hbfangrui.base.ddd.domain.repository.Repository;
import com.hbfangrui.user.command.domain.model.User;
import com.hbfangrui.user.base.model.UserId;

/**
 * Created by taoli on 15/10/30.
 */
public interface UserCommandRepository extends Repository<UserId, User> {


}

package com.hbfangrui.user.command.application;

import com.hbfangrui.base.ddd.domain.repository.support.MemBaseRepository;
import com.hbfangrui.user.command.domain.model.User;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.repository.UserCommandRepository;

class MemBasesUserRepository extends MemBaseRepository<UserId, User> implements UserCommandRepository {

}
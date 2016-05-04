package com.hbfangrui.user.query.service.impl;

import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.query.repository.UserEmailsQueryRepository;
import com.hbfangrui.user.query.service.UserQueryService;
import com.hbfangrui.user.query.model.UserEmailsVo;
import com.hbfangrui.user.query.model.UserVo;
import com.hbfangrui.user.query.repository.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Created by taoli on 15/10/31.
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {
    @Autowired
    private UserQueryRepository userQueryRepository;

    @Autowired
    private UserEmailsQueryRepository userEmailsQueryRepository;


    @Override
    public CompletableFuture<Optional<UserVo>> getUserById(UserId userId) {
        return this.userQueryRepository.getById(userId);
    }

    @Override
    public CompletableFuture<List<UserVo>> getUserByIds(Collection<UserId> userIds) {
        return this.userQueryRepository.getByIds(userIds);
    }

    @Override
    public CompletableFuture<Optional<UserEmailsVo>> getUserEmailsById(UserId userId) {
        return this.userEmailsQueryRepository.getById(userId);
    }

    @Override
    public CompletableFuture<List<UserEmailsVo>> getUserEmailsByIds(Collection<UserId> userIds) {
        return this.userEmailsQueryRepository.getByIds(userIds);
    }
}

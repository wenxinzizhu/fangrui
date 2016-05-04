package com.hbfangrui.user.query.service;

import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.query.model.UserEmailsVo;
import com.hbfangrui.user.query.model.UserVo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Created by taoli on 15/10/31.
 */
public interface UserQueryService {
    CompletableFuture<Optional<UserVo>> getUserById(UserId userId);

    CompletableFuture<List<UserVo>> getUserByIds(Collection<UserId> userIds);

    CompletableFuture<Optional<UserEmailsVo>> getUserEmailsById(UserId userId);

    CompletableFuture<List<UserEmailsVo>> getUserEmailsByIds(Collection<UserId> userIds);
}

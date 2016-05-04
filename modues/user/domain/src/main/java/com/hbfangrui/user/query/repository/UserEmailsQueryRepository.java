package com.hbfangrui.user.query.repository;

import com.hbfangrui.base.cqrs.repository.QueryRepository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.query.model.UserEmailsVo;

/**
 * Created by taoli on 15/11/4.
 */
public interface UserEmailsQueryRepository extends QueryRepository<UserId, UserEmailsVo> {
}

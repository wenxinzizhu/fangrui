package com.hbfangrui.user.query.repository;

import com.hbfangrui.base.cqrs.repository.QueryRepository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.query.model.UserVo;

/**
 * Created by taoli on 15/10/31.
 */
public interface UserQueryRepository extends QueryRepository<UserId, UserVo> {
}

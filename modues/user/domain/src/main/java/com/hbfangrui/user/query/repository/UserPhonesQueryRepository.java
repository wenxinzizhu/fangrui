package com.hbfangrui.user.query.repository;

import com.hbfangrui.base.cqrs.repository.QueryRepository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.query.model.UserPhonesVo;

/**
 * Created by taoli on 15/11/4.
 */
public interface UserPhonesQueryRepository extends QueryRepository<UserId, UserPhonesVo> {
}

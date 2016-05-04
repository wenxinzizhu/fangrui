package com.hbfangrui.user.infra.query;

import com.hbfangrui.base.infra.jpa.query.JpaQueryDao;
import com.hbfangrui.base.infra.jpa.query.SimpleJpaQueryRepositorySupport;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.infra.query.dao.UserVoDao;
import com.hbfangrui.user.query.model.UserVo;
import com.hbfangrui.user.query.repository.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by taoli on 15/10/31.
 */
@Repository
public class JpaUserQueryRepository
        extends SimpleJpaQueryRepositorySupport<UserId, UserVo>
        implements UserQueryRepository {

    @Autowired
    private UserVoDao userVoDao;

    @Override
    protected JpaQueryDao<UserId, UserVo> getDao() {
        return userVoDao;
    }
}

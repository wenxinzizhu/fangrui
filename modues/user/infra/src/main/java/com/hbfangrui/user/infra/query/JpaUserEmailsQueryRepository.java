package com.hbfangrui.user.infra.query;

import com.hbfangrui.base.infra.jpa.query.JpaQueryDao;
import com.hbfangrui.base.infra.jpa.query.SimpleJpaQueryRepositorySupport;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.infra.query.dao.UserEmailsVoDao;
import com.hbfangrui.user.query.model.UserEmailsVo;
import com.hbfangrui.user.query.repository.UserEmailsQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by taoli on 15/11/4.
 */
@Repository
public class JpaUserEmailsQueryRepository extends SimpleJpaQueryRepositorySupport<UserId, UserEmailsVo>
    implements UserEmailsQueryRepository {

    @Autowired
    private UserEmailsVoDao userEmailsDao;

    @Override
    protected JpaQueryDao<UserId, UserEmailsVo> getDao() {
        return this.userEmailsDao;
    }
}

package com.hbfangrui.user.infra.query;

import com.hbfangrui.base.infra.jpa.query.JpaQueryDao;
import com.hbfangrui.base.infra.jpa.query.SimpleJpaQueryRepositorySupport;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.infra.query.dao.UserPhonesVoDao;
import com.hbfangrui.user.query.model.UserPhonesVo;
import com.hbfangrui.user.query.repository.UserPhonesQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by taoli on 15/11/4.
 */
@Repository
public class JpaUserPhonesQueryRepository extends SimpleJpaQueryRepositorySupport<UserId, UserPhonesVo>
    implements UserPhonesQueryRepository {

    @Autowired
    private UserPhonesVoDao userPhonesVoDao;

    @Override
    protected JpaQueryDao<UserId, UserPhonesVo> getDao() {
        return this.userPhonesVoDao;
    }
}

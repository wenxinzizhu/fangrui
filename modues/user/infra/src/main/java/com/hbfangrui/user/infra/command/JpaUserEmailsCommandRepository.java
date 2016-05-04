package com.hbfangrui.user.infra.command;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.base.infra.jpa.command.JpaCommandDao;
import com.hbfangrui.base.infra.jpa.command.JpaCommandRepository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.model.UserEmail;
import com.hbfangrui.user.command.domain.model.UserEmails;
import com.hbfangrui.user.command.domain.repository.UserEmailsCommandRepository;
import com.hbfangrui.user.infra.command.dao.UserEmailDao;
import com.hbfangrui.user.infra.command.dao.UserEmailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by taoli on 15/11/3.
 */
@Repository
public class JpaUserEmailsCommandRepository extends JpaCommandRepository<UserId, UserEmails>
    implements UserEmailsCommandRepository {

    @Autowired
    private UserEmailsDao userEmailsDao;

    @Autowired
    private UserEmailDao userEmailDao;

    @Override
    protected JpaCommandDao<UserId, UserEmails> getDao() {
        return userEmailsDao;
    }

    @Override
    public List<UserEmail> getByEmail(Email email) {
        return this.userEmailDao.getByEmail(email);
    }
}

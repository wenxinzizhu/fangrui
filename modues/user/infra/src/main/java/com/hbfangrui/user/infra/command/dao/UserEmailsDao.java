package com.hbfangrui.user.infra.command.dao;


import com.hbfangrui.base.infra.jpa.command.JpaCommandDao;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.model.UserEmails;

/**
 * Created by taoli on 15/11/3.
 */
public interface UserEmailsDao extends JpaCommandDao<UserId, UserEmails> {

}

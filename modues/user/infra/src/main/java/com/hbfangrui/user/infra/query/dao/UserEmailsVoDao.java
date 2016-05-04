package com.hbfangrui.user.infra.query.dao;


import com.hbfangrui.base.infra.jpa.query.JpaQueryDao;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.query.model.UserEmailsVo;

/**
 * Created by taoli on 15/11/4.
 */
public interface UserEmailsVoDao extends JpaQueryDao<UserId, UserEmailsVo> {
}

package com.hbfangrui.user.infra.query.dao;


import com.hbfangrui.base.infra.jpa.query.JpaQueryDao;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.query.model.UserVo;

/**
 * Created by taoli on 15/10/31.
 */
public interface UserVoDao extends JpaQueryDao<UserId, UserVo> {
}

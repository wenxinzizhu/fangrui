package com.hbfangrui.user.infra.command.dao;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.command.domain.model.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by taoli on 15/11/3.
 */
public interface UserEmailDao extends JpaRepository<UserEmail, Long>{
    List<UserEmail> getByEmail(Email email);
}

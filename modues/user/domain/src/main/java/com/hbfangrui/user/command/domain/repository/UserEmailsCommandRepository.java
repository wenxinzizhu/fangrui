package com.hbfangrui.user.command.domain.repository;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.base.ddd.domain.repository.Repository;
import com.hbfangrui.user.command.domain.model.UserEmails;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.model.UserEmail;

import java.util.List;

/**
 * Created by taoli on 15/11/1.
 */
public interface UserEmailsCommandRepository extends Repository<UserId, UserEmails> {
    List<UserEmail> getByEmail(Email email);
}

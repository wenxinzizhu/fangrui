package com.hbfangrui.user.command.application;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.base.ddd.domain.repository.support.MemBaseRepository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.model.UserEmail;
import com.hbfangrui.user.command.domain.model.UserEmails;
import com.hbfangrui.user.command.domain.repository.UserEmailsCommandRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

class MemBasedUserEmailsRepository extends MemBaseRepository<UserId, UserEmails> implements UserEmailsCommandRepository {

    @Override
    public List<UserEmail> getByEmail(Email email) {
        return getData().values().stream()
                .filter(userEmails -> userEmails.getEmail(email).isPresent())
                .map(userEmails -> userEmails.getEmail(email).get())
                .collect(toList());
    }
}
package com.hbfangrui.user.command.domain.service;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.command.domain.model.UserEmail;
import com.hbfangrui.user.command.domain.repository.UserEmailsCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by taoli on 15/11/1.
 */
@Component
public class UserEmailCheckService {

    private UserEmailsCommandRepository userEmailsRepository;

    @Autowired
    public void setUserEmailsRepository(UserEmailsCommandRepository userEmailsRepository){
        this.userEmailsRepository = userEmailsRepository;
    }
    public Optional<UserEmail> getActivatedUserEmail(String email){
        return getActivatedUserEmail(Email.apply(email));
    }

    public Optional<UserEmail> getActivatedUserEmail(Email email){
        List<UserEmail> userEmails = userEmailsRepository.getByEmail(email);

        return userEmails.stream().filter(userEmail->userEmail.getStatus().isActivated()).findFirst();
    }


}

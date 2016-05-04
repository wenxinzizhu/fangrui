package com.hbfangrui.user.command.domain.model;

import com.google.common.collect.Lists;
import com.hbfangrui.base.ddd.domain.model.es.ESAggregate;
import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserEmailStatus;
import com.hbfangrui.user.base.model.UserEmailsBase;
import com.hbfangrui.user.base.model.UserEmailsChecker;
import com.hbfangrui.user.command.domain.event.email.UserEmailActivatedEvent;
import com.hbfangrui.user.command.domain.event.email.UserEmailAddedEvent;
import com.hbfangrui.user.command.domain.event.email.UserEmailDeletedEvent;
import com.hbfangrui.user.command.domain.event.email.UserEmailSetDefaultEvent;
import com.hbfangrui.user.command.domain.exception.EmailExistedException;
import com.hbfangrui.user.command.domain.exception.EmailNotActivatedException;
import com.hbfangrui.user.command.domain.exception.EmailNotExistException;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.domain.exception.EmailIsDefaultException;
import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

/**
 * Created by taoli on 15/11/1.
 */
@Entity
@Table(name = "t_user_emails")
public class UserEmails extends UserEmailsBase<UserEmail> implements ESAggregate<UserId> {
    public static final UserEmailStatus DEFAULT_EMAIL_STATUS = UserEmailStatus.apply(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "emails_id", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private List<UserEmail> emails = Lists.newArrayList();

    public UserEmails(UserId owner) {
        setOwner(owner);
    }

    @Override
    protected List<UserEmail> getUserEmails() {
        return this.emails;
    }

    private UserEmails(){

    }
    public static UserEmails apply(UserId owner) {
        return new UserEmails(owner);
    }


    // Add Usr Email
    public void addEmail(Email email) {
        UserEmailsChecker.checkEmail(email);
        Optional<UserEmail> userEmail = getEmail(email);
        if (userEmail.isPresent()){
            throw new EmailExistedException(email);
        }
        publishAndApply(UserEmailAddedEvent.apply(getOwner(), getVersion(), email));
    }

    private void apply(UserEmailAddedEvent event){
        setOwner(event.getId());
        this.emails.add(UserEmail.apply(event.getId(), event.getEmail(), DEFAULT_EMAIL_STATUS));
        setEmailCount(this.emails.size());
    }

    // Active User Email
    public void active(Email email) {
        UserEmailsChecker.checkEmail(email);
        Optional<UserEmail> userEmail = getEmail(email);
        if (!userEmail.isPresent()){
            throw new EmailNotExistException(email);
        }
        publishAndApply(UserEmailActivatedEvent.apply(getOwner(), getVersion(), email));
        if (!getDefaultEmail().isPresent()){
            publishAndApply(UserEmailSetDefaultEvent.apply(getOwner(), getVersion(), email));
        }
    }

    private void apply(UserEmailActivatedEvent event){
        Optional<UserEmail> userEmail = getEmail(event.getEmail());
        userEmail.get().active();
    }

    // Delete User Email
    public void deleteEmail(Email email) {
        UserEmailsChecker.checkEmail(email);
        Optional<UserEmail> userEmail = getEmail(email);
        if (!userEmail.isPresent()){
            throw new EmailNotExistException(email);
        }
        Optional<UserEmail> defEmail = getDefaultEmail();
        if (defEmail.isPresent() && defEmail.get().getEmail().equals(email)){
            throw new EmailIsDefaultException(userEmail.get());
        }
        publishAndApply(UserEmailDeletedEvent.apply(getOwner(), getVersion(), email));
    }

    private void apply(UserEmailDeletedEvent event){
        this.emails.remove(getEmail(event.getEmail()).get());
        setEmailCount(this.emails.size());
    }


    // Set Default Email
    public void setDefaultEmail(Email email) {
        Optional<UserEmail> userEmail = getEmail(email);
        if (!userEmail.isPresent()){
            throw new EmailNotExistException(email);
        }
        if (!userEmail.get().getStatus().isActivated()){
            throw new EmailNotActivatedException(email);
        }
        publishAndApply(UserEmailSetDefaultEvent.apply(getOwner(), getVersion(), email));
    }

    private void apply(UserEmailSetDefaultEvent event){
        setDefEmail(event.getEmail());
    }
}

package com.hbfangrui.user.command.domain.model;

import com.hbfangrui.base.ddd.domain.model.es.ESAggregate;
import com.hbfangrui.user.base.model.*;
import com.hbfangrui.user.command.domain.event.user.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Optional;

import static com.hbfangrui.user.base.model.UserChecker.*;

/**
 * Created by tao.li on 2015/10/30.
 */
@Entity
@Table(name = "t_user")
public class User extends UserBase implements ESAggregate<UserId> {
    private static final UserStatus DEFAULT_USER_STATUS = UserStatus.apply(0);
    private User(){
    }

    // Create User
    public static User apply(UserId id, Gender gender, String name, String pwd, String avatar) {
        return new User(id, gender, name, pwd, avatar);
    }

    private User(UserId userId, Gender gender, String name,  String password, String avatar){
        checkUserId(userId);
        checkUserName(name);
        checkGender(gender);
        checkPassword(password);
        checkAvatar(avatar);
        UserPassword userPassword = UserPassword.apply(password);
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.apply(userId, getVersion(), gender, DEFAULT_USER_STATUS, name, userPassword, avatar);
        publishAndApply(userCreatedEvent);
    }


    private void apply(UserCreatedEvent event){
        setId(event.getId());
        setGender(event.getGender());
        setStatus(event.getUserStatus());
        setName(event.getName());
        setPassword(event.getPassword());
        setAvatar(event.getAvatar());
    }


    // Change Password
    public void changePassword(String pwd) {
        checkPassword(pwd);
        UserPassword userPassword = UserPassword.apply(pwd);
        publishAndApply(PasswordChangedEvent.apply(getId(), getVersion(), userPassword));
    }

    private void apply(PasswordChangedEvent event){
        setPassword(event.getUserPassword());
    }


    // Update User
    public void changeUser(UserId userId, Optional<Gender> gender, Optional<String> name, Optional<String> avatar) {
        checkUserId(userId);
        if (name!=null){
            checkUserName(name.get());
        }
        if (avatar != null){
            checkAvatar(avatar.get());
        }
        if (gender != null){
            checkGender(gender.get());
        }
        UserChangedEvent event = UserChangedEvent
                .apply(userId, getVersion(),
                        gender != null ? gender.get() : getGender(),
                        name != null ? name.get() : getName(),
                        avatar != null ? avatar.get() : getAvatar());
        publishAndApply(event);
    }

    private void apply(UserChangedEvent event){
        setGender(event.getGender());
        setName(event.getName());
        setAvatar(event.getAvatar());
    }

    // Disable User
    public void disable() {
        publishAndApply(UserDisabledEvent.apply(getId(), getVersion()));
    }

    private void apply(UserDisabledEvent disableEvent){
        setStatus(getStatus().disable(true));
    }

    // Enable User
    public void enable(){
        publishAndApply(UserEnabledEvent.apply(getId(), getVersion()));
    }

    private void apply(UserEnabledEvent enableEvent){
        setStatus(getStatus().disable(false));
    }

    // Active User
    public void active() {
        publishAndApply(UserActivatedEvent.apply(getId(), getVersion()));
    }
    private void apply(UserActivatedEvent event){
        setStatus(getStatus().active(true));
    }

    // Cancel
    public void cancel() {
        publishAndApply(UserCanceledEvent.apply(getId(), getVersion()));
    }
    private void apply(UserCanceledEvent event){
        setStatus(getStatus().cancel(true));
    }


    public UserEmails createEmails(){
        return UserEmails.apply(getId());
    }

    public UserPhones createPhones() {
        return UserPhones.apply(getId());
    }
}

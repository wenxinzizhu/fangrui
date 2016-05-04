package com.hbfangrui.user.command.application.imp;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.base.ddd.domain.unitofwork.DefaultUnitOfWork;
import com.hbfangrui.user.command.application.cmd.email.ActiveUserEmailCMD;
import com.hbfangrui.user.command.application.cmd.phone.AddUserPhoneCMD;
import com.hbfangrui.user.command.application.cmd.phone.SetDefaultPhoneCMD;
import com.hbfangrui.user.command.application.cmd.user.*;
import com.hbfangrui.user.command.domain.event.subscriber.UserPhonesEventSubscriber;
import com.hbfangrui.user.command.domain.exception.PhoneActivatedByOtherException;
import com.hbfangrui.user.command.domain.exception.UserNotExistException;
import com.hbfangrui.user.command.domain.model.*;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.command.application.UserCommandService;
import com.hbfangrui.user.command.application.cmd.email.AddUserEmailCMD;
import com.hbfangrui.user.command.application.cmd.email.DeleteUserEmailCMD;
import com.hbfangrui.user.command.application.cmd.email.SetDefaultEmailCMD;
import com.hbfangrui.user.command.application.cmd.phone.ActiveUserPhoneCMD;
import com.hbfangrui.user.command.application.cmd.phone.DeleteUserPhoneCMD;
import com.hbfangrui.user.command.domain.event.subscriber.UserEmailsEventSubscriber;
import com.hbfangrui.user.command.domain.event.subscriber.UserEventSubscriber;
import com.hbfangrui.user.command.domain.exception.EmailActivatedByOtherException;
import com.hbfangrui.user.command.domain.repository.UserCommandRepository;
import com.hbfangrui.user.command.domain.repository.UserEmailsCommandRepository;
import com.hbfangrui.user.command.domain.repository.UserPhonesCommandRepository;
import com.hbfangrui.user.command.domain.service.UserEmailCheckService;
import com.hbfangrui.user.command.domain.service.UserPhoneCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by taoli on 15/10/30.
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCommandServiceImpl.class);

    private UserCommandRepository userRepository;

    private UserEmailsCommandRepository userEmailsRepository;

    private UserPhonesCommandRepository userPhonesCommandRepository;

    private UserEmailCheckService userEmailCheckService;

    private UserPhoneCheckService userPhoneCheckService;

    private Collection<UserEventSubscriber> userEventSubscribers = Lists.newArrayList();

    private Collection<UserEmailsEventSubscriber> userEmailsEventSubscribers = Lists.newArrayList();

    private Collection<UserPhonesEventSubscriber> userPhonesEventSubscribers = Lists.newArrayList();

    @Autowired
    public void setUserRepository(UserCommandRepository commandRepository){
        Preconditions.checkArgument(commandRepository != null);
        this.userRepository = commandRepository;
    }

    @Autowired
    public void setUserEmailsRepository(UserEmailsCommandRepository userEmailsRepository){
        Preconditions.checkArgument(userEmailsRepository != null);
        this.userEmailsRepository = userEmailsRepository;
    }

    @Autowired
    public void setUserPhonesRepository(UserPhonesCommandRepository commandRepository) {
        this.userPhonesCommandRepository = commandRepository;
    }

    @Autowired
    public void setUserEventSubscribers(Collection<UserEventSubscriber> subscribers){
        this.userEventSubscribers.addAll(subscribers);
    }

    @Autowired
    public void setUserEmailsEventSubscribers(Collection<UserEmailsEventSubscriber> subscribers){
        this.userEmailsEventSubscribers.addAll(subscribers);
    }

    @Autowired
    public void setUserEmailCheckService(UserEmailCheckService checkService){
        Preconditions.checkArgument(checkService != null);
        this.userEmailCheckService = checkService;
    }

    @Autowired
    public void setUserPhoneCheckService(UserPhoneCheckService checkService){
        Preconditions.checkArgument(checkService != null);
        this.userPhoneCheckService = checkService;
    }

    @Override
    public void createUser(CreateUserCMD cmd) {
        executeForUser(() -> {
            User user = User.apply(cmd.getId(), cmd.getGender(), cmd.getName(), cmd.getPwd(), cmd.getAvatar());
            this.userRepository.save(user);
        });
    }

    @Override
    public void changePassword(ChangePasswordCMD cmd) {
        executeForUser(() -> {
            updateUser(cmd.getId(), user -> {
                user.changePassword(cmd.getPwd());
            });
        });
    }

    @Override
    public void changeUser(ChangeUserCMD cmd) {
        executeForUser(() -> {
            updateUser(cmd.getId(), user -> {
                user.changeUser(cmd.getId(), cmd.getGender(), cmd.getName(), cmd.getAvatar());
            });
        });
    }

    @Override
    public void disableUser(DisableUserCMD cmd) {
        executeForUser(() -> {
            updateUser(cmd.getId(), user -> {
                user.disable();
            });
        });
    }

    @Override
    public void enableUser(EnableUserCMD cmd) {
        executeForUser(() -> {
            updateUser(cmd.getId(), user -> {
                user.enable();
            });
        });
    }

    @Override
    public void activeUser(ActiveUserCMD cmd) {
        executeForUser(() -> {
            updateUser(cmd.getId(), user -> {
                user.active();
            });
        });
    }

    @Override
    public void cancelUser(CancelUserCMD cmd) {
        executeForUser(() -> {
            updateUser(cmd.getId(), user -> {
                user.cancel();
            });
        });
    }



    @Override
    public void addUserEmail(AddUserEmailCMD cmd) {
        executeForUserEmails(cmd.getId(), userEmails->{
            emailIsActivatedByOther(cmd.getEmail(), cmd.getId());
            userEmails.addEmail(cmd.getEmail());
        });
    }

    @Override
    public void activeUserEmail(ActiveUserEmailCMD cmd) {
        executeForUserEmails(cmd.getId(), userEmails->{
            emailIsActivatedByOther(cmd.getEmail(), cmd.getId());
            userEmails.active(cmd.getEmail());
        });
    }

    @Override
    public void setDefaultUserEmail(SetDefaultEmailCMD cmd) {
        executeForUserEmails(cmd.getId(), userEmails->{
            userEmails.setDefaultEmail(cmd.getEmail());
        });
    }

    @Override
    public void deleteUserEmail(DeleteUserEmailCMD cmd) {
        executeForUserEmails(cmd.getId(), userEmails->{
            userEmails.deleteEmail(cmd.getEmail());
        });
    }

    @Override
    public void addUserPhone(AddUserPhoneCMD cmd) {
        executeForUserPhones(cmd.getId(), userPhones -> {
            phoneIsActivatedByOther(cmd.getPhone(), cmd.getId());
            userPhones.addPhone(cmd.getPhone());
        });
    }

    @Override
    public void activeUserPhone(ActiveUserPhoneCMD cmd) {
        executeForUserPhones(cmd.getId(), userPhones -> {
            phoneIsActivatedByOther(cmd.getPhone(), cmd.getId());
            userPhones.active(cmd.getPhone());
        });
    }

    @Override
    public void setDefaultUserPhone(SetDefaultPhoneCMD cmd) {
        executeForUserPhones(cmd.getId(), userPhones -> {
            userPhones.setDefaultPhone(cmd.getPhone());
        });
    }

    @Override
    public void deleteUserPhone(DeleteUserPhoneCMD cmd) {
        executeForUserPhones(cmd.getId(), userPhones -> {
            userPhones.deletePhone(cmd.getPhone());
        });
    }

    private void updateUser(UserId userId, Consumer<User> consumer){
        Preconditions.checkArgument(userId != null, "user id can not be null");
        Preconditions.checkArgument(consumer != null, "user consumer can not be null");
        Optional<User> user = this.userRepository.load(userId);
        if (!user.isPresent()){
            throw new UserNotExistException(userId);
        }
        consumer.accept(user.get());

    }

    private void executeForUserEmails(UserId owner, Consumer<UserEmails> consumer){
        try {
            DefaultUnitOfWork.getContext().registerSubscribers(userEmailsEventSubscribers);
            Optional<UserEmails> userEmailsOptional = this.userEmailsRepository.load(owner);
            UserEmails userEmails = null;
            if (!userEmailsOptional.isPresent()){
                Optional<User> user = userRepository.load(owner);
                if (!user.isPresent()){
                    throw new UserNotExistException(owner);
                }
                userEmails = user.get().createEmails();
            }else {
                userEmails = userEmailsOptional.get();
            }
            consumer.accept(userEmails);
            this.userEmailsRepository.save(userEmails);
        }catch (Exception e){
            LOGGER.error("failed to execute for user emails.", e);
            throw e;
        }finally {
            DefaultUnitOfWork.cleanContext();
        }
    }

    private void executeForUserPhones(UserId owner, Consumer<UserPhones> consumer){
        try {
            DefaultUnitOfWork.getContext().registerSubscribers(userPhonesEventSubscribers);
            Optional<UserPhones> userPhonesOptional = this.userPhonesCommandRepository.load(owner);
            UserPhones userPhones = null;
            if (!userPhonesOptional.isPresent()){
                Optional<User> user = userRepository.load(owner);
                if (!user.isPresent()){
                    throw new UserNotExistException(owner);
                }
                userPhones = user.get().createPhones();
            }else {
                userPhones = userPhonesOptional.get();
            }
            consumer.accept(userPhones);
            this.userPhonesCommandRepository.save(userPhones);
        }catch (Exception e){
            LOGGER.error("failed to execute for user phones", e);
            throw e;
        }finally {
            DefaultUnitOfWork.cleanContext();
        }

    }

    private void emailIsActivatedByOther(Email email, UserId owner){
        Optional<UserEmail> userEmail = userEmailCheckService.getActivatedUserEmail(email);
        if (userEmail.isPresent() && !userEmail.get().getOwner().equals(owner)){
            throw new EmailActivatedByOtherException(email, userEmail.get().getOwner());
        }
    }

    private void phoneIsActivatedByOther(Phone phone, UserId owner) {
        Optional<UserPhone> userPhone = userPhoneCheckService.getActivatedUserPhone(phone);
        if (userPhone.isPresent() && !userPhone.get().getOwner().equals(owner)){
            throw new PhoneActivatedByOtherException(phone, userPhone.get().getOwner());
        }
    }

    private void executeForUser(Runnable runnable){
        try {
            DefaultUnitOfWork.getContext().registerSubscribers(userEventSubscribers);
            runnable.run();
        }catch (Exception e){
            LOGGER.error("failed to execute for user.", e);
            throw e;
        }finally {
            DefaultUnitOfWork.cleanContext();
        }
    }


}

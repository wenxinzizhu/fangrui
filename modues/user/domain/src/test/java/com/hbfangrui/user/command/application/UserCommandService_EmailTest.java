package com.hbfangrui.user.command.application;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.Gender;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserIdGenerator;
import com.hbfangrui.user.command.application.cmd.email.ActiveUserEmailCMD;
import com.hbfangrui.user.command.application.cmd.email.AddUserEmailCMD;
import com.hbfangrui.user.command.application.cmd.email.DeleteUserEmailCMD;
import com.hbfangrui.user.command.application.cmd.email.SetDefaultEmailCMD;
import com.hbfangrui.user.command.application.cmd.user.CreateUserCMD;
import com.hbfangrui.user.command.application.imp.UserCommandServiceImpl;
import com.hbfangrui.user.command.domain.exception.*;
import com.hbfangrui.user.command.domain.model.UserEmail;
import com.hbfangrui.user.command.domain.model.UserEmails;
import com.hbfangrui.user.command.domain.repository.UserCommandRepository;
import com.hbfangrui.user.command.domain.repository.UserEmailsCommandRepository;
import com.hbfangrui.user.command.domain.service.UserEmailCheckService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class UserCommandService_EmailTest {
    private final UserId TEST_USER_ID_1 = UserIdGenerator.generate();

    private final UserId TEST_USER_ID_2 = UserIdGenerator.generate();

    // 默认激活邮箱
    private final Email TEST_USER_EMAIL_1 = Email.apply("litao1@163.com");
    // 默认只添加，未激活
    private final Email TEST_USER_EMAIL_2 = Email.apply("litao2@163.com");

    private UserCommandRepository userCommandRepository;

    private UserCommandService userCommandService;

    private UserEmailsCommandRepository commandRepository;


    @Before
    public void setUp(){
        this.userCommandRepository = createUserRepository();
        this.commandRepository = createRepository();

        this.userCommandService = createUserCommandService();

        this.userCommandService.createUser(CreateUserCMD.apply(TEST_USER_ID_1, Gender.FEMALE, "test", "test", "avatar"));
        this.userCommandService.addUserEmail(AddUserEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_1));
        this.userCommandService.addUserEmail(AddUserEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_2));

        this.userCommandService.createUser(CreateUserCMD.apply(TEST_USER_ID_2, Gender.FEMALE, "test", "test", "avatar"));
        this.userCommandService.addUserEmail(AddUserEmailCMD.apply(TEST_USER_ID_2, TEST_USER_EMAIL_1));
        this.userCommandService.addUserEmail(AddUserEmailCMD.apply(TEST_USER_ID_2, TEST_USER_EMAIL_2));

        this.userCommandService.activeUserEmail(ActiveUserEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_1));
    }

    @After
    public void after(){
        this.userCommandRepository = null;
        this.commandRepository = null;
        this.userCommandService = null;
    }

    private UserCommandRepository createUserRepository() {
        return new MemBasesUserRepository();
    }

    private UserEmailsCommandRepository createRepository() {
        return new MemBasedUserEmailsRepository();
    }

    private UserCommandService createUserCommandService() {
        UserCommandServiceImpl service = new UserCommandServiceImpl();
        service.setUserEmailsRepository(this.commandRepository);
        service.setUserRepository(this.userCommandRepository);
        UserEmailCheckService emailCheckService = new UserEmailCheckService();
        emailCheckService.setUserEmailsRepository(this.commandRepository);
        service.setUserEmailCheckService(emailCheckService);
        return service;
    }

    @Test(expected = UserNotExistException.class)
    public void testAddUserEmail1() throws Exception {
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.addUserEmail(AddUserEmailCMD.apply(owner, TEST_USER_EMAIL_1));
    }


    @Test(expected = EmailExistedException.class)
    public void testAddUserEmail2(){
        this.userCommandService.addUserEmail(AddUserEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_1));
    }

    @Test(expected = EmailActivatedByOtherException.class)
    public void testAddUserEmail3() throws Exception {
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.createUser(CreateUserCMD.apply(owner, Gender.FEMALE, "test", "test", "avatar"));
        this.userCommandService.addUserEmail(AddUserEmailCMD.apply(owner, TEST_USER_EMAIL_1));
    }


    @Test(expected = UserNotExistException.class)
    public void testActiveUserEmail() throws Exception{
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.activeUserEmail(ActiveUserEmailCMD.apply(owner, TEST_USER_EMAIL_1));
    }

    @Test(expected = EmailNotExistException.class)
    public void testActiveUserEmail1() throws Exception{
        this.userCommandService.activeUserEmail(ActiveUserEmailCMD.apply(TEST_USER_ID_1, "test@123.com"));
    }

    @Test(expected = EmailActivatedByOtherException.class)
    public void testActiveUserEmail2() throws Exception{
        this.userCommandService.activeUserEmail(ActiveUserEmailCMD.apply(TEST_USER_ID_2, TEST_USER_EMAIL_1));
    }

    @Test
    public void testActiveUserEmail3() throws Exception {
        checkUserEmails(TEST_USER_ID_1, userEmails->{
            Optional<UserEmail> userEmail = userEmails.getEmail(TEST_USER_EMAIL_2);
            assertTrue(userEmail.isPresent());
            assertFalse(userEmail.get().getStatus().isActivated());
        });
        this.userCommandService.activeUserEmail(ActiveUserEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_2));
        checkUserEmails(TEST_USER_ID_1, userEmails->{
            Optional<UserEmail> userEmail = userEmails.getEmail(TEST_USER_EMAIL_2);
            assertTrue(userEmail.isPresent());
            assertTrue(userEmail.get().getStatus().isActivated());
        });
    }

    @Test(expected = UserNotExistException.class)
    public void testSetDefaultUserEmail() throws Exception {
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.setDefaultUserEmail(SetDefaultEmailCMD.apply(owner, TEST_USER_EMAIL_1));
    }

    @Test(expected = EmailNotExistException.class)
    public void testSetDefaultUserEmail1() throws Exception {
        this.userCommandService.setDefaultUserEmail(SetDefaultEmailCMD.apply(TEST_USER_ID_1, "test@123.com"));
    }

    @Test(expected = EmailNotActivatedException.class)
    public void testSetDefaultUserEmail2() throws Exception{
        this.userCommandService.setDefaultUserEmail(SetDefaultEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_2));
    }

    @Test
    public void testSetDefaultUserEmail3() throws Exception {
        checkUserEmails(TEST_USER_ID_1, userEmails->{
            Optional<UserEmail> userEmail1 = userEmails.getEmail(TEST_USER_EMAIL_1);
            assertTrue(userEmail1.isPresent());
            assertEquals(userEmails.getDefaultEmail().get().getEmail(), userEmail1.get().getEmail());

            Optional<UserEmail> userEmail2 = userEmails.getEmail(TEST_USER_EMAIL_2);
            assertTrue(userEmail2.isPresent());
        });

        this.userCommandService.activeUserEmail(ActiveUserEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_2));
        this.userCommandService.setDefaultUserEmail(SetDefaultEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_2));

        checkUserEmails(TEST_USER_ID_1, userEmails->{
            Optional<UserEmail> userEmail1 = userEmails.getEmail(TEST_USER_EMAIL_1);
            assertTrue(userEmail1.isPresent());

            Optional<UserEmail> userEmail2 = userEmails.getEmail(TEST_USER_EMAIL_2);
            assertTrue(userEmail2.isPresent());
            assertEquals(userEmails.getDefaultEmail().get().getEmail(), userEmail2.get().getEmail());
        });
    }

    @Test(expected = UserNotExistException.class)
    public void testDeleteUserEmail1() throws Exception {
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.deleteUserEmail(DeleteUserEmailCMD.apply(owner, TEST_USER_EMAIL_1));
    }

    @Test(expected = EmailNotExistException.class)
    public void testDeleteUserEmail2() throws Exception {
        this.userCommandService.deleteUserEmail(DeleteUserEmailCMD.apply(TEST_USER_ID_1, "test@123.com"));
    }

    @Test(expected = EmailIsDefaultException.class)
    public void testDeleteUserEmail3() throws Exception {
        this.userCommandService.deleteUserEmail(DeleteUserEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_1));
    }

    @Test
    public void testDeleteUserEmail() throws Exception {
        checkUserEmails(TEST_USER_ID_1, userEmails->{
            Optional<UserEmail> userEmail1 = userEmails.getEmail(TEST_USER_EMAIL_1);
            assertTrue(userEmail1.isPresent());
            Optional<UserEmail> userEmail2 = userEmails.getEmail(TEST_USER_EMAIL_2);
            assertTrue(userEmail2.isPresent());
        });
        this.userCommandService.deleteUserEmail(DeleteUserEmailCMD.apply(TEST_USER_ID_1, TEST_USER_EMAIL_2));
        checkUserEmails(TEST_USER_ID_1, userEmails->{
            Optional<UserEmail> userEmail1 = userEmails.getEmail(TEST_USER_EMAIL_1);
            assertTrue(userEmail1.isPresent());
            Optional<UserEmail> userEmail2 = userEmails.getEmail(TEST_USER_EMAIL_2);
            assertFalse(userEmail2.isPresent());
        });
    }

    private void checkUserEmails(UserId owner, Consumer<UserEmails> consumer){
        Optional<UserEmails> userEmails = this.commandRepository.load(owner);
        assertTrue(userEmails.isPresent());
        consumer.accept(userEmails.get());
    }

}
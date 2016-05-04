package com.hbfangrui.user.command.application;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.Gender;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserIdGenerator;
import com.hbfangrui.user.command.application.cmd.phone.ActiveUserPhoneCMD;
import com.hbfangrui.user.command.application.cmd.phone.AddUserPhoneCMD;
import com.hbfangrui.user.command.application.cmd.phone.DeleteUserPhoneCMD;
import com.hbfangrui.user.command.application.cmd.phone.SetDefaultPhoneCMD;
import com.hbfangrui.user.command.application.cmd.user.CreateUserCMD;
import com.hbfangrui.user.command.application.imp.UserCommandServiceImpl;
import com.hbfangrui.user.command.domain.exception.*;
import com.hbfangrui.user.command.domain.model.UserPhone;
import com.hbfangrui.user.command.domain.model.UserPhones;
import com.hbfangrui.user.command.domain.repository.UserCommandRepository;
import com.hbfangrui.user.command.domain.repository.UserPhonesCommandRepository;
import com.hbfangrui.user.command.domain.service.UserPhoneCheckService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class UserCommandService_PhoneTest {
    private final UserId TEST_USER_ID_1 = UserIdGenerator.generate();

    private final UserId TEST_USER_ID_2 = UserIdGenerator.generate();

    // 默认激活邮箱
    private final Phone TEST_USER_PHONE_1 = Phone.apply("18610043917");
    // 默认只添加，未激活
    private final Phone TEST_USER_PHONE_2 = Phone.apply("18610043918");

    private UserCommandRepository userCommandRepository;

    private UserCommandService userCommandService;

    private UserPhonesCommandRepository commandRepository;


    @Before
    public void setUp(){
        this.userCommandRepository = createUserRepository();
        this.commandRepository = createRepository();

        this.userCommandService = createUserCommandService();

        this.userCommandService.createUser(CreateUserCMD.apply(TEST_USER_ID_1, Gender.FEMALE, "test", "test", "avatar"));
        this.userCommandService.addUserPhone(AddUserPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_1));
        this.userCommandService.addUserPhone(AddUserPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_2));

        this.userCommandService.createUser(CreateUserCMD.apply(TEST_USER_ID_2, Gender.FEMALE, "test", "test", "avatar"));
        this.userCommandService.addUserPhone(AddUserPhoneCMD.apply(TEST_USER_ID_2, TEST_USER_PHONE_1));
        this.userCommandService.addUserPhone(AddUserPhoneCMD.apply(TEST_USER_ID_2, TEST_USER_PHONE_2));

        this.userCommandService.activeUserPhone(ActiveUserPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_1));
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

    private UserPhonesCommandRepository createRepository() {
        return new MemBasedUserPhonesRepository();
    }

    private UserCommandService createUserCommandService() {
        UserCommandServiceImpl service = new UserCommandServiceImpl();
        service.setUserPhonesRepository(this.commandRepository);
        service.setUserRepository(this.userCommandRepository);
        UserPhoneCheckService emailCheckService = new UserPhoneCheckService();
        emailCheckService.setUserPhonesRepository(this.commandRepository);
        service.setUserPhoneCheckService(emailCheckService);
        return service;
    }

    @Test(expected = UserNotExistException.class)
    public void testAddUserPhone1() throws Exception {
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.addUserPhone(AddUserPhoneCMD.apply(owner, TEST_USER_PHONE_1));
    }


    @Test(expected = PhoneExistedException.class)
    public void testAddUserPhone2(){
        this.userCommandService.addUserPhone(AddUserPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_1));
    }

    @Test(expected = PhoneActivatedByOtherException.class)
    public void testAddUserPhone3() throws Exception {
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.createUser(CreateUserCMD.apply(owner, Gender.FEMALE, "test", "test", "avatar"));
        this.userCommandService.addUserPhone(AddUserPhoneCMD.apply(owner, TEST_USER_PHONE_1));
    }


    @Test(expected = UserNotExistException.class)
    public void testActiveUserPhone() throws Exception{
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.activeUserPhone(ActiveUserPhoneCMD.apply(owner, TEST_USER_PHONE_1));
    }

    @Test(expected = PhoneNotExistException.class)
    public void testActiveUserPhone1() throws Exception{
        this.userCommandService.activeUserPhone(ActiveUserPhoneCMD.apply(TEST_USER_ID_1, "18619987201"));
    }

    @Test(expected = PhoneActivatedByOtherException.class)
    public void testActiveUserPhone2() throws Exception{
        this.userCommandService.activeUserPhone(ActiveUserPhoneCMD.apply(TEST_USER_ID_2, TEST_USER_PHONE_1));
    }

    @Test
    public void testActiveUserPhone3() throws Exception {
        checkUserPhones(TEST_USER_ID_1, userPhones -> {
            Optional<UserPhone> userPhone = userPhones.getPhone(TEST_USER_PHONE_2);
            assertTrue(userPhone.isPresent());
            assertFalse(userPhone.get().getStatus().isActivated());
        });
        this.userCommandService.activeUserPhone(ActiveUserPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_2));
        checkUserPhones(TEST_USER_ID_1, userPhones -> {
            Optional<UserPhone> userPhone = userPhones.getPhone(TEST_USER_PHONE_2);
            assertTrue(userPhone.isPresent());
            assertTrue(userPhone.get().getStatus().isActivated());
        });
    }

    @Test(expected = UserNotExistException.class)
    public void testSetDefaultUserPhone() throws Exception {
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.setDefaultUserPhone(SetDefaultPhoneCMD.apply(owner, TEST_USER_PHONE_1));
    }

    @Test(expected = PhoneNotExistException.class)
    public void testSetDefaultUserPhone1() throws Exception {
        this.userCommandService.setDefaultUserPhone(SetDefaultPhoneCMD.apply(TEST_USER_ID_1, "18610043911"));
    }

    @Test(expected = PhoneNotActivatedException.class)
    public void testSetDefaultUserPhone2() throws Exception{
        this.userCommandService.setDefaultUserPhone(SetDefaultPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_2));
    }

    @Test
    public void testSetDefaultUserPhone3() throws Exception {
        checkUserPhones(TEST_USER_ID_1, userPhones -> {
            Optional<UserPhone> userPhone1 = userPhones.getPhone(TEST_USER_PHONE_1);
            assertTrue(userPhone1.isPresent());
            assertEquals(userPhones.getDefaultPhone().get().getPhone(), userPhone1.get().getPhone());

            Optional<UserPhone> userPhone2 = userPhones.getPhone(TEST_USER_PHONE_2);
            assertTrue(userPhone2.isPresent());
        });

        this.userCommandService.activeUserPhone(ActiveUserPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_2));
        this.userCommandService.setDefaultUserPhone(SetDefaultPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_2));

        checkUserPhones(TEST_USER_ID_1, userPhones -> {
            Optional<UserPhone> userPhone1 = userPhones.getPhone(TEST_USER_PHONE_1);
            assertTrue(userPhone1.isPresent());

            Optional<UserPhone> userPhone2 = userPhones.getPhone(TEST_USER_PHONE_2);
            assertTrue(userPhone2.isPresent());
            assertEquals(userPhones.getDefaultPhone().get().getPhone(), userPhone2.get().getPhone());
        });
    }

    @Test(expected = UserNotExistException.class)
    public void testDeleteUserPhone1() throws Exception {
        UserId owner = UserIdGenerator.generate();
        this.userCommandService.deleteUserPhone(DeleteUserPhoneCMD.apply(owner, TEST_USER_PHONE_1));
    }

    @Test(expected = PhoneNotExistException.class)
    public void testDeleteUserPhone2() throws Exception {
        this.userCommandService.deleteUserPhone(DeleteUserPhoneCMD.apply(TEST_USER_ID_1, "18610032819"));
    }

    @Test(expected = PhoneIsDefaultException.class)
    public void testDeleteUserPhone3() throws Exception {
        this.userCommandService.deleteUserPhone(DeleteUserPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_1));
    }

    @Test
    public void testDeleteUserPhone() throws Exception {
        checkUserPhones(TEST_USER_ID_1, userPhones -> {
            Optional<UserPhone> userPhone1 = userPhones.getPhone(TEST_USER_PHONE_1);
            assertTrue(userPhone1.isPresent());
            Optional<UserPhone> userPhone2 = userPhones.getPhone(TEST_USER_PHONE_2);
            assertTrue(userPhone2.isPresent());
        });
        this.userCommandService.deleteUserPhone(DeleteUserPhoneCMD.apply(TEST_USER_ID_1, TEST_USER_PHONE_2));
        checkUserPhones(TEST_USER_ID_1, userPhones -> {
            Optional<UserPhone> userPhone1 = userPhones.getPhone(TEST_USER_PHONE_1);
            assertTrue(userPhone1.isPresent());
            Optional<UserPhone> userPhone2 = userPhones.getPhone(TEST_USER_PHONE_2);
            assertFalse(userPhone2.isPresent());
        });
    }

    private void checkUserPhones(UserId owner, Consumer<UserPhones> consumer){
        Optional<UserPhones> userPhones = this.commandRepository.load(owner);
        assertTrue(userPhones.isPresent());
        consumer.accept(userPhones.get());
    }

}
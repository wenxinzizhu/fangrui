package com.hbfangrui.user.command.application;

import com.hbfangrui.user.base.model.Gender;
import com.hbfangrui.user.command.application.cmd.user.*;
import com.hbfangrui.user.command.application.imp.UserCommandServiceImpl;
import com.hbfangrui.user.command.domain.model.User;
import com.hbfangrui.user.command.domain.repository.UserCommandRepository;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserIdGenerator;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

public class UserCommandServiceTest {
    private static final UserId TEST_USER_ID = UserIdGenerator.generate();
    private static final Gender TEST_GENDER = Gender.NONE;
    private static final String TEST_USER_NAME = "userName";
    private static final String TEST_USER_PWD = "userPassword";
    private static final String TEST_USER_AVATAR = "userAvatar";

    private UserCommandRepository commandRepository;

    private UserCommandService commandService;

    @Before
    public void setUp() throws Exception {
        this.commandRepository = new MemBasesUserRepository();
        this.commandService = createUserCommandService();
        createTestUser();
    }

    private void createTestUser() {
        CreateUserCMD cmd = CreateUserCMD.apply(TEST_USER_ID,TEST_GENDER, TEST_USER_NAME, TEST_USER_PWD, TEST_USER_AVATAR);
        this.commandService.createUser(cmd);
    }

    private UserCommandService createUserCommandService(){
        UserCommandServiceImpl commandService = new UserCommandServiceImpl();
        commandService.setUserRepository(this.commandRepository);
        commandService.setUserEventSubscribers(Arrays.asList(new UserLoggedEventSubscriber()));
        return commandService;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testChangePassword() throws Exception {
        checkUser(TEST_USER_ID, user->{
            Assert.assertTrue(user.getPassword().check(TEST_USER_PWD));
        });

        String pwd = "new password";
        this.commandService.changePassword(ChangePasswordCMD.apply(TEST_USER_ID, pwd));

        checkUser(TEST_USER_ID, user->{
            Assert.assertTrue(user.getPassword().check(pwd));
        });

    }

    @Test
    public void testChangeUser() throws Exception {
        checkUser(TEST_USER_ID, user->{
            Assert.assertEquals(TEST_USER_ID, user.getId());
            Assert.assertEquals(TEST_GENDER, user.getGender());
            Assert.assertEquals(TEST_USER_NAME, user.getName());
            Assert.assertEquals(TEST_USER_AVATAR, user.getAvatar());
        });
        String name = "newUserName";
        String avatar = "newAvatar";
        Gender gender = Gender.FEMALE;
        this.commandService.changeUser(ChangeUserCMD.builder(TEST_USER_ID).gender(gender).name(name).avatar(avatar).build());
        checkUser(TEST_USER_ID, user->{
            Assert.assertEquals(TEST_USER_ID, user.getId());
            Assert.assertEquals(gender, user.getGender());
            Assert.assertEquals(name, user.getName());
            Assert.assertEquals(avatar, user.getAvatar());
        });
    }

    @Test
    public void testEnableAndDisableUser() throws Exception {
        checkUser(TEST_USER_ID, user->{
            Assert.assertTrue(user.getStatus().isEnable());
        });

        this.commandService.disableUser(DisableUserCMD.apply(TEST_USER_ID));

        checkUser(TEST_USER_ID, user->{
            Assert.assertTrue(user.getStatus().isDisabled());
        });

        this.commandService.enableUser(EnableUserCMD.apply(TEST_USER_ID));

        checkUser(TEST_USER_ID, user->{
            Assert.assertTrue(user.getStatus().isEnable());
        });
    }

    @Test
    public void testActiveUser() throws Exception {
        checkUser(TEST_USER_ID, user->{
            Assert.assertFalse(user.getStatus().isActivated());
        });
        this.commandService.activeUser(ActiveUserCMD.apply(TEST_USER_ID));
        checkUser(TEST_USER_ID, user->{
            Assert.assertTrue(user.getStatus().isActivated());
        });
    }

    @Test
    public void testCancelUser() throws Exception {
        checkUser(TEST_USER_ID, user->{
            Assert.assertFalse(user.getStatus().isCanceled());
        });
        this.commandService.cancelUser(CancelUserCMD.apply(TEST_USER_ID));
        checkUser(TEST_USER_ID, user->{
            Assert.assertTrue(user.getStatus().isCanceled());
        });
    }

    private void checkUser(UserId userId, Consumer<User> consumer){
        User user = this.commandRepository.load(userId).get();
        consumer.accept(user);
    }

}

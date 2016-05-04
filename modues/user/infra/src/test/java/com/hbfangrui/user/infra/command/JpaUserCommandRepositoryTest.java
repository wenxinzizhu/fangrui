package com.hbfangrui.user.infra.command;

import com.hbfangrui.user.base.model.Gender;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserIdGenerator;
import com.hbfangrui.user.command.domain.model.User;
import com.hbfangrui.user.command.domain.repository.UserCommandRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-jpa.xml",
        "classpath:spring-user-command-repository.xml"})
public class JpaUserCommandRepositoryTest extends
        AbstractTransactionalJUnit4SpringContextTests {
    private static final UserId TEST_USER_ID = UserIdGenerator.generate();
    private static final Gender TEST_USER_GENDER = Gender.NONE;
    private static final String TEST_USER_NAME = "userName";
    private static final String TEST_USER_PWD = "userPassword";
    private static final String TEST_USER_AVATAR = "userAvatar";

    @Autowired
    private UserCommandRepository commandRepository;



    @Before
    public void setUp(){
        User user = User.apply(TEST_USER_ID, TEST_USER_GENDER, TEST_USER_NAME, TEST_USER_PWD, TEST_USER_AVATAR);
        this.commandRepository.save(user);
    }

//    @Test
//    public void testSave() throws Exception {
//
//    }

    @Test
    public void testRemove() throws Exception {
        Optional<User> user = this.commandRepository.load(TEST_USER_ID);
        assertTrue(user.isPresent());
        assertNotNull(user.get());

        this.commandRepository.remove(user.get());

        Optional<User> user1 = this.commandRepository.load(TEST_USER_ID);
        assertFalse(user1.isPresent());

    }

    @Test
    public void testRemoveById() throws Exception {
        Optional<User> user = this.commandRepository.load(TEST_USER_ID);
        assertTrue(user.isPresent());
        assertNotNull(user.get());

        this.commandRepository.removeById(TEST_USER_ID);

        Optional<User> user1 = this.commandRepository.load(TEST_USER_ID);
        assertFalse(user1.isPresent());
    }

    @Test
    public void testLoad() throws Exception {
        Optional<User> user = this.commandRepository.load(TEST_USER_ID);
        assertTrue(user.isPresent());
        assertNotNull(user.get());
        assertEquals(TEST_USER_ID, user.get().getId());
        assertEquals(TEST_USER_GENDER, user.get().getGender());
        assertEquals(TEST_USER_NAME, user.get().getName());
        assertEquals(TEST_USER_AVATAR, user.get().getAvatar());
        assertTrue(user.get().getPassword().check(TEST_USER_PWD));
    }
}
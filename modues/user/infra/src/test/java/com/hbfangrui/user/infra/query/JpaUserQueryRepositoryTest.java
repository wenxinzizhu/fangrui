package com.hbfangrui.user.infra.query;

import com.hbfangrui.user.base.model.*;
import com.hbfangrui.user.query.model.UserVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-jpa.xml",
        "classpath:spring-user-query-repository.xml"})
public class JpaUserQueryRepositoryTest
    extends AbstractTransactionalJUnit4SpringContextTests {
    private static final UserId TEST_USER_ID = UserIdGenerator.generate();
    private static final Gender TEST_USER_GENDER = Gender.NONE;
    private static final String TEST_USER_NAME = "userName";
    private static final UserStatus TEST_USER_STATUS = UserStatus.apply(1);
    private static final UserPassword TEST_USER_PWD = UserPassword.apply("userPassword");
    private static final String TEST_USER_AVATAR = "userAvatar";

    @Autowired
    private JpaUserQueryRepository queryRepository;

    @Before
    public void setUp() throws Exception {
        UserVo userVo = UserVo.apply(TEST_USER_ID, TEST_USER_STATUS, TEST_USER_GENDER, TEST_USER_NAME, TEST_USER_PWD, TEST_USER_AVATAR);
        queryRepository.save(userVo);
    }

//    @Test
//    public void testSave() throws Exception {
//
//    }

    @Test
    public void testGetById() throws Exception {
        CompletableFuture<Optional<UserVo>> userVoFuture = this.queryRepository.getById(TEST_USER_ID);
        UserVo user = userVoFuture.get().get();
        assertEquals(TEST_USER_ID, user.getId());
        assertEquals(TEST_USER_GENDER, user.getGender());
        assertEquals(TEST_USER_NAME, user.getName());
        assertEquals(TEST_USER_STATUS, user.getStatus());
        assertEquals(TEST_USER_PWD, user.getPassword());
        assertEquals(TEST_USER_AVATAR, user.getAvatar());
    }

    @Test
    public void testGetByIds() throws Exception {
        CompletableFuture<List<UserVo>> usersVosFuture = this.queryRepository.getByIds(Arrays.asList(TEST_USER_ID));
        List<UserVo> userVos = usersVosFuture.get();
        assertFalse(userVos.isEmpty());
    }
}
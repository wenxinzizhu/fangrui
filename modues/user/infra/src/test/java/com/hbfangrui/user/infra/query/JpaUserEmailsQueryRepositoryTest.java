package com.hbfangrui.user.infra.query;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserIdGenerator;
import com.hbfangrui.user.query.model.UserEmailsVo;
import com.hbfangrui.user.query.repository.UserEmailsQueryRepository;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-jpa.xml",
        "classpath:spring-user-query-repository.xml"})
public class JpaUserEmailsQueryRepositoryTest
    extends AbstractTransactionalJUnit4SpringContextTests{
    private static final UserId TEST_USER_ID = UserIdGenerator.generate();
    private static final Email TEST_EMAIL_1 = Email.apply("litao1@163.com");
    private static final Email TEST_EMAIL_2 = Email.apply("litao2@163.com");

    @Autowired
    private UserEmailsQueryRepository userEmailsQueryRepository;

    @Before
    public void seUp(){
        UserEmailsVo vo = UserEmailsVo.apply(TEST_USER_ID, Arrays.asList(TEST_EMAIL_1, TEST_EMAIL_2));
        this.userEmailsQueryRepository.save(vo);
    }


    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {
        CompletableFuture<Optional<UserEmailsVo>> optionalCompletableFuture = this.userEmailsQueryRepository.getById(TEST_USER_ID);
        Optional<UserEmailsVo> vo = optionalCompletableFuture.get();
        assertTrue(vo.isPresent());
        assertTrue(vo.get().getEmail(TEST_EMAIL_1).isPresent());
        assertTrue(vo.get().getEmail(TEST_EMAIL_2).isPresent());
    }

    @Test
    public void testGetByIds() throws Exception {
        CompletableFuture<List<UserEmailsVo>> vosFuture = this.userEmailsQueryRepository.getByIds(Arrays.asList(TEST_USER_ID));
        List<UserEmailsVo> vos = vosFuture.get();
        assertFalse(vos.isEmpty());
    }
}
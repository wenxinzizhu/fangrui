package com.hbfangrui.user.infra.query;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserIdGenerator;
import com.hbfangrui.user.query.model.UserPhonesVo;
import com.hbfangrui.user.query.repository.UserPhonesQueryRepository;
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
public class JpaUserPhonesQueryRepositoryTest
    extends AbstractTransactionalJUnit4SpringContextTests{
    private static final UserId TEST_USER_ID = UserIdGenerator.generate();
    private static final Phone TEST_EMAIL_1 = Phone.apply("18629817027");
    private static final Phone TEST_EMAIL_2 = Phone.apply("18620918927");

    @Autowired
    private UserPhonesQueryRepository userPhonesQueryRepository;

    @Before
    public void seUp(){
        UserPhonesVo vo = UserPhonesVo.apply(TEST_USER_ID, Arrays.asList(TEST_EMAIL_1, TEST_EMAIL_2));
        this.userPhonesQueryRepository.save(vo);
    }


    @Test
    public void testSave() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {
        CompletableFuture<Optional<UserPhonesVo>> optionalCompletableFuture = this.userPhonesQueryRepository.getById(TEST_USER_ID);
        Optional<UserPhonesVo> vo = optionalCompletableFuture.get();
        assertTrue(vo.isPresent());
        assertTrue(vo.get().getPhone(TEST_EMAIL_1).isPresent());
        assertTrue(vo.get().getPhone(TEST_EMAIL_2).isPresent());
    }

    @Test
    public void testGetByIds() throws Exception {
        CompletableFuture<List<UserPhonesVo>> vosFuture = this.userPhonesQueryRepository.getByIds(Arrays.asList(TEST_USER_ID));
        List<UserPhonesVo> vos = vosFuture.get();
        assertFalse(vos.isEmpty());
    }
}
package com.hbfangrui.user.infra.command;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserIdGenerator;
import com.hbfangrui.user.command.domain.model.UserPhone;
import com.hbfangrui.user.command.domain.model.UserPhones;
import com.hbfangrui.user.command.domain.repository.UserPhonesCommandRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-jpa.xml",
        "classpath:spring-user-command-repository.xml"})
public class JpaUserPhonesCommandRepositoryTest
        extends AbstractTransactionalJUnit4SpringContextTests{
    private static final UserId TEST_USER_ID = UserIdGenerator.generate();
    private static final Phone TEST_PHONE_1 = Phone.apply("18610087927");
    private static final Phone TEST_PHONE_2 = Phone.apply("18620991287");

    @Autowired
    private UserPhonesCommandRepository userPhonesCommandRepository;

    @Before
    public void setUp() throws Exception {
        UserPhones userPhones = UserPhones.apply(TEST_USER_ID);
        userPhones.addPhone(TEST_PHONE_1);
        userPhones.addPhone(TEST_PHONE_2);
        this.userPhonesCommandRepository.save(userPhones);
        checkUserPhones(TEST_USER_ID, uPhones -> {
            assertEquals(TEST_USER_ID, uPhones.getOwner());
            {
                Optional<UserPhone> userPhone = uPhones.getDefaultPhone();
                assertFalse(userPhone.isPresent());
            }
            {
                Optional<UserPhone> userPhone = uPhones.getPhone(TEST_PHONE_1);
                assertTrue(userPhone.isPresent());
                assertEquals(TEST_PHONE_1, userPhone.get().getPhone());
                assertEquals(TEST_USER_ID, userPhone.get().getOwner());
                assertEquals(UserPhones.DEFAULT_PHONE_STATUS, userPhone.get().getStatus());
            }
            {
                Optional<UserPhone> userPhone = uPhones.getPhone(TEST_PHONE_2);
                assertTrue(userPhone.isPresent());
                assertEquals(TEST_PHONE_2, userPhone.get().getPhone());
                assertEquals(TEST_USER_ID, userPhone.get().getOwner());
                assertEquals(UserPhones.DEFAULT_PHONE_STATUS, userPhone.get().getStatus());
            }
        });
    }

    @Test
    public void testSave() throws Exception {
        Optional<UserPhones> userPhones = this.userPhonesCommandRepository.load(TEST_USER_ID);
        userPhones.get().active(TEST_PHONE_1);
        this.userPhonesCommandRepository.save(userPhones.get());
        checkUserPhones(TEST_USER_ID, uPhones -> {
            assertEquals(TEST_USER_ID, uPhones.getOwner());
            {
                Optional<UserPhone> userPhone = uPhones.getDefaultPhone();
                assertTrue(userPhone.isPresent());
                assertEquals(TEST_PHONE_1, userPhone.get().getPhone());
                assertEquals(TEST_USER_ID, userPhone.get().getOwner());
                assertTrue(userPhone.get().getStatus().isActivated());
            }
            {
                Optional<UserPhone> userPhone = uPhones.getPhone(TEST_PHONE_1);
                assertTrue(userPhone.isPresent());
                assertEquals(TEST_PHONE_1, userPhone.get().getPhone());
                assertEquals(TEST_USER_ID, userPhone.get().getOwner());
                assertTrue(userPhone.get().getStatus().isActivated());

            }
            {
                Optional<UserPhone> userPhone = uPhones.getPhone(TEST_PHONE_2);
                assertTrue(userPhone.isPresent());
                assertEquals(TEST_PHONE_2, userPhone.get().getPhone());
                assertEquals(TEST_USER_ID, userPhone.get().getOwner());
                assertFalse(userPhone.get().getStatus().isActivated());
            }
        });
    }

    @Test
    public void testRemove() throws Exception {
        checkUserPhones(TEST_USER_ID, userPhones -> {
            assertNotNull(userPhones);
        });
        this.userPhonesCommandRepository.removeById(TEST_USER_ID);
        Optional<UserPhones> userPhones = this.userPhonesCommandRepository.load(TEST_USER_ID);
        assertFalse(userPhones.isPresent());
    }

    @Test
    public void testRemoveById() throws Exception {

    }

    @Test
    public void testLoad() throws Exception {

    }

    @Test
    public void testGetByPhone(){
        {
            List<UserPhone> userPhones = this.userPhonesCommandRepository.getByPhone(TEST_PHONE_1);
            assertFalse(userPhones.isEmpty());
        }
        {
            List<UserPhone> userPhones = this.userPhonesCommandRepository.getByPhone(TEST_PHONE_2);
            assertFalse(userPhones.isEmpty());
        }
    }

    private void checkUserPhones(UserId userId, Consumer<UserPhones> consumer){
        Optional<UserPhones> userPhones = this.userPhonesCommandRepository.load(userId);
        assertTrue(userPhones.isPresent());
        consumer.accept(userPhones.get());
    }
}
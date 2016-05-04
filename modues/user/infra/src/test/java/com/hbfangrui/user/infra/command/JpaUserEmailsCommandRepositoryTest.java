package com.hbfangrui.user.infra.command;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserIdGenerator;
import com.hbfangrui.user.command.domain.model.UserEmail;
import com.hbfangrui.user.command.domain.model.UserEmails;
import com.hbfangrui.user.command.domain.repository.UserEmailsCommandRepository;
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
public class JpaUserEmailsCommandRepositoryTest
    extends AbstractTransactionalJUnit4SpringContextTests{
    private static final UserId TEST_USER_ID = UserIdGenerator.generate();
    private static final Email TEST_EMAIL_1 = Email.apply("litao1@163.com");
    private static final Email TEST_EMAIL_2 = Email.apply("litao2@163.com");

    @Autowired
    private UserEmailsCommandRepository userEmailsCommandRepository;

    @Before
    public void setUp() throws Exception {
        UserEmails userEmails = UserEmails.apply(TEST_USER_ID);
        userEmails.addEmail(TEST_EMAIL_1);
        userEmails.addEmail(TEST_EMAIL_2);
        this.userEmailsCommandRepository.save(userEmails);
        checkUserEmails(TEST_USER_ID, uEmails->{
            assertEquals(TEST_USER_ID, uEmails.getOwner());
            {
                Optional<UserEmail> userEmail = uEmails.getDefaultEmail();
                assertFalse(userEmail.isPresent());
            }
            {
                Optional<UserEmail> userEmail = uEmails.getEmail(TEST_EMAIL_1);
                assertTrue(userEmail.isPresent());
                assertEquals(TEST_EMAIL_1, userEmail.get().getEmail());
                assertEquals(TEST_USER_ID, userEmail.get().getOwner());
                assertEquals(UserEmails.DEFAULT_EMAIL_STATUS, userEmail.get().getStatus());
            }
            {
                Optional<UserEmail> userEmail = uEmails.getEmail(TEST_EMAIL_2);
                assertTrue(userEmail.isPresent());
                assertEquals(TEST_EMAIL_2, userEmail.get().getEmail());
                assertEquals(TEST_USER_ID, userEmail.get().getOwner());
                assertEquals(UserEmails.DEFAULT_EMAIL_STATUS, userEmail.get().getStatus());
            }
        });
    }

    @Test
    public void testSave() throws Exception {
        Optional<UserEmails> userEmails = this.userEmailsCommandRepository.load(TEST_USER_ID);
        userEmails.get().active(TEST_EMAIL_1);
        this.userEmailsCommandRepository.save(userEmails.get());
        checkUserEmails(TEST_USER_ID, uEmails->{
            assertEquals(TEST_USER_ID, uEmails.getOwner());
            {
                Optional<UserEmail> userEmail = uEmails.getDefaultEmail();
                assertTrue(userEmail.isPresent());
                assertEquals(TEST_EMAIL_1, userEmail.get().getEmail());
                assertEquals(TEST_USER_ID, userEmail.get().getOwner());
                assertTrue(userEmail.get().getStatus().isActivated());
            }
            {
                Optional<UserEmail> userEmail = uEmails.getEmail(TEST_EMAIL_1);
                assertTrue(userEmail.isPresent());
                assertEquals(TEST_EMAIL_1, userEmail.get().getEmail());
                assertEquals(TEST_USER_ID, userEmail.get().getOwner());
                assertTrue(userEmail.get().getStatus().isActivated());

            }
            {
                Optional<UserEmail> userEmail = uEmails.getEmail(TEST_EMAIL_2);
                assertTrue(userEmail.isPresent());
                assertEquals(TEST_EMAIL_2, userEmail.get().getEmail());
                assertEquals(TEST_USER_ID, userEmail.get().getOwner());
                assertFalse(userEmail.get().getStatus().isActivated());
            }
        });
    }

    @Test
    public void testRemove() throws Exception {
        checkUserEmails(TEST_USER_ID, userEmails -> {
            assertNotNull(userEmails);
        });
        this.userEmailsCommandRepository.removeById(TEST_USER_ID);
        Optional<UserEmails> userEmails = this.userEmailsCommandRepository.load(TEST_USER_ID);
        assertFalse(userEmails.isPresent());
    }

    @Test
    public void testRemoveById() throws Exception {

    }

    @Test
    public void testLoad() throws Exception {

    }

    @Test
    public void testGetByEmail(){
        {
            List<UserEmail> userEmails = this.userEmailsCommandRepository.getByEmail(TEST_EMAIL_1);
            assertFalse(userEmails.isEmpty());
        }
        {
            List<UserEmail> userEmails = this.userEmailsCommandRepository.getByEmail(TEST_EMAIL_2);
            assertFalse(userEmails.isEmpty());
        }
    }

    private void checkUserEmails(UserId userId, Consumer<UserEmails> consumer){
        Optional<UserEmails> userEmails = this.userEmailsCommandRepository.load(userId);
        assertTrue(userEmails.isPresent());
        consumer.accept(userEmails.get());
    }
}
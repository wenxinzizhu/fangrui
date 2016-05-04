package com.hbfangrui.user.query.model;

import com.google.common.collect.Lists;
import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.user.base.model.UserEmailStatus;
import com.hbfangrui.user.base.model.UserEmailsBase;
import com.hbfangrui.user.base.model.UserId;
import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by taoli on 15/11/4.
 */
@Entity
@Table(name = "t_user_emails")
public class UserEmailsVo extends UserEmailsBase<UserEmailVo>
    implements Serializable{
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "emails_id", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private List<UserEmailVo> emails = Lists.newArrayList();
    @Override
    protected List<UserEmailVo> getUserEmails() {
        return emails;
    }

    public static UserEmailsVo apply(UserId owner, List<Email> emails) {
        UserEmailsVo userEmailsVo = new UserEmailsVo();
        userEmailsVo.setId(owner);
        userEmailsVo.setEmails(emails.stream()
                .map(email -> UserEmailVo.apply(owner, email, UserEmailStatus.apply(0)))
                .collect(toList()));
        return userEmailsVo;
    }
}

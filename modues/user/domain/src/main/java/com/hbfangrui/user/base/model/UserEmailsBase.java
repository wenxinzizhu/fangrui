package com.hbfangrui.user.base.model;

import com.hbfangrui.base.ddd.domain.model.support.Email;
import com.hbfangrui.base.ddd.domain.model.support.Model;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by taoli on 15/11/1.
 */
@MappedSuperclass
@Data
public abstract class UserEmailsBase<USEREMAIL extends UserEmailBase> extends Model implements Serializable {
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PROTECTED)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;

    @AttributeOverride(name = "id", column = @Column(name = "owner", nullable = false, updatable = false))
    @Embedded
    @Setter(AccessLevel.PROTECTED)
    private UserId id;

    @Setter(AccessLevel.PROTECTED)
    @Getter(AccessLevel.PRIVATE)
//    @Column(name = "email_default", nullable = false)
    @AttributeOverrides({
            @AttributeOverride(name = "domain", column = @Column(name = "default_email_domain")),
            @AttributeOverride(name = "name", column = @Column(name = "default_email_name"))
    })
    private Email defEmail;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "email_count", nullable = false)
    private int emailCount = 0;

    public UserId getOwner(){
        return getId();
    }

    protected void setOwner(UserId userId){
        setId(userId);
    }


    public Optional<USEREMAIL> getDefaultEmail(){
        return getUserEmails().stream()
                 .filter(email -> email.getEmail().equals(getDefEmail())).findFirst();

    }

    public List<USEREMAIL> getAllEmails(){
        return Collections.unmodifiableList(getUserEmails());
    }


    public Optional<USEREMAIL> getEmail(Email email) {
        return getAllEmails().stream().filter(userEmail->userEmail.getEmail().equals(email)).findFirst();
    }

    public boolean containsEmail(Email email){
        return getEmail(email).isPresent();
    }

    public boolean containsEmail(String email){
        return containsEmail(Email.apply(email));
    }

    public Optional<USEREMAIL> getEmail(String email){
        return getEmail(Email.apply(email));
    }
    protected abstract List<USEREMAIL> getUserEmails();

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "EAF_ORDER_ID", nullable = false)
//    private List<EMAIL> emails;
}

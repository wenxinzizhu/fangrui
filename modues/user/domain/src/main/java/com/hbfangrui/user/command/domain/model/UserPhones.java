package com.hbfangrui.user.command.domain.model;

import com.google.common.collect.Lists;
import com.hbfangrui.base.ddd.domain.model.es.ESAggregate;
import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.base.model.UserId;
import com.hbfangrui.user.base.model.UserPhoneStatus;
import com.hbfangrui.user.base.model.UserPhonesBase;
import com.hbfangrui.user.command.domain.event.phone.UserPhoneActivatedEvent;
import com.hbfangrui.user.command.domain.event.phone.UserPhoneAddedEvent;
import com.hbfangrui.user.command.domain.event.phone.UserPhoneDeletedEvent;
import com.hbfangrui.user.command.domain.event.phone.UserPhoneSetDefaultEvent;
import com.hbfangrui.user.command.domain.exception.PhoneExistedException;
import com.hbfangrui.user.command.domain.exception.PhoneIsDefaultException;
import com.hbfangrui.user.command.domain.exception.PhoneNotActivatedException;
import com.hbfangrui.user.command.domain.exception.PhoneNotExistException;
import lombok.AccessLevel;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

import static com.hbfangrui.user.base.model.UserPhonesChecker.checkPhone;


/**
 * Created by taoli on 15/11/1.
 */
@Entity
@Table(name = "t_user_phones")
public class UserPhones extends UserPhonesBase<UserPhone> implements ESAggregate<UserId> {
    public static final UserPhoneStatus DEFAULT_PHONE_STATUS = UserPhoneStatus.apply(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "phones_id", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private List<UserPhone> phones = Lists.newArrayList();

    public UserPhones(UserId owner) {
        setOwner(owner);
    }

    @Override
    protected List<UserPhone> getUserPhones() {
        return this.phones;
    }

    private UserPhones(){

    }
    public static UserPhones apply(UserId owner) {
        return new UserPhones(owner);
    }


    // Add Usr Phone
    public void addPhone(Phone phone) {
        checkPhone(phone);
        Optional<UserPhone> userPhone = getPhone(phone);
        if (userPhone.isPresent()){
            throw new PhoneExistedException(phone);
        }
        publishAndApply(UserPhoneAddedEvent.apply(getOwner(), getVersion(), phone));
    }

    private void apply(UserPhoneAddedEvent event){
        setOwner(event.getId());
        this.phones.add(UserPhone.apply(event.getId(), event.getPhone(), DEFAULT_PHONE_STATUS));
        setPhoneCount(this.phones.size());
    }

    // Active User Phone
    public void active(Phone phone) {
        checkPhone(phone);
        Optional<UserPhone> userPhone = getPhone(phone);
        if (!userPhone.isPresent()){
            throw new PhoneNotExistException(phone);
        }
        publishAndApply(UserPhoneActivatedEvent.apply(getOwner(), getVersion(), phone));
        if (!getDefaultPhone().isPresent()){
            publishAndApply(UserPhoneSetDefaultEvent.apply(getOwner(), getVersion(), phone));
        }
    }

    private void apply(UserPhoneActivatedEvent event){
        Optional<UserPhone> userPhone = getPhone(event.getPhone());
        userPhone.get().active();
    }

    // Delete User Email
    public void deletePhone(Phone phone) {
        checkPhone(phone);
        Optional<UserPhone> userPhone = getPhone(phone);
        if (!userPhone.isPresent()){
            throw new PhoneNotExistException(phone);
        }
        Optional<UserPhone> defPhone = getDefaultPhone();
        if (defPhone.isPresent() && defPhone.get().getPhone().equals(phone)){
            throw new PhoneIsDefaultException(userPhone.get());
        }
        publishAndApply(UserPhoneDeletedEvent.apply(getOwner(), getVersion(), phone));
    }

    private void apply(UserPhoneDeletedEvent event){
        this.phones.remove(getPhone(event.getPhone()).get());
        setPhoneCount(this.phones.size());
    }


    // Set Default Email
    public void setDefaultPhone(Phone phone) {
        Optional<UserPhone> userPhone = getPhone(phone);
        if (!userPhone.isPresent()){
            throw new PhoneNotExistException(phone);
        }
        if (!userPhone.get().getStatus().isActivated()){
            throw new PhoneNotActivatedException(phone);
        }
        publishAndApply(UserPhoneSetDefaultEvent.apply(getOwner(), getVersion(), phone));
    }

    private void apply(UserPhoneSetDefaultEvent event){
        setDefPhone(event.getPhone());
    }
}

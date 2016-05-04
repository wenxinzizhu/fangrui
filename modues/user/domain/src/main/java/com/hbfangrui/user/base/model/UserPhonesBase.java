package com.hbfangrui.user.base.model;

import com.hbfangrui.base.ddd.domain.model.support.Model;
import com.hbfangrui.base.ddd.domain.model.support.Phone;
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
public abstract class UserPhonesBase<USERPHONE extends UserPhoneBase> extends Model implements Serializable {
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
    @AttributeOverrides({
            @AttributeOverride(name = "phone", column = @Column(name = "default_phone_num"))
    })
    private Phone defPhone;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "phone_count", nullable = false)
    private int phoneCount = 0;

    public UserId getOwner(){
        return getId();
    }

    protected void setOwner(UserId userId){
        setId(userId);
    }


    public Optional<USERPHONE> getDefaultPhone(){
        return getUserPhones().stream()
                 .filter(phone -> phone.getPhone().equals(getDefPhone())).findFirst();

    }

    public List<USERPHONE> getAllPhones(){
        return Collections.unmodifiableList(getUserPhones());
    }


    public Optional<USERPHONE> getPhone(Phone phone) {
        return getAllPhones().stream().filter(userPhone->userPhone.getPhone().equals(phone)).findFirst();
    }

    public boolean containsPhone(Phone phone){
        return getPhone(phone).isPresent();
    }

    public boolean containsPhone(String phone){
        return containsPhone(Phone.apply(phone));
    }

    public Optional<USERPHONE> getPhone(String phone){
        return getPhone(Phone.apply(phone));
    }
    protected abstract List<USERPHONE> getUserPhones();

//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "EAF_ORDER_ID", nullable = false)
//    private List<EMAIL> emails;
}

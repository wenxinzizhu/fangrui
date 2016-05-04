package com.hbfangrui.user.infra.command.dao;

import com.hbfangrui.base.ddd.domain.model.support.Phone;
import com.hbfangrui.user.command.domain.model.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by taoli on 15/11/3.
 */
public interface UserPhoneDao extends JpaRepository<UserPhone, Long>{
    List<UserPhone> getByPhone(Phone phone);
}

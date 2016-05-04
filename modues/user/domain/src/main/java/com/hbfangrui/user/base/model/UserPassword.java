package com.hbfangrui.user.base.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by tao.li on 2015/10/30.
 */
@Data
@Embeddable
public class UserPassword {

    @Setter(AccessLevel.PRIVATE)
    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "password", nullable = false)
    @Setter(AccessLevel.PRIVATE)
    private String password;


    public static UserPassword apply(String password) {
        String salt = createSalt();
        String encodePassword = encodePassword(salt, password);
        UserPassword userPassword = new UserPassword();
        userPassword.setSalt(salt);
        userPassword.setPassword(encodePassword);
        return userPassword;
    }

    private static String createSalt() {
        return RandomStringUtils.randomAscii(8);
    }

    public boolean check(String pwd) {
        return encodePassword(salt, pwd).equals(this.password);
    }

    private static String encodePassword(String salt, String pwd) {
        return DigestUtils.md5Hex(salt + DigestUtils.md5Hex(salt) + pwd +  DigestUtils.md5Hex(pwd) );
    }
}

package com.hbfangrui.user.base.model;


import com.hbfangrui.base.ddd.domain.model.support.StringBasedIdentifierGenerator;

/**
 * Created by taoli on 15/10/31.
 */
public class UserIdGenerator extends StringBasedIdentifierGenerator<UserId> {
    private static final UserIdGenerator GENERATOR = new UserIdGenerator();
    private UserIdGenerator() {
        super("user", 32);
    }

    @Override
    protected UserId buildIdentifier(String id) {
        return UserId.apply(id);
    }

    public static UserId generate(){
        return GENERATOR.get();
    }
}

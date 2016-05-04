package com.hbfangrui.user.infra.query.subscriber;


import com.hbfangrui.user.command.domain.model.User;
import com.hbfangrui.user.query.model.UserVo;

/**
 * Created by taoli on 15/10/31.
 */
public class UserVoConverter {
    public UserVo toUserVo(User user){
        return UserVo.apply(user.getId(), user.getStatus(),user.getGender(), user.getName(), user.getPassword(), user.getAvatar());
    }
}

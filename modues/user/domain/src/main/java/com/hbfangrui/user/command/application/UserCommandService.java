package com.hbfangrui.user.command.application;

import com.hbfangrui.base.cqrs.application.AppCommandService;
import com.hbfangrui.user.command.application.cmd.email.ActiveUserEmailCMD;
import com.hbfangrui.user.command.application.cmd.email.AddUserEmailCMD;
import com.hbfangrui.user.command.application.cmd.email.DeleteUserEmailCMD;
import com.hbfangrui.user.command.application.cmd.phone.ActiveUserPhoneCMD;
import com.hbfangrui.user.command.application.cmd.phone.AddUserPhoneCMD;
import com.hbfangrui.user.command.application.cmd.phone.DeleteUserPhoneCMD;
import com.hbfangrui.user.command.application.cmd.phone.SetDefaultPhoneCMD;
import com.hbfangrui.user.command.application.cmd.user.*;
import com.hbfangrui.user.command.application.cmd.email.SetDefaultEmailCMD;

/**
 * Created by taoli on 15/10/30.
 */
public interface UserCommandService extends AppCommandService {
    void createUser(CreateUserCMD cmd);

    void changePassword(ChangePasswordCMD cmd);

    void changeUser(ChangeUserCMD cmd);

    void disableUser(DisableUserCMD cmd);

    void enableUser(EnableUserCMD cmd);

    void activeUser(ActiveUserCMD cmd);

    void cancelUser(CancelUserCMD cmd);

    void addUserEmail(AddUserEmailCMD cmd);

    void activeUserEmail(ActiveUserEmailCMD cmd);

    void setDefaultUserEmail(SetDefaultEmailCMD cmd);

    void deleteUserEmail(DeleteUserEmailCMD cmd);

    void addUserPhone(AddUserPhoneCMD cmd);

    void activeUserPhone(ActiveUserPhoneCMD cmd);

    void setDefaultUserPhone(SetDefaultPhoneCMD cmd);

    void deleteUserPhone(DeleteUserPhoneCMD cmd);
}

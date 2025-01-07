package jmu.cdl.tutor.service;

import jakarta.validation.Valid;
import jmu.cdl.tutor.pojo.Account;
import jmu.cdl.tutor.pojo.DTO.AccountDto;
import jmu.cdl.tutor.pojo.DTO.IdDto;
import jmu.cdl.tutor.pojo.DTO.LoginAndDeleteDto;
import jmu.cdl.tutor.pojo.DTO.UpdatePasswordDto;

/**
 * AccountService 接口定义了用户账户相关的业务逻辑，包括注册、登录、修改密码和注销账户等操作。
 */
public interface AccountService {

    /**
     * 注册新用户
     * 
     * @param accountDto 包含用户注册信息的 DTO 对象
     * @return 注册成功的用户信息
     */
    Account addAccount(AccountDto accountDto);

    /**
     * 用户登录
     * 
     * @param loginAndDeleteDto 包含用户登录信息的 DTO 对象
     * @return 登录成功的用户信息
     */
    Account login(@Valid LoginAndDeleteDto loginAndDeleteDto);

    /**
     * 修改用户密码
     * 
     * @param updatePasswordDto 包含用户旧密码和新密码的 DTO 对象
     * @return 修改密码的结果
     */
    boolean updatePassword(@Valid UpdatePasswordDto updatePasswordDto);

    /**
     * 注销用户账户
     * 
     * @param loginAndDeleteDto 包含用户账号和密码的 DTO 对象
     * @return 注销账户的结果
     */
    boolean deleteAccount(@Valid LoginAndDeleteDto loginAndDeleteDto);

    String getStatus(IdDto idDto);
}
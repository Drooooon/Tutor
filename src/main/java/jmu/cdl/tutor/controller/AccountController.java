package jmu.cdl.tutor.controller;

import jakarta.validation.Valid;
import jmu.cdl.tutor.pojo.Account;
import jmu.cdl.tutor.pojo.DTO.*;
import jmu.cdl.tutor.pojo.ResponseMessage;
import jmu.cdl.tutor.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.PSource;

/**
 * AccountController 处理与用户账户相关的请求，包括注册、登录、修改密码和注销账户。
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 处理用户注册请求
     * 
     * @param accountDto 包含用户注册信息的 DTO 对象
     * @return 注册成功的用户信息
     */
    @PostMapping("add")
    public ResponseMessage<Account> add(@Valid @RequestBody AccountDto accountDto) {
        Account account = accountService.addAccount(accountDto);
        return ResponseMessage.success(account);
    }

    /**
     * 处理用户登录请求
     * 
     * @param loginAndDeleteDto 包含用户登录信息的 DTO 对象
     * @return 登录成功的用户信息
     */
    @PostMapping("login")
    public ResponseMessage<Account> login(@Valid @RequestBody LoginAndDeleteDto loginAndDeleteDto) {
        Account account = accountService.login(loginAndDeleteDto);
        if (account == null) {
            return ResponseMessage.failed(null);
        }
        return ResponseMessage.success(account);
    }

    /**
     * 处理用户修改密码请求
     * 
     * @param updatePasswordDto 包含用户旧密码和新密码的 DTO 对象
     * @return 修改密码的结果信息
     */
    @PutMapping("updatePassword")
    public ResponseMessage<String> updatePassword(@Valid @RequestBody UpdatePasswordDto updatePasswordDto) {
        boolean flag = accountService.updatePassword(updatePasswordDto);
        if (flag) {
            return ResponseMessage.success("修改完成");
        }
        return ResponseMessage.failed("密码错误！");
    }

    /**
     * 处理用户注销账户请求
     * 
     * @param loginAndDeleteDto 包含用户账号和密码的 DTO 对象
     * @return 注销账户的结果信息
     */
    @DeleteMapping("deleteAccount")
    public ResponseMessage<String> deleteAccount(@Valid @RequestBody LoginAndDeleteDto loginAndDeleteDto) {
        boolean flag = accountService.deleteAccount(loginAndDeleteDto);
        if (flag) {
            return ResponseMessage.success("注销成功，感谢您的陪伴");
        }
        return ResponseMessage.failed("账号密码错误，请重新修改");
    }

    @PostMapping("requestStatus")
    public ResponseMessage<String> requestStatus(@Valid @RequestBody IdDto idDto) {
        String status = accountService.getStatus(idDto);
        System.out.println("cdl66668");

        return ResponseMessage.success(status);
    }
}
package jmu.cdl.tutor.service.Impl;

import jakarta.validation.Valid;
import jmu.cdl.tutor.dao.*;
import jmu.cdl.tutor.pojo.Account;
import jmu.cdl.tutor.pojo.Customer;
import jmu.cdl.tutor.pojo.DTO.AccountDto;
import jmu.cdl.tutor.pojo.DTO.IdDto;
import jmu.cdl.tutor.pojo.DTO.LoginAndDeleteDto;
import jmu.cdl.tutor.pojo.DTO.UpdatePasswordDto;
import jmu.cdl.tutor.pojo.Teacher;
import jmu.cdl.tutor.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AccountServiceImpl 实现了 AccountService 接口，处理用户账户相关的业务逻辑，包括注册、登录、修改密码和注销账户等操作。
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StuSubjectDao stuSubjectDao;


    /**
     * 注册新用户
     * 
     * @param accountDto 包含用户注册信息的 DTO 对象
     * @return 注册成功的用户信息
     */
    @Transactional
    @Override
    public Account addAccount(AccountDto accountDto) {
        int id = 0;
        if (accountDto.getUserType().equals("teacher")) {
            Teacher teacher = new Teacher();
            teacher.setName(accountDto.getName());
            teacher.setEmail(accountDto.getEmail());
            teacher.setStatus("PENDING");
            teacherDao.save(teacher);
            id = teacher.getId();
        } else if (accountDto.getUserType().equals("customer")) {
            Customer customer = new Customer();
            customer.setName(accountDto.getName());
            customer.setEmail(accountDto.getEmail());
            customerDao.save(customer);
            id = customer.getId();
        }

        Account account = new Account();
        account.setName(accountDto.getName());
        account.setId(id);
        account.setPassword(accountDto.getPassword());
        account.setUserType(accountDto.getUserType());
        accountDao.save(account);
        return account;
    }

    /**
     * 用户登录
     * 
     * @param loginAndDeleteDto 包含用户登录信息的 DTO 对象
     * @return 登录成功的用户信息
     */
    @Override
    public Account login(@Valid LoginAndDeleteDto loginAndDeleteDto) {
        Account account = accountDao.findById(loginAndDeleteDto.getId()).orElse(null);
        if (account == null) {
            return null;
        } else {
            if (account.getPassword().equals(loginAndDeleteDto.getPassword())) {
                return account;
            } else {
                return null;
            }
        }
    }

    /**
     * 修改用户密码
     * 
     * @param updatePasswordDto 包含用户旧密码和新密码的 DTO 对象
     * @return 修改密码的结果
     */
    @Override
    public boolean updatePassword(UpdatePasswordDto updatePasswordDto) {
        String oldPassword = accountDao.getPasswordById(updatePasswordDto.getId());
        if (updatePasswordDto.getOldPassword().equals(oldPassword)) {
            accountDao.updatePasswordById(updatePasswordDto.getId(), updatePasswordDto.getNewPassword());
            return true;
        }
        return false;
    }

    /**
     * 注销用户账户
     * 
     * @param loginAndDeleteDto 包含用户账号和密码的 DTO 对象
     * @return 注销账户的结果
     */
    @Transactional
    @Override
    public boolean deleteAccount(LoginAndDeleteDto loginAndDeleteDto) {
        String password = loginAndDeleteDto.getPassword();
        int id = loginAndDeleteDto.getId();
        if (password.equals(accountDao.getPasswordById(id))) {
            if (accountDao.getUserTypeById(id).equals("teacher")) {
                teacherDao.deleteTeacherById(id);
            } else {
                List<Integer> ids = studentDao.getStudentsIdByCustomerId(id);
                stuSubjectDao.deleteStuSubjectById(ids);
                studentDao.deleteStudentById(id);
                customerDao.deleteCustomerById(id);
            }
//            accountDao.deleteAccount(id);
            accountDao.deleteById(id);
            return true;

        }
        return false;
    }

    @Override
    public String getStatus(IdDto idDto) {
        return teacherDao.getStatusById(idDto.getId());
    }
}
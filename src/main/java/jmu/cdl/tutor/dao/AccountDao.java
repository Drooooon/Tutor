package jmu.cdl.tutor.dao;

import jmu.cdl.tutor.pojo.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * AccountDao 接口用于定义对 Account 实体的数据库操作，包括删除账户、获取密码、获取用户类型和更新密码等操作。
 */
@Repository("accountDao")
public interface AccountDao extends CrudRepository<Account, Integer> {

    /**
     * djh
     * 根据用户ID删除账户
     * 
     * @param id 用户ID
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Account a where a.id = :id")
    void deleteAccount(int id);

    /**
     * 根据用户ID获取密码
     * 
     * @param id 用户ID
     * @return 用户密码
     */
    @Query("SELECT a.password FROM Account a WHERE a.id = :id")
    String getPasswordById(int id);

    /**
     * 根据用户ID获取用户类型
     * 
     * @param id 用户ID
     * @return 用户类型
     */
    @Query("SELECT a.userType FROM Account a WHERE a.id = :id")
    String getUserTypeById(int id);

    /**
     * 根据用户ID更新密码
     * 
     * @param id       用户ID
     * @param password 新密码
     */
    @Modifying
    @Transactional
    @Query("UPDATE Account a SET a.password = :password WHERE a.id = :id")
    void updatePasswordById(int id, String password);
}
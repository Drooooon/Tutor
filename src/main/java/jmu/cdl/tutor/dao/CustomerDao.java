package jmu.cdl.tutor.dao;

import jmu.cdl.tutor.pojo.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * CustomerDao 接口用于定义对 Customer 实体的数据库操作，包括删除客户等操作。
 */
@Repository("customerDao")
public interface CustomerDao extends CrudRepository<Customer, Integer> {

    /**
     * 根据客户ID删除客户
     * 
     * @param id 客户ID
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Customer c WHERE c.id = :id")
    void deleteCustomerById(int id);
}
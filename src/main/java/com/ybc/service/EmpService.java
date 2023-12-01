package com.ybc.service;

import com.ybc.pojo.Emp;
import com.ybc.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    Emp login(Emp emp);

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender,LocalDate begin,LocalDate end);

    /**
     * 删除用户
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 添加员工
     * @param emp
     */
    void add(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);
}

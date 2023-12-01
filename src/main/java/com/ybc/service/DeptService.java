package com.ybc.service;

import com.ybc.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询
     * @return
     */
    List<Dept> list();

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 添加
     * @param dept
     */
    void insert(Dept dept);

    /**
     * 根据id查询
     * @return
     */
    List<Dept> inquiry(Integer id);

    /**
     * 更新数据
     * @param dept
     */
    void update(Dept dept);
}

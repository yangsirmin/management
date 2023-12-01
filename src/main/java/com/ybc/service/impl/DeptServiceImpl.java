package com.ybc.service.impl;

import com.ybc.anno.Log;
import com.ybc.mapper.DeptMapper;
import com.ybc.mapper.EmpMapper;
import com.ybc.pojo.Dept;
import com.ybc.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Log
    @Transactional
    @Override
    public void delete(Integer id) {
        deptMapper.delete(id);
        empMapper.deleteByDeptId(id);
    }

    @Log
    @Override
    public void insert(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public List<Dept> inquiry(Integer id) {
        return deptMapper.inquiry(id);
    }

    @Log
    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}

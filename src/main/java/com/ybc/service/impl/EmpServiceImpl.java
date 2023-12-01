package com.ybc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ybc.anno.Log;
import com.ybc.mapper.EmpMapper;
import com.ybc.pojo.Emp;
import com.ybc.pojo.PageBean;
import com.ybc.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        long count = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.page(start,pageSize);
//        return new PageBean(count,empList);
//    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getUserByUsernameAndPassword(emp);
    }

    /**
     * PageHelper插件
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //1.分页设置
        PageHelper.startPage(page,pageSize);
        //2.执行查询
        List<Emp> list = empMapper.list(name,gender,begin,end);
        Page<Emp> p = (Page<Emp>) list;

        return new PageBean(p.getTotal(),p.getResult());
    }

    @Log
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Log
    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getByid(id);
    }

    @Log
    @Override
    public void update(Emp emp) {
        empMapper.update(emp);
    }
}

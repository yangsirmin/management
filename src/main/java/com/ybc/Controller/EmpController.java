package com.ybc.Controller;

import com.ybc.pojo.Emp;
import com.ybc.pojo.PageBean;
import com.ybc.pojo.Result;
import com.ybc.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询 页数：条数 : begin : end = {} , {} , {} , {}",
                page,pageSize,begin,end);
        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/emps/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("根据id删除用户：" + ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp){
        empService.add(emp);
        return Result.success();
    }

    @GetMapping("/emps/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询:{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @PutMapping("/emps")
    public Result upate(@RequestBody Emp emp){
        log.info("更新信息:{}",emp);
        empService.update(emp);
        return Result.success();
    }
}

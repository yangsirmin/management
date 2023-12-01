package com.ybc.Controller;

import com.ybc.pojo.Dept;
import com.ybc.pojo.Result;
import com.ybc.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

   // private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @GetMapping()
    public Result list(){
        log.info("查询全部部门成功");
        List<Dept> deptList =  deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：" + id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping()
    public Result add(@RequestBody Dept dept){
        log.info("根据name添加部门:" + dept.getName());
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result inquiry(@PathVariable Integer id){
        log.info("根据id查询用户:" + id);
        List<Dept> list = deptService.inquiry(id);
        return Result.success(list);
    }

    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("更新数据：" + dept.getName());
        deptService.update(dept);
        return Result.success();
    }
}

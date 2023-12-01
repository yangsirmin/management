package com.ybc.mapper;

import com.ybc.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询全部的数据
     * @return
     */
    @Select("select id, name, create_time, update_time from tlias.dept")
    List<Dept> list();

    /**
     * 根据id删除部门
     */
    @Delete("delete from tlias.dept where id = #{id}")
    void delete(Integer id);

    /**
     * 添加
     * @param dept
     */
    @Insert("insert into tlias.dept(name, create_time, update_time) VALUES (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select * from tlias.dept where id = #{id}")
    List<Dept> inquiry(Integer id);

    @Update("update tlias.dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}

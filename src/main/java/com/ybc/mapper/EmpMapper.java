package com.ybc.mapper;

import com.ybc.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    /**
//     * 查询数据总数
//     * @return
//     */
//    @Select("select count(*) from tlias.emp")
//    long count();

//    /**
//     * 分页查询
//     * @param start
//     * @param pageSize
//     * @return
//     */
//    @Select("select * from tlias.emp limit #{start},#{pageSize}")
//    List<Emp> page(Integer start, Integer pageSize);

    //@Select("select * from tlias.emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    /**
     * 添加员工
     * @param emp
     */
    @Insert("insert into tlias.emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) values " +
            "(#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void add(Emp emp);

    @Select("select * from tlias.emp where id = #{id}")
    Emp getByid(Integer id);

    void update(Emp emp);

    @Select("select * from tlias.emp where username = #{username} and password = #{password}")
    Emp getUserByUsernameAndPassword(Emp emp);

    @Delete("delete from tlias.emp where dept_id = #{id}")
    void deleteByDeptId(Integer id);
}

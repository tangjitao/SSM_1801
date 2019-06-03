package com.huayu.ssm.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huayu.ssm.bean.Student;
import com.huayu.ssm.sql.StudentSql;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Results({
            @Result(column = "cid",property = "classes",one = @One(select = "com.huayu.ssm.dao.ClassesMapper.query_cla",fetchType = FetchType.EAGER))
    })
    @SelectProvider(type = StudentSql.class,method = "selset")
    public List<Student> queryAll(Student student);

    void update(Wrapper wrapper);

//    @Insert("insert into student values(null,#{name},#{sex},#{age},#{position},#{cid})")
//    public void insert(Student student);
//
//    @Delete("delete from student where id=#{value}")
//    public void dalete(int id);
//
    @Select("select * from student where id=#{value}")
    public Student toupdate(int id);
//
//    @Insert("update student set name=#{name},sex=#{sex},age=#{age},position=#{position},cid=#{cid} where id=#{id}")
//    public void update(Student student);
}

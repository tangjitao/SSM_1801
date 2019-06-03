package com.huayu.ssm.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.huayu.ssm.bean.Classes;
import com.huayu.ssm.bean.Student;
import com.huayu.ssm.dao.ClassesMapper;
import com.huayu.ssm.dao.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ClassesMapper classesMapper;

    public List<Student> queryAll(Student student){
        return studentMapper.queryAll(student);
    }

    public List<Classes> toAdd(Wrapper wrapper){
        return classesMapper.selectList(wrapper);
    }

    public void add(Student student){
        studentMapper.insert(student);
    }
    public void delete(int id){
        studentMapper.deleteById(id);
    }
    public Student toupdate(int id){
        return studentMapper.selectById(id);
    }
    public void update(Wrapper wrapper){
        studentMapper.update(wrapper);
    }
}

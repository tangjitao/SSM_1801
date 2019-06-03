package com.huayu.ssm.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huayu.ssm.bean.Student;
import com.huayu.ssm.dao.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuService extends ServiceImpl<StudentMapper,Student> {
    public List<Student> queryAll(Student student){
        return baseMapper.queryAll(student);
    }

    public Student toUpdate(int id){
        return baseMapper.toupdate(id);
    }
}

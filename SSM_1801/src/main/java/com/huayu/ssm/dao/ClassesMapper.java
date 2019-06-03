package com.huayu.ssm.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huayu.ssm.bean.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassesMapper extends BaseMapper<Classes> {
    @Select("select * from classes where cid=#{value}")
    public List<Classes> query_cla(int id);
//    @Select("select * from classes")
//    public List<Classes> querycla();
}

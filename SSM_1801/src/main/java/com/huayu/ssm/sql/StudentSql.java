package com.huayu.ssm.sql;

import com.huayu.ssm.bean.Student;

public class StudentSql {
    public String selset(Student student){
        StringBuffer str = new StringBuffer();
        str.append("select * from student where 1=1");
        if (student != null){
            if (student.getName() != null && !student.getName().equals("")){
                str.append(" and name like '%"+student.getName()+"%'");
            }else if(student.getSex() != null && !student.getSex().equals("")){
                str.append(" and sex = "+student.getSex());
            }else if(student.getCid() != 0){
                str.append(" and cid = "+student.getCid());
            }
        }
        return str.toString();
    }
}

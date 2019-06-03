package com.huayu.ssm.controller;

import com.github.pagehelper.PageHelper;
import com.huayu.ssm.bean.Classes;
import com.huayu.ssm.bean.DataMessage;
import com.huayu.ssm.bean.Student;
import com.huayu.ssm.service.Claservice;
import com.huayu.ssm.service.StuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StuController {
    private int num = 1;
    private int pageize = 2;
    private int count;
    private List<Student> list;
    @Autowired
    private StuService stuService;
    @Autowired
    private Claservice claservice;
    static Logger logger = LogManager.getLogger(StuController.class);
    @RequestMapping("/toquery.do")
    public String toquery(Model model) {
        List<Classes> list = claservice.list(null);
        model.addAttribute("list", list);
        return "list1";
    }

    @RequestMapping("/query.do")
    @ResponseBody
    public DataMessage query(Student student, Integer page, Integer limit) {
        logger.info("用户查询了");
        DataMessage dataMessage = new DataMessage();
        dataMessage.setCode(0);
        dataMessage.setMsg("");
        dataMessage.setCount(stuService.queryAll(student).size());
        PageHelper.startPage(page, limit, true);
        dataMessage.setData(stuService.queryAll(student));
        return dataMessage;
    }

    @RequestMapping("/queryAll.do")
    public String queryAll(Model model, Student student) {
//        Page page = PageHelper.startPage(num, pageize, true);
////        page.getTotal()获得数据库总条数
        list = stuService.queryAll(student);
//        count = (int) page.getTotal() / pageize;//获得总页数
//        if (page.getTotal() % pageize != 0) {
//            count += 1;
//        } else {
//            count = (int) page.getTotal() / pageize;
//        }
//        Wrapper<Classes> wrapper = new QueryWrapper<>();
        List<Classes> list1 = claservice.list(null);
        model.addAttribute("list", list);
        model.addAttribute("list1", list1);
//        model.addAttribute("num", num);
        return "list";
    }

    @RequestMapping("/sm.do")
    public String s() {
        if (num <= 1) {
            num = 1;
            return "redirect:/student/queryAll.do";
        } else {
            num -= 1;
            return "redirect:/student/queryAll.do";
        }
    }

    @RequestMapping("/xm.do")
    public String x() {
        if (num >= count) {
            num = count;
            return "redirect:/student/queryAll.do";
        } else {
            num += 1;
            return "redirect:/student/queryAll.do";
        }
    }

    @RequestMapping("/toAdd.do")
    public String toAdd(Model model) {
        List<Classes> list = claservice.list(null);
        model.addAttribute("list", list);
        return "add1";
    }
//    @RequestMapping("/toAdd.do")
//    @ResponseBody
//    public String toAdd(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//        request.setCharacterEncoding("utf-8");
//        response.setCharacterEncoding("utf-8");
//        response.setContentType("text/html");
//        List<Classes> list = claservice.list(null);
//        String str = "<option value=''>请选择</option>";
//        for (Classes i : list) {
//            str += "<option value="+i.getCid()+">"+i.getCname()+"</option>";
//        }
//        str=java.net.URLEncoder.encode(str);
//        return str;
//    }

    //    @RequestMapping("/add.do")
//    public String add(Student student) {
//        stuService.save(student);
//        return "redirect:/student/queryAll.do";
//    }
    @RequestMapping("/add.do")
    public String add(Student student) {
        logger.info(student.getName()+"被加入了");
        stuService.save(student);
        return "add1";
    }

    //    @RequestMapping("/delete/{id}.do")
//    public String delete(@PathVariable(value = "id") int id) {
//        stuService.removeById(id);
//        return "redirect:/student/queryAll.do";
//    }
    @RequestMapping("/delete/{id}.do")
    @ResponseBody
    public boolean delete(@PathVariable(value = "id") int id) {
        logger.info("删除了ID为"+id+"的用户");
        boolean b = stuService.removeById(id);
        return b;
    }
    @RequestMapping("/deletes.do")
    @ResponseBody
    public boolean deletes(String[] ids) {
        return stuService.removeByIds(Arrays.asList(ids));
    }

    @RequestMapping("/toupdate/{id}.do")
    public String toupdate(@PathVariable(value = "id") int id, Model model) {
        Student student = stuService.toUpdate(id);
        List<Classes> list = claservice.list(null);
        model.addAttribute("stu", student);
        model.addAttribute("list", list);
        return "update1";
    }

    @RequestMapping("/update.do")
    public String update(Student student) {
        logger.info("ID为"+student.getId()+"的用户被修改了");
        stuService.updateById(student);
        return "list1";
    }

//    @RequestMapping("/xiazai.do")
//    public void xiazai(HttpServletResponse response) throws IOException {
//        String i = "学生信息管理";
//        i = new String(i.getBytes(), "utf-8");
//        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(i + ".xls", "UTF-8"));
//        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
//
//        OutputStream out = response.getOutputStream();
//
//        String[] str = {"姓名", "性别", "年龄", "学号", "班级"};
//        HSSFWorkbook workbook = POI.export(str, list);
//        workbook.write(out);
//        out.flush();
//        out.close();
//
//    }

    @RequestMapping("/uplaod.do")
    public String shangChuan(@RequestParam("filename") MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        List<Student> list = POI.upload(is);
        stuService.saveBatch(list);
        return "redirect:/student/queryAll.do";
    }
}











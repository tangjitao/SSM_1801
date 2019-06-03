package com.huayu.ssm.controller;

import com.huayu.ssm.bean.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * POI
 */
public class POI {
    public static HSSFWorkbook export(String[] hide, List<Student> list){
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("学生信息管理");//设置文件下载名称
        HSSFRow row=hssfSheet.createRow(0);//设置当前第1行
        for(int i=0;i<hide.length;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(hide[i]);//设置头的值
        }
        for(int i=0;i<list.size();i++){//循环集合里面的值
            HSSFRow row1=hssfSheet.createRow(i+1);
            for(int j=0;j<hide.length;j++){//匹配头和循环后的值做对应
                HSSFCell cell = row1.createCell(j);
                if(hide[j].equals("姓名")){
                    cell.setCellValue(list.get(i).getName());
                }else if(hide[j].equals("性别")){
                    cell.setCellValue(list.get(i).getSex());
                }else if(hide[j].equals("年龄")){
                    cell.setCellValue(list.get(i).getAge());
                }else if(hide[j].equals("学号")){
                    cell.setCellValue(list.get(i).getPosition());
                }else if(hide[j].equals("班级")){
                    cell.setCellValue(list.get(i).getClasses().getCname());
                }
            }
        }
        return hssfWorkbook;
    }

    public static List upload(InputStream is) throws IOException {
        Workbook hssfWorkbookFactory=WorkbookFactory.create(is);
        List list = new ArrayList<>();
        Sheet sheet= hssfWorkbookFactory.getSheetAt(0);
        int num= sheet.getLastRowNum();
        for(int i=0;i<num;i++){
            Student student=new Student();
            Row row= sheet.getRow(i+1);
            int cells = row.getPhysicalNumberOfCells();
            for(int j=0;j<cells;j++){
                Cell cell =  row.getCell(j);
                String value = null;
                if(cell.getCellType()==CellType.STRING){
                    value = cell.getStringCellValue();
                }else if(cell.getCellType()==CellType.NUMERIC){
                    value = String.valueOf(cell.getNumericCellValue());
                    value = value.replace(",","");
                }if(sheet.getRow(0).getCell(j).getStringCellValue().equals("姓名")){
                    student.setName(value);
                }else if(sheet.getRow(0).getCell(j).getStringCellValue().equals("性别")){
                    student.setSex(value);
                }else if(sheet.getRow(0).getCell(j).getStringCellValue().equals("年龄")){
                    student.setAge(Integer.parseInt(value.substring(0,value.indexOf("."))));
                }else if(sheet.getRow(0).getCell(j).getStringCellValue().equals("学号")){
                    student.setPosition(value);
                }else if(sheet.getRow(0).getCell(j).getStringCellValue().equals("班级")){
                    student.setCid(Integer.parseInt(value.substring(0,value.indexOf("."))));
                }
                list.add(student);
            }
        }
        return list;
    }

}
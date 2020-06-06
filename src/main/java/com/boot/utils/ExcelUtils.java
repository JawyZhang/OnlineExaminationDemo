package com.boot.utils;

import com.boot.pojo.Student;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Mango
 * @Date 2020-05-30 15:27
 */
public class ExcelUtils {
    public static List<Student> readExcel(InputStream is) {
        List<Student> list = new ArrayList<>();
        try {
            Workbook workbook = Workbook.getWorkbook(is);
            // 为0号页签创建一个Sheet对象
            Sheet sheet = workbook.getSheet(0);
            // sheet.getRows()返回该页的总行数
            for (int i = 0; i < sheet.getRows(); i++) {
                //如果存在表头则跳过表头
                if ("学号".equals(sheet.getCell(0, i).getContents())) {
                    continue;
                }
                // 只读三列值，分别为学号，姓名和班级
                Student student = new Student();
                for (int j = 0; j < 3; j++) {
                    String content = sheet.getCell(j, i).getContents();
                    if (j == 0) {
                        student.setStu_no(content);
                        //设置密码为字符串的后六位
                        student.setPassword(content);
                    } else if (j == 1) {
                        student.setUsername(content);
                    } else {
                        student.setClass_room(content);
                    }
                }
                list.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        return list;
    }
}

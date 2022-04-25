package com.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.oa.pojo.Duty;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelOperate {

    public static void main(String[] args) {
        // 创建Excel表格
//        createExcel(getStudent());
    }

    /**
     * 初始化数据
     *
     * @return 数据
     */
    private static List<Student> getStudent() {
        List<Student> list = new ArrayList<Student>();
        Student student1 = new Student("小明", 18, "二年级");
        Student student2 = new Student("小光", 19, "三年级");
        Student student3 = new Student("小花", 20, "四年级");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        return list;
    }

    /**
     * 创建Excel
     * @param list
     *            数据
     */
    public static void createExcel(List<Duty> list) throws IOException {
        // 创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个Excel文件中的一个工作表
        HSSFSheet sheet = workbook.createSheet("5月考勤");
        //合并（从第一行到第一行的第一列到第三列合并）
        CellRangeAddress region = new CellRangeAddress(
                0, // 行起始：第1行
                0, // 行结束：第1行
                0, // 列起始：第1列
                5  // 列结束：第6列
        );
        sheet.addMergedRegion(region);
        HSSFRow hssfRow = sheet.createRow(0);
        HSSFCell headCell = hssfRow.createCell(0);
        headCell.setCellValue("5月份考勤数据");  //第一行的表描述信息

        // 设置单元格格式居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  //所有的单元格居中对齐

        /*
       //这里是样式设置
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       // cellStyle.setFillBackgroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);

        HSSFFont font = workbook.createFont();
        font.setFontName("楷体"); //字体
        font.setFontHeightInPoints((short)30); //字体大小
        font.setColor(HSSFColor.RED.index);//颜色
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
        font.setItalic(true); //倾斜
        cellStyle.setFont(font);
        */
        headCell.setCellStyle(cellStyle);

//表头每列的描述信息
        // 添加表头行
        hssfRow = sheet.createRow(1);//第二行
        // 添加表头内容
        headCell = hssfRow.createCell(0);  //列号，从0开始计数，表示第一列
        headCell.setCellValue("用户名");//第2行第1列的内容
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(1);//表示第二列
        headCell.setCellValue("真实姓名");//第2行第2列的内容
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(2);//表示第三列
        headCell.setCellValue("所属部门");//第2行第3列的内容
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(3);//表示第四列
        headCell.setCellValue("出勤日期");//第2行第4列的内容
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(4);//表示第5列
        headCell.setCellValue("签到时间");//第2行第5列的内容
        headCell.setCellStyle(cellStyle);

        headCell = hssfRow.createCell(5);//表示第六列
        headCell.setCellValue("签退时间");//第2行第6列的内容
        headCell.setCellStyle(cellStyle);



        // 添加数据内容，数据内容从第三行开始
        for (int i = 0; i < list.size(); i++) {
            hssfRow = sheet.createRow((int) i + 2);
            Duty duty = list.get(i);

            // 创建行单元格，并设置值
            HSSFCell cell = hssfRow.createCell(0); //列号，从0开始计数，0表示第1列
            cell.setCellValue(duty.getEmprid());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(1);//第二列
            cell.setCellValue(duty.getRealname());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(2);//第三列
            cell.setCellValue(duty.getDeptname());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(3);//第四列
            cell.setCellValue(duty.getDtdate());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(4);//第五列
            cell.setCellValue(duty.getSignintime());
            cell.setCellStyle(cellStyle);

            cell = hssfRow.createCell(5);//第六列
            cell.setCellValue(duty.getSignouttime());
            cell.setCellStyle(cellStyle);

        }

        // 保存Excel文件
            OutputStream outputStream = new FileOutputStream("e:/duty"+System.currentTimeMillis()+".xls");//保存地址
            workbook.write(outputStream);
            outputStream.close(); //关流
    }

    /**
     * 读取Excel
     *
     * @return 数据集合
     */
    private static List<Student> readExcel() {
        List<Student> list = new ArrayList<Student>();
        HSSFWorkbook workbook = null;

        try {
            // 读取Excel文件
            InputStream inputStream = new FileInputStream("e:/students.xls");
            workbook = new HSSFWorkbook(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 循环工作表
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null) {
                    continue;
                }

                // 将单元格中的内容存入集合
                Student student = new Student();

                HSSFCell cell = hssfRow.getCell(0);
                if (cell == null) {
                    continue;
                }
                student.setName(cell.getStringCellValue());

                cell = hssfRow.getCell(1);
                if (cell == null) {
                    continue;
                }
                student.setAge((int) cell.getNumericCellValue());

                cell = hssfRow.getCell(2);
                if (cell == null) {
                    continue;
                }
                student.setGrade(cell.getStringCellValue());

                list.add(student);
            }
        }
        return list;
    }
}

class Student {

    private String name;
    private int age;
    private String grade;

    public Student() {
    }

    public Student(String name, int age, String grade) {
        super();
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", grade=" + grade
                + "]";
    }
}
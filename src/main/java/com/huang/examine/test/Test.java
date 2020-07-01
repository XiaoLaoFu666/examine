package com.huang.examine.test;

import com.huang.examine.entity.Student;
import com.huang.examine.entity.User;
import com.huang.examine.utils.DateUtils;
import com.huang.examine.utils.MD5Util;
import org.thymeleaf.expression.Dates;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Mr.huang
 */
public class Test {

    /**
    public static void main(String[] args) {
        User user = null;
        Student student = new Student();
        student.setPassword("5444444444");;
        student.setSalt("4564546");
        user = student;
        System.out.println("" + user.getPassword() + " " + user.getSalt());
    }
     */

//    public static void main(String[] args) {
//        Integer id = 1610300204;
//        String salt = "1a2b3c";
//        String password ="d3b1294a61a07da9b49b6e22b2cbd7f9";
//        String calcPass = MD5Util.formPassToDBPass(password, salt);
//        System.out.println(calcPass);
//    }
    public static void main(String[] args) {
        DateUtils.LocalDateToDate("2020-01-03T18:00");
    }
}

package com.huang.examine.service;

import com.huang.examine.dao.StudentDao;
import com.huang.examine.dao.TeacherDao;
import com.huang.examine.entity.LoginVo;
import com.huang.examine.entity.Teacher;
import com.huang.examine.entity.User;
import com.huang.examine.result.CodeMsg;
import com.huang.examine.result.Result;
import com.huang.examine.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr.huang
 */
@Service
public class UserService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    public Result<String> login(HttpServletResponse response, LoginVo loginVo){
        if(loginVo==null){
            return Result.error(CodeMsg.NULLPOINT);
        }
        User user = null;
        if(loginVo.getIdentity().equals("student")){
            user = studentDao.getStudentByStudentID(loginVo.getUsername());
        }else if(loginVo.getIdentity().equals("teacher")){
            user = teacherDao.getTeacherByTeacherID(loginVo.getUsername());
        }
        if(user == null){
            return Result.error(CodeMsg.USERNAME_ERROR);
        }
        String formPass = loginVo.getPassword();
        String passDB = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if(calcPass.equals(passDB)){
            return Result.OK();
        }else {
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }
    }
}

package com.huang.examine.result;

/**
 * @author Mr.huang
 */
public class CodeMsg {

    private Integer code;
    private String msg;

    /**
     * 通用消息代码
     * */
    public static final CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static final CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static final CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static final CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");
    public static final CodeMsg ACCESS_LIMIT_REACHED= new CodeMsg(500104, "访问太频繁！");
    public static final CodeMsg NULLPOINT =new CodeMsg(500105,"数据对象为空");
    /**
     * 登录模块消息代码
     * */
    public static final CodeMsg SESSION_ERROR = new CodeMsg(500210,"用户登录过期或者Session已失效");
    public static final CodeMsg USERNAME_ERROR = new CodeMsg(500211,"用户名不存在");
    public static final CodeMsg PASSWORD_ERROR = new CodeMsg(500212,"登录密码错误");


    public CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }
}

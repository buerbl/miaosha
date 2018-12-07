package com.immoc.miaosha.result;

/**
 * Created by buer on 2018/12/5.
 * 结果封装，以后只需修改一出地方即可，优雅（编程技巧）
 */
public class Result<T> {
    private int code;
    private String msg;
    private T data;

//    成功时候调用
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    private Result(T data){
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    //    失败时候调用
    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }
    private Result(CodeMsg cm){
        if(cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public int getCode() {
        return code;
    }



    public String getMsg() {
        return msg;
    }



    public T getData() {
        return data;
    }


}

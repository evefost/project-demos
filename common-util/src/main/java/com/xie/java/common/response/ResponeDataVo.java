package com.xie.java.common.response;

/**
 * 接口统一返回格式
 *
 * @param <T>
 */
public class ResponeDataVo <T> {

    private T data;

    private Integer code;

    private String message;

    private ResponeDataVo(T data, Integer code) {
        this(data, code, null);
    }

    private ResponeDataVo(T data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
    public static <T> ResponeDataVo success() {
        return success(null);
    }

    public static <T> ResponeDataVo success(T data) {
        return success(data,ResponeEnum.SUCCESS.getMessage());
    }

    public static <T> ResponeDataVo success(T data, String message) {
        ResponeDataVo responeDataVo = new ResponeDataVo(data, ResponeEnum.SUCCESS.getCode(), message);
        return responeDataVo;
    }


    public static <T> ResponeDataVo error(String message) {
        return error(null,message);
    }


    public static <T> ResponeDataVo error(ResponeEnum code) {
        return error(code.getCode(),code.getMessage());
    }

    public static <T> ResponeDataVo error(T data,ResponeEnum code) {
        return error(data,code.getCode(),code.getMessage());
    }

    private static <T> ResponeDataVo error(Integer code,String message) {
        return error(null,code,message);
    }

    private static <T> ResponeDataVo error(T data, Integer code, String message) {
        ResponeDataVo responeDataVo = new ResponeDataVo(data, code, message);
        return responeDataVo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

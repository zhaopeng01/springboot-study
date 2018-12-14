package com.zyc.repeatCommit;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.UUID;

/**
 * @description:  返回结果
 *
 * @author zhaopeng
 */
public class Result {
    public int code = 0;
    public boolean success = true;
    public String rid = UUID.randomUUID().toString();
    public String msg = "";
    public Object data = new JSONObject();

    public Result() {
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public static Result insert = new Result("添加成功");
    public static Result update = new Result("更新成功");
    public static Result delete = new Result("删除成功");
    public static Result logout = new Result("退出成功");

    public static Result success(PageInfo data) {
        JSONObject obj = new JSONObject();
        obj.put("list", data.getList());
        JSONObject page = new JSONObject();
        page.put("total", data.getTotal());
        page.put("pageSize", data.getPageSize());
        page.put("pageNum", data.getPageNum());
        obj.put("page", page);
        Result result = new Result();
        result.data = obj;
        return result;
    }

    public static Result success(List<Object> data) {
        Result result = new Result();
        result.data = data;
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.data = data;
        return result;
    }

    public static Result success(Object data, String message) {
        Result result = new Result();
        result.data = data;
        result.msg = message;
        return result;
    }

    public static Result success(String msg) {
        Result result = new Result();
        result.msg = msg;
        return result;
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.code = code;
        result.success = false;
        result.msg = msg;
        return result;
    }

    public static Result error(Integer code, String msg, Object data) {
        Result result = new Result();
        result.code = code;
        result.success = false;
        result.msg = msg;
        result.data = data;
        return result;
    }
}

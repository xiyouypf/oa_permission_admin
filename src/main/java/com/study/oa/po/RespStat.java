package com.study.oa.po;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespStat {
    private int code;
    private String msg;
    private String data;

    public static RespStat build(int code) {
        return new RespStat(200, "ok", "删除成功");
    }

    public static RespStat build(int code, String msg) {
        return new RespStat(code, msg, "meiyou");
    }
}

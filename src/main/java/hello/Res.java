package hello;

import java.util.HashMap;

public class Res {

    private int ret;
    private String msg;
    private HashMap data;

    public Res(int ret, String msg, HashMap data) {
        this.ret = ret;
        this.msg = msg;
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public String getMsg() {
        return msg;
    }

    public HashMap getData() {
        return data;
    }
}
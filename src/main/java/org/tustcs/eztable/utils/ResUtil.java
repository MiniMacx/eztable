package org.tustcs.eztable.utils;



public class ResUtil {
    public static Res success(){
        return  new Res().setMsg("成功").setCode(0);
    }

    public static Res success(Object o){
        return new Res().setMsg("成功").setCode(0).setData(o);
    }

    public static Res error(int errorCode,String msg){
        return new Res().setMsg(msg).setCode(errorCode);
    }


}

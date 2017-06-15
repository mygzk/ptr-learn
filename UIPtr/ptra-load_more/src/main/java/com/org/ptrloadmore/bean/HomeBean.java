package com.org.ptrloadmore.bean;

/**
 * Created by guozhk on 2017/6/15.
 */

public class HomeBean {
    private Class cl;
    private String dre;

    public HomeBean() {
    }

    public HomeBean(Class cl, String dre) {
        this.cl = cl;
        this.dre = dre;
    }

    public Class getCl() {
        return cl;
    }

    public void setCl(Class cl) {
        this.cl = cl;
    }

    public String getDre() {
        return dre;
    }

    public void setDre(String dre) {
        this.dre = dre;
    }
}

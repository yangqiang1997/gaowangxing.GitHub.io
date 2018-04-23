package com.cqut.entity.entity.menu;

/**
 * 二级菜单，对应前台的格式
 * 作者：谭勇
 * 时间：2018-04-11
 * */
public class Label {

    /**
     * 对应菜单名
     * */
    private String label;

    /**
     * 对应前台路由
     * */
    private String to;

    /**
     * 对应前台index
     * */
    private String index;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}

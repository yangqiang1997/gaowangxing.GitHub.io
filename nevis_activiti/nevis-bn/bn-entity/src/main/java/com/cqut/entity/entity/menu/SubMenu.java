package com.cqut.entity.entity.menu;

import java.util.List;

/**
 * 一级菜单，对应前台的格式
 * 作者：谭勇
 * 时间：2018-04-11
 * */
public class SubMenu {

    /**
     * 菜单名
     * */
    private String subMenu;

    /**
     * icon
     * */
    private String icon;

    /**
     * 对应前台路由
     * */
    private String to;

    /**
     * 对应前台index
     * */
    private String index;

    /**
     * 子菜单
     * */
    private List<Label> menuGroup;

    public String getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(String subMenu) {
        this.subMenu = subMenu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<Label> getMenuGroup() {
        return menuGroup;
    }

    public void setMenuGroup(List<Label> menuGroup) {
        this.menuGroup = menuGroup;
    }
}

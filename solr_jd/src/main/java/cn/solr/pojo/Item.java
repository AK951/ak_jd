/**
 * Copyright (C), 2015-2018
 * FileName: Item
 * Author:   AK
 * Date:     2018/8/1 15:59
 * Description:
 * History:
 */

package cn.solr.pojo;

/**
 * 〈Description〉 
 * 〈〉
 *
 * @author AK
 * @create 2018/8/1
 * @since 1.0.0
 */
public class Item {
    private String pid;
    private String picture;
    private String name;
    private String price;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
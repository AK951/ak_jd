/**
 * Copyright (C), 2015-2018
 * FileName: ResultModle
 * Author:   AK
 * Date:     2018/8/1 16:04
 * Description:
 * History:
 */

package cn.solr.pojo;

import java.util.List;

/**
 * 〈Description〉 
 * 〈〉
 *
 * @author AK
 * @create 2018/8/1
 * @since 1.0.0
 */
public class ResultModle {
    // private
    private Integer curPage;
    private Integer totalPages;
    private Long totalCount;

    private List<Item> productList;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Item> getProductList() {
        return productList;
    }

    public void setProductList(List<Item> productList) {
        this.productList = productList;
    }
}
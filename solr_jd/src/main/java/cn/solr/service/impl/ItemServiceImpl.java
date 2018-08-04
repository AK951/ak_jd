/**
 * Copyright (C), 2015-2018
 * FileName: ItemServiceImpl
 * Author:   AK
 * Date:     2018/8/1 16:40
 * Description:
 * History:
 */

package cn.solr.service.impl;

import cn.solr.dao.ItemDao;
import cn.solr.pojo.ResultModle;
import cn.solr.service.ItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 〈Description〉 
 * 〈〉
 *
 * @author AK
 * @create 2018/8/1
 * @since 1.0.0
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Override
    public ResultModle queryItem(String queryString, String catalog_name, String price, Integer page, String sort) {
        SolrQuery query = new SolrQuery();
        //主查询
        if(queryString != null && !"".equals(queryString)) {
            query.setQuery(queryString);
        } else {
            query.setQuery("*:*");
        }
        //分类
        if(catalog_name != null && !"".equals(catalog_name)) {
            query.addFilterQuery("product_catalog_name:"+catalog_name);
        }
        //价格
        if(price != null && !"".equals(price)) {
            String[] split = price.split("-");
            if(split != null && split.length == 2) {
                query.addFilterQuery("product_price:["+split[0]+" TO "+split[1]+"]");
            }
        }
        //排序
        if(sort != null) {
            if(sort.equals("1")) {
                query.setSort("product_price",SolrQuery.ORDER.asc);
            } else {
                query.setSort("product_price",SolrQuery.ORDER.desc);
            }
        }

        //默认字段
        query.set("df","product_keywords");

        // 7.高亮查询
        // 1)开启高亮
        query.setHighlight(true);
        // 2)指定高亮字段
        query.addHighlightField("product_name");
        // 3)设置高亮前缀
        query.setHighlightSimplePre("<font color='red'>");
        // 4)设置高亮后缀
        query.setHighlightSimplePost("</font>");

        // 设置分页
        if (page == null) {
            page = 1;
        }
        query.setStart((page - 1) * 20);
        query.setRows(20);

        ResultModle resultModle = itemDao.queryItem(query);
        Long totalCount = resultModle.getTotalCount();
        Integer totalPages = (int) (totalCount + 20 - 1) / 20;
        resultModle.setTotalPages(totalPages);
        resultModle.setCurPage(page);

        return resultModle;
    }
}
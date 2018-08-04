/**
 * Copyright (C), 2015-2018
 * FileName: itemDaoImpl
 * Author:   AK
 * Date:     2018/8/1 16:21
 * Description:
 * History:
 */

package cn.solr.dao.impl;

import cn.solr.dao.ItemDao;
import cn.solr.pojo.Item;
import cn.solr.pojo.ResultModle;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 〈Description〉 
 * 〈〉
 *
 * @author AK
 * @create 2018/8/1
 * @since 1.0.0
 */
@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private SolrServer solrServer;

    @Override
    public ResultModle queryItem(SolrQuery solrQuery) {
        ResultModle resultModle = new ResultModle();
        try {

            // 使用solr服务查询索引库
            QueryResponse queryResponse = solrServer.query(solrQuery);
            // 获取查询文档集合
            SolrDocumentList results = queryResponse.getResults();

            resultModle.setTotalCount(results.getNumFound());
            List<Item> itemList = new ArrayList<>();

            for (SolrDocument doc : results) {
                Item item = new Item();

                String id = (String) doc.get("id");
                String price = String.valueOf(doc.get("product_price"));
                String picture = (String) doc.get("product_picture");
                String productName = (String) doc.get("product_name");

                // 获取高亮
                Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
                //第一个map的key就是Id
                Map<String, List<String>> map = highlighting.get(id);
                //第二个map的key是域名
                List<String> list = map.get("product_name");

                if(list!=null && list.size()>0){
                    productName = list.get(0);
                }

                item.setPid(id);
                item.setName(productName);
                item.setPicture(picture);
                item.setPrice(price);

                itemList.add(item);

            }
            resultModle.setProductList(itemList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultModle;
    }
}
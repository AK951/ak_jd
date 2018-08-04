/**
 * Copyright (C), 2015-2018
 * FileName: itemDao
 * Author:   AK
 * Date:     2018/8/1 16:19
 * Description:
 * History:
 */

package cn.solr.dao;

import cn.solr.pojo.ResultModle;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * 〈Description〉 
 * 〈〉
 *
 * @author AK
 * @create 2018/8/1
 * @since 1.0.0
 */
public interface ItemDao {

    ResultModle queryItem(SolrQuery solrQuery);

}
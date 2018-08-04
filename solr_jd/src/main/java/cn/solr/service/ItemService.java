/**
 * Copyright (C), 2015-2018
 * FileName: itemService
 * Author:   AK
 * Date:     2018/8/1 16:39
 * Description:
 * History:
 */

package cn.solr.service;

import cn.solr.pojo.ResultModle;

/**
 * 〈Description〉 
 * 〈〉
 *
 * @author AK
 * @create 2018/8/1
 * @since 1.0.0
 */
public interface ItemService {
    ResultModle queryItem(String queryString,String catalog_name,
                          String price,Integer page,String sort);
}
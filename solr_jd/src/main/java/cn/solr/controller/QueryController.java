/**
 * Copyright (C), 2015-2018
 * FileName: QueryController
 * Author:   AK
 * Date:     2018/8/1 16:39
 * Description:
 * History:
 */

package cn.solr.controller;

import cn.solr.pojo.ResultModle;
import cn.solr.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 〈Description〉 
 * 〈〉
 *
 * @author AK
 * @create 2018/8/1
 * @since 1.0.0
 */
@Controller
@RequestMapping("item")
public class QueryController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("list")
    public String queryItem(Model model, String queryString, String catalog_name,
                            String price, Integer page, String sort) {
        ResultModle resultModle = itemService.queryItem(queryString, catalog_name, price, page, sort);
        model.addAttribute("queryString",queryString);
        model.addAttribute("catalog_name",catalog_name);
        model.addAttribute("price",price);
        model.addAttribute("page",page);
        model.addAttribute("sort",sort);
        model.addAttribute("result",resultModle);
        return "product_list";
    }

}
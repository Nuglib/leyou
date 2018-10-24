package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 9:18 2018/10/21
 * @Modified By:
 */
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public ResponseEntity<List<Category>>
    queryCategoryListByParentId(@RequestParam(value = "pid") Long pid) {
        /**
         * @Author:jesse
         * @Description:分类管理
         * @param pid
         * @Date: 13:49 2018/10/22
         */
        List<Category> categoryList = null;
        try {
            if (pid == null || pid.longValue() < 0) {
                return ResponseEntity.badRequest().build();
            }
            categoryList = this.categoryService.queryCategoryListByParentId(pid);
            if (CollectionUtils.isEmpty(categoryList)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryCategoryByBrandid(@PathVariable("bid") Long bid) {
        /**
        * @Author:jesse
        * @Description:  根据品牌id查询所在的分类
        * @param bid
        * @Date: 20:33 2018/10/23
        */
        try {
            List<Category> list = this.categoryService.queryCategoryByBrandid(bid);
            if (CollectionUtils.isEmpty(list)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

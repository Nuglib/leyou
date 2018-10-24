package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 10:09 2018/10/21
 * @Modified By:
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    public List<Category> queryCategoryListByParentId(Long pid) {
        /**
        * @Author:jesse
        * @Description:查询所有分类
        * @param pid
        * @Date: 20:27 2018/10/23
        */
        Category category = new Category();
        category.setParent_id(pid);
        return this.categoryMapper.select(category);
    }

    public List<Category> queryCategoryByBrandid(Long bid) {
        /**
        * @Author:jesse
        * @Description:根据品牌id获取品牌分类
        * @param bid
        * @Date: 20:28 2018/10/23
        */
        return this.categoryMapper.queryCategoryByBrandid(bid);
    }
}

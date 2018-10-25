package com.leyou.item.mapper;

import com.leyou.item.pojo.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 10:12 2018/10/21
 * @Modified By:
 */
public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {

    /**
     * 自定义方法，根据品牌id查询所对应的商品分类
     * @param bid
     * @return
     */
    @Select("select id,name,parent_id,is_parent,sort from tb_category " +
            "where id in (select category_id from tb_category_brand where brand_id = #{bid})")
    List<Category> queryCategoryByBrandid(@Param("bid") Long bid);
}

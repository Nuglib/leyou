package com.leyou.item.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 14:02 2018/10/21
 * @Modified By:
 */
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 添加品牌与商品的对应关系在tb_category_brand表中
     * @param cid
     * @param bid
     * @return
     */
    @Insert("INSERT INTO tb_category_brand(category_id,brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    /**
     * 根据品牌id删除tb_category_brand中的对应关系
     * @param bid
     */
    @Delete("delete from tb_category_brand where brand_id = #{bid}")
    int deleteCategoryBrandById(@Param("bid") Long bid);

    @Update("update tb_brand set image=#{image},name=#{name},letter=#{letter} where id = #{bid}")
    int updateBrand(@Param("bid") Long id, @Param("image") String image,
                    @Param("name") String name, @Param("letter") Character letter);
}

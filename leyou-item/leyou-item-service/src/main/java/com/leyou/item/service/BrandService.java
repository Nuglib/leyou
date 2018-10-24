package com.leyou.item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 20:05 2018/10/21
 * @Modified By:
 */
@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPage(
            /**
             * @Author:jesse
             * @Description: 查询所有的品牌，对其进行分页
             * @param page
             * @param rows
             * @param sortBy
             * @param desc
             * @Date: 20:18 2018/10/21
             */
            String key, Integer page,
            Integer rows, String sortBy, Boolean desc) {
        PageHelper.startPage(page, rows);
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%")
                    .orEqualTo("letter", key + " ");
        }
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + " " + (desc ? "desc" : "asc");
            example.setOrderByClause(orderByClause);
        }


        Page<Brand> brandPage = (Page<Brand>) brandMapper.selectByExample(example);

        return new PageResult<>(brandPage.getTotal(), brandPage.getResult());

    }

    /**
     * 添加品牌
     * 和修改品牌
     * @param brand
     * @param cids
     */
    public void saveBrand(Brand brand, List<Long> cids) {

        int num = this.brandMapper.insert(brand);
        if (num != 1) {
            return;
        }
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }

    /**
     * 删除品牌
     * @param brand
     * @return
     */
    public int deleteBrand(Brand brand) {

        int num = brandMapper.deleteCategoryBrandById(brand.getId());
        if (num < 1) {
            return 0;
        }
        brandMapper.deleteByPrimaryKey(brand.getId());
        return num;
    }

    public int updateBrand(Brand brand,List<Long> cids) {
        this.brandMapper.deleteCategoryBrandById(brand.getId());
        for (Long cid : cids) {
            try {
                this.brandMapper.insertCategoryBrand(cid,brand.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return brandMapper.updateBrand(brand.getId(),brand.getImage(),brand.getName(),brand.getLetter());
    }
}

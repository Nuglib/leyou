package com.leyou.item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.SpuMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Spu;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 21:54 2018/10/24
 * @Modified By:
 */
@Service
public class GoodsService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 根据查询条件和分页条件查询该分类下的商品
     * 返回的是一个分页对象
     *
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    public PageResult<SpuBo> qerySpuByPage(String key, Boolean saleable, Integer page, Integer rows) {
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        PageHelper.startPage(page, rows);
        if (StringUtils.isNotBlank(key)) {

            criteria.andLike("title", "%" + key + "%");
        }

        criteria.andEqualTo("saleable", saleable);

        Page<Spu> objectPageInfo = (Page<Spu>) spuMapper.selectByExample(example);

        List<SpuBo> list = objectPageInfo.getResult().stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);
            List<String> cnames = categoryService.queryCategoryNameByIds(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(cnames, "-"));
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuBo.setBname(brand.getName());
            return spuBo;
        }).collect(Collectors.toList());
        return new PageResult(objectPageInfo.getTotal(), list);
    }
}

package com.leyou.item.service;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 20:13 2018/10/24
 * @Modified By:
 */
@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 根据分类ID查询，商品分组模板信息
     *
     * @param cid
     * @return
     */
    public List<SpecGroup> querySpecGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return specGroupMapper.select(specGroup);
    }

    /**
     * 根据组id查询所对应的权限信息
     *
     * @param gid
     * @return
     */
    public List<SpecParam> querySpecParamByGid(Long gid) {
        SpecParam specParam = new SpecParam();
        specParam.setGroup_id(gid);
        return specParamMapper.select(specParam);
    }

    /**
     * 修改组信息（名称）
     *
     * @param specGroup
     * @return
     */
    public int updataSpecGroupNameByGroup(SpecGroup specGroup) {
        return specGroupMapper.updateByPrimaryKey(specGroup);
    }

    /**
     * 添加分组
     *
     * @param specGroup
     * @return
     */
    public int addSpecGroupInCategory(SpecGroup specGroup) {
        int num = specGroupMapper.insert(specGroup);
        if (num < 1) {
            return 0;
        }
        return num;
    }

    /**
     * 删除一个分组
     *
     * @param gid
     * @return
     */
    public int deleteSpecGroupInCategory(Long gid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setId(gid);
        int num = specGroupMapper.deleteByPrimaryKey(specGroup);
        if (num < 1) {
            return 0;
        }
        return num;
    }

    /**
     * 添加分组下详情
     *
     * @param specParam
     * @return
     */
    public int addSpecParam(SpecParam specParam) {
        int insert = specParamMapper.insert(specParam);
        if (insert < 1) {
            return 0;
        }
        return insert;
    }

    /**
     * 修改分组参数信息
     *
     * @param specParam
     * @return
     */
    public int updateSpecParam(SpecParam specParam) {
        int num = specParamMapper.updateByPrimaryKey(specParam);
        if (num < 1) {
            return 0;
        }
        return num;
    }

    /**
     * 根据id删除参数详情
     *
     * @param pid
     * @return
     */
    public int deleteSpecParamById(Long pid) {
        int num = specParamMapper.deleteByPrimaryKey(pid);
        if (num < 1) {
            return 0;
        }
        return num;
    }
}

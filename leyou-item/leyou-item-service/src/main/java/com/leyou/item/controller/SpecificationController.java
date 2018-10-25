package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 19:54 2018/10/24
 * @Modified By:
 */
@Controller
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据分类ID查询，商品分组模板信息
     *
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroupByCid(@PathVariable("cid") Long cid) {
        List<SpecGroup> groupList = specificationService.querySpecGroupByCid(cid);
        if (CollectionUtils.isEmpty(groupList)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groupList);
    }

    /**
     * 根据组id查询当前组的权限
     *
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> querySpecParamByGid(@RequestParam("gid") Long gid) {
        List<SpecParam> specParams = specificationService.querySpecParamByGid(gid);
        if (CollectionUtils.isEmpty(specParams)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(specParams);
    }

    /**
     * 修改组名称
     *
     * @param specGroup
     * @return
     */
    @PutMapping("group")
    public ResponseEntity<Void> updateSpecGroupNameByGroup(SpecGroup specGroup) {
        int num = specificationService.updataSpecGroupNameByGroup(specGroup);
        if (num < 1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 规格参数中，添加分组
     *
     * @param specGroup
     * @return
     */
    @PostMapping("group")
    public ResponseEntity<Void> addSpecGroupInCategory(SpecGroup specGroup) {
        if (specGroup == null) {
            return ResponseEntity.badRequest().build();
        }
        int num = specificationService.addSpecGroupInCategory(specGroup);
        if (num == 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据分组id删除分组
     *
     * @param gid
     * @return
     */
    @DeleteMapping("group/{gid}")
    public ResponseEntity<Void> deleteSpecGroupInCategory(@PathVariable("gid") Long gid) {
        int num = specificationService.deleteSpecGroupInCategory(gid);
        if (num == 0) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 添加分组下参数
     *
     * @param specParam
     * @return
     */
    @PostMapping("param")
    public ResponseEntity<Void> addSpecParam(@RequestBody SpecParam specParam) {
        int num = specificationService.addSpecParam(specParam);
        if (num < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改分组参数信息
     *
     * @param specParam
     * @return
     */
    @PutMapping("param")
    public ResponseEntity<Void> updateSpecParam(@RequestBody SpecParam specParam) {
        int num = specificationService.updateSpecParam(specParam);
        if (num < 1) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 根据传来的参数id删除该参数
     *
     * @param pid
     * @return
     */
    @DeleteMapping("param/{pid}")
    public ResponseEntity<Void> deleteSpecParam(Long pid) {
        int num = specificationService.deleteSpecParamById(pid);
        if (num < 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

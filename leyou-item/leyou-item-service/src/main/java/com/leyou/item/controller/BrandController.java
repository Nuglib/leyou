package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 19:36 2018/10/21
 * @Modified By:
 */
@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc
    ) {

        /**
         * @Author:jesse
         * @Description:获取所有品牌
         * @param:贾存
         * @param key
         * @param page
         * @param rows
         * @param sortBy
         * @param desc
         * @Date: 13:51 2018/10/22
         */

        PageResult<Brand> pageResult = null;
        try {
            pageResult = brandService.queryBrandByPage(key, page, rows, sortBy, desc);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (pageResult == null || pageResult.getItems().size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pageResult);
    }

    /**
     * 添加商品
     *
     * @param brand
     * @param cids
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addBrandBy(Brand brand, @RequestParam("cids") List<Long> cids) {
        try {
            this.brandService.saveBrand(brand, cids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateBrandBy(Brand brand, @RequestParam("cids") List<Long> cids) {
        try {
            int doSuccess = this.brandService.updateBrand(brand, cids);
            if (doSuccess != 1) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根据传来的品牌删除品牌
     *
     * @param brand
     * @return
     */
    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteBrand(Brand brand) {
        try {
            int num = brandService.deleteBrand(brand);
            if (num != 1) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

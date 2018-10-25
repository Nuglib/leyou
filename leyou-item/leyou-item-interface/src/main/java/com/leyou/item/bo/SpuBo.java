package com.leyou.item.bo;

import com.leyou.item.pojo.Spu;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 21:38 2018/10/24
 * @Modified By:
 */
public class SpuBo extends Spu {
    private String cname;
    private String bname;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}

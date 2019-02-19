package com.youngor.wsc.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Auther zhoutf
 * @Date 2019/2/13 16:17
 * @Description 在售商品
 */
@Data
@Entity
@Table(name = "WSC_ITEM")
public class Item {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "ITEM_ID")
    private BigDecimal itemId;

    @Column(name = "ITEM_NO")
    private String itemNo;

    @Column(name = "ITEM_CODE")
    private String itemCode;

}

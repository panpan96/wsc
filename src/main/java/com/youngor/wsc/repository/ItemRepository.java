package com.youngor.wsc.repository;

import com.youngor.wsc.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther zhoutf
 * @Date 2019/2/13 16:22
 * @Description
 */
public interface ItemRepository extends JpaRepository<Item,String> {

}

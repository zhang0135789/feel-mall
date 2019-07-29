package com.feel.mall.db.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodsProductMapper {
    int addStock(@Param("id") Integer id, @Param("num") Short num);
    int reduceStock(@Param("id") Integer id, @Param("num") Short num);
}

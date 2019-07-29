package com.feel.mall.db.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface StatMapper {
    List<Map> statUser();

    List<Map> statOrder();

    List<Map> statGoods();
}

package com.feel.mall.db.dao;

import com.feel.mall.db.domain.LitemallOrder;
import org.apache.ibatis.annotations.Param;
import com.feel.mall.db.domain.LitemallOrder;

import java.time.LocalDateTime;

public interface OrderMapper {
    int updateWithOptimisticLocker(@Param("lastUpdateTime") LocalDateTime lastUpdateTime, @Param("order") LitemallOrder order);
}

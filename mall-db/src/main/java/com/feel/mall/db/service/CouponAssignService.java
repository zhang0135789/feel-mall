package com.feel.mall.db.service;

import com.feel.mall.db.domain.MallCoupon;
import com.feel.mall.db.domain.MallCouponUser;
import com.feel.mall.db.util.CouponConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponAssignService {

    @Autowired
    private MallCouponUserService mallCouponUserService;
    @Autowired
    private MallCouponService mallCouponService;

    /**
     * 分发注册优惠券
     *
     * @param userId
     * @return
     */
    public void assignForRegister(Integer userId) {
        List<MallCoupon> couponList = mallCouponService.queryRegister();
        for(MallCoupon coupon : couponList){
            Integer couponId = coupon.getId();

            Integer count = mallCouponUserService.countUserAndCoupon(userId, couponId);
            if (count > 0) {
                continue;
            }

            Short limit = coupon.getLimit();
            while(limit > 0){
                MallCouponUser couponUser = new MallCouponUser();
                couponUser.setCouponId(couponId);
                couponUser.setUserId(userId);
                Short timeType = coupon.getTimeType();
                if (timeType.equals(CouponConstant.TIME_TYPE_TIME)) {
                    couponUser.setStartTime(coupon.getStartTime());
                    couponUser.setEndTime(coupon.getEndTime());
                }
                else{
                    LocalDateTime now = LocalDateTime.now();
                    couponUser.setStartTime(now);
                    couponUser.setEndTime(now.plusDays(coupon.getDays()));
                }
                mallCouponUserService.add(couponUser);

                limit--;
            }
        }

    }

}

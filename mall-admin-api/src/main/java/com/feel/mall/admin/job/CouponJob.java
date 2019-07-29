package com.feel.mall.admin.job;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.feel.mall.db.domain.MallCoupon;
import com.feel.mall.db.domain.MallCouponUser;
import com.feel.mall.db.service.MallCouponService;
import com.feel.mall.db.service.MallCouponUserService;
import com.feel.mall.db.util.CouponConstant;
import com.feel.mall.db.util.CouponUserConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 检测优惠券过期情况
 */
@Component
public class CouponJob {
    private final Log logger = LogFactory.getLog(CouponJob.class);

    @Autowired
    private MallCouponService mallCouponService;
    @Autowired
    private MallCouponUserService mallCouponUserService;

    /**
     * 每隔一个小时检查
     * TODO
     * 注意，因为是相隔一个小时检查，因此导致优惠券真正超时时间可能比设定时间延迟1个小时
     */
    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void checkCouponExpired() {
        logger.info("系统开启任务检查优惠券是否已经过期");

        List<MallCoupon> couponList = mallCouponService.queryExpired();
        for (MallCoupon coupon : couponList) {
            coupon.setStatus(CouponConstant.STATUS_EXPIRED);
            mallCouponService.updateById(coupon);
        }

        List<MallCouponUser> couponUserList = mallCouponUserService.queryExpired();
        for (MallCouponUser couponUser : couponUserList) {
            couponUser.setStatus(CouponUserConstant.STATUS_EXPIRED);
            mallCouponUserService.update(couponUser);
        }
    }

}

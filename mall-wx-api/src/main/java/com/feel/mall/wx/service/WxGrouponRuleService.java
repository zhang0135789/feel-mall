package com.feel.mall.wx.service;

import com.github.pagehelper.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.feel.mall.db.domain.MallGoods;
import com.feel.mall.db.domain.MallGrouponRules;
import com.feel.mall.db.service.MallGoodsService;
import com.feel.mall.db.service.MallGrouponRulesService;
import com.feel.mall.db.service.MallGrouponService;
import com.feel.mall.wx.vo.GrouponRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxGrouponRuleService {
    private final Log logger = LogFactory.getLog(WxGrouponRuleService.class);

    @Autowired
    private MallGrouponRulesService grouponRulesService;
    @Autowired
    private MallGrouponService grouponService;
    @Autowired
    private MallGoodsService goodsService;


    public List<GrouponRuleVo> queryList(Integer page, Integer size) {
        return queryList(page, size, "add_time", "desc");
    }


    public List<GrouponRuleVo> queryList(Integer page, Integer size, String sort, String order) {
        Page<MallGrouponRules> grouponRulesList = (Page)grouponRulesService.queryList(page, size, sort, order);

        Page<GrouponRuleVo> grouponList = new Page<GrouponRuleVo>();
        grouponList.setPages(grouponRulesList.getPages());
        grouponList.setPageNum(grouponRulesList.getPageNum());
        grouponList.setPageSize(grouponRulesList.getPageSize());
        grouponList.setTotal(grouponRulesList.getTotal());

        for (MallGrouponRules rule : grouponRulesList) {
            Integer goodsId = rule.getGoodsId();
            MallGoods goods = goodsService.findById(goodsId);
            if (goods == null)
                continue;

            GrouponRuleVo grouponRuleVo = new GrouponRuleVo();
            grouponRuleVo.setId(goods.getId());
            grouponRuleVo.setName(goods.getName());
            grouponRuleVo.setBrief(goods.getBrief());
            grouponRuleVo.setPicUrl(goods.getPicUrl());
            grouponRuleVo.setCounterPrice(goods.getCounterPrice());
            grouponRuleVo.setRetailPrice(goods.getRetailPrice());
            grouponRuleVo.setGrouponPrice(goods.getRetailPrice().subtract(rule.getDiscount()));
            grouponRuleVo.setGrouponDiscount(rule.getDiscount());
            grouponRuleVo.setGrouponMember(rule.getDiscountMember());
            grouponList.add(grouponRuleVo);
        }

        return grouponList;
    }
}

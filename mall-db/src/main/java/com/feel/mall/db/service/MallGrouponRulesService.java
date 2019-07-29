package com.feel.mall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallGoodsMapper;
import com.feel.mall.db.dao.MallGrouponRulesMapper;
import com.feel.mall.db.domain.MallGoods;
import com.feel.mall.db.domain.MallGrouponRules;
import com.feel.mall.db.domain.MallGrouponRulesExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallGrouponRulesService {
    @Resource
    private MallGrouponRulesMapper mallGrouponRulesMapper;
    @Resource
    private MallGoodsMapper mallGoodsMapper;
    private MallGoods.Column[] goodsColumns = new MallGoods.Column[]{MallGoods.Column.id, MallGoods.Column.name, MallGoods.Column.brief, MallGoods.Column.picUrl, MallGoods.Column.counterPrice, MallGoods.Column.retailPrice};

    public int createRules(MallGrouponRules rules) {
        rules.setAddTime(LocalDateTime.now());
        rules.setUpdateTime(LocalDateTime.now());
        return mallGrouponRulesMapper.insertSelective(rules);
    }

    /**
     * 根据ID查找对应团购项
     *
     * @param id
     * @return
     */
    public MallGrouponRules queryById(Integer id) {
        MallGrouponRulesExample example = new MallGrouponRulesExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return mallGrouponRulesMapper.selectOneByExample(example);
    }

    /**
     * 查询某个商品关联的团购规则
     *
     * @param goodsId
     * @return
     */
    public List<MallGrouponRules> queryByGoodsId(Integer goodsId) {
        MallGrouponRulesExample example = new MallGrouponRulesExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return mallGrouponRulesMapper.selectByExample(example);
    }

    /**
     * 获取首页团购活动列表
     *
     * @param page
     * @param limit
     * @return
     */
    public List<MallGrouponRules> queryList(Integer page, Integer limit) {
        return queryList(page, limit, "add_time", "desc");
    }

    public List<MallGrouponRules> queryList(Integer page, Integer limit, String sort, String order) {
        MallGrouponRulesExample example = new MallGrouponRulesExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(page, limit);
        return mallGrouponRulesMapper.selectByExample(example);
    }

    /**
     * 判断某个团购活动是否已经过期
     *
     * @return
     */
    public boolean isExpired(MallGrouponRules rules) {
        return (rules == null || rules.getExpireTime().isBefore(LocalDateTime.now()));
    }

    /**
     * 获取团购活动列表
     *
     * @param goodsId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<MallGrouponRules> querySelective(String goodsId, Integer page, Integer size, String sort, String order) {
        MallGrouponRulesExample example = new MallGrouponRulesExample();
        example.setOrderByClause(sort + " " + order);

        MallGrouponRulesExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(Integer.parseInt(goodsId));
        }
        criteria.andDeletedEqualTo(false);

        PageHelper.startPage(page, size);
        return mallGrouponRulesMapper.selectByExample(example);
    }

    public void delete(Integer id) {
        mallGrouponRulesMapper.logicalDeleteByPrimaryKey(id);
    }

    public int updateById(MallGrouponRules grouponRules) {
        grouponRules.setUpdateTime(LocalDateTime.now());
        return mallGrouponRulesMapper.updateByPrimaryKeySelective(grouponRules);
    }
}

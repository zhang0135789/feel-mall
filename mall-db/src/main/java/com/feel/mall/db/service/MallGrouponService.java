package com.feel.mall.db.service;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallGrouponMapper;
import com.feel.mall.db.domain.MallGroupon;
import com.feel.mall.db.domain.MallGrouponExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallGrouponService {
    @Resource
    private MallGrouponMapper mallGrouponMapper;

    /**
     * 获取用户发起的团购记录
     *
     * @param userId
     * @return
     */
    public List<MallGroupon> queryMyGroupon(Integer userId) {
        MallGrouponExample example = new MallGrouponExample();
        example.or().andUserIdEqualTo(userId).andCreatorUserIdEqualTo(userId).andGrouponIdEqualTo(0).andDeletedEqualTo(false).andPayedEqualTo(true);
        example.orderBy("add_time desc");
        return mallGrouponMapper.selectByExample(example);
    }

    /**
     * 获取用户参与的团购记录
     *
     * @param userId
     * @return
     */
    public List<MallGroupon> queryMyJoinGroupon(Integer userId) {
        MallGrouponExample example = new MallGrouponExample();
        example.or().andUserIdEqualTo(userId).andGrouponIdNotEqualTo(0).andDeletedEqualTo(false).andPayedEqualTo(true);
        example.orderBy("add_time desc");
        return mallGrouponMapper.selectByExample(example);
    }

    /**
     * 根据OrderId查询团购记录
     *
     * @param orderId
     * @return
     */
    public MallGroupon queryByOrderId(Integer orderId) {
        MallGrouponExample example = new MallGrouponExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return mallGrouponMapper.selectOneByExample(example);
    }

    /**
     * 获取某个团购活动参与的记录
     *
     * @param id
     * @return
     */
    public List<MallGroupon> queryJoinRecord(Integer id) {
        MallGrouponExample example = new MallGrouponExample();
        example.or().andGrouponIdEqualTo(id).andDeletedEqualTo(false).andPayedEqualTo(true);
        example.orderBy("add_time desc");
        return mallGrouponMapper.selectByExample(example);
    }

    /**
     * 根据ID查询记录
     *
     * @param id
     * @return
     */
    public MallGroupon queryById(Integer id) {
        MallGrouponExample example = new MallGrouponExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false).andPayedEqualTo(true);
        return mallGrouponMapper.selectOneByExample(example);
    }

    /**
     * 返回某个发起的团购参与人数
     *
     * @param grouponId
     * @return
     */
    public int countGroupon(Integer grouponId) {
        MallGrouponExample example = new MallGrouponExample();
        example.or().andGrouponIdEqualTo(grouponId).andDeletedEqualTo(false).andPayedEqualTo(true);
        return (int) mallGrouponMapper.countByExample(example);
    }

    public int updateById(MallGroupon groupon) {
        groupon.setUpdateTime(LocalDateTime.now());
        return mallGrouponMapper.updateByPrimaryKeySelective(groupon);
    }

    /**
     * 创建或参与一个团购
     *
     * @param groupon
     * @return
     */
    public int createGroupon(MallGroupon groupon) {
        groupon.setAddTime(LocalDateTime.now());
        groupon.setUpdateTime(LocalDateTime.now());
        return mallGrouponMapper.insertSelective(groupon);
    }


    /**
     * 查询所有发起的团购记录
     *
     * @param rulesId
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<MallGroupon> querySelective(String rulesId, Integer page, Integer size, String sort, String order) {
        MallGrouponExample example = new MallGrouponExample();
        MallGrouponExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(rulesId)) {
            criteria.andRulesIdEqualTo(Integer.parseInt(rulesId));
        }
        criteria.andDeletedEqualTo(false);
        criteria.andPayedEqualTo(true);
        criteria.andGrouponIdEqualTo(0);

        PageHelper.startPage(page, size);
        return mallGrouponMapper.selectByExample(example);
    }
}

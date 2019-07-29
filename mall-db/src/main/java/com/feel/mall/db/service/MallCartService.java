package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallCartMapper;
import com.feel.mall.db.domain.MallCart;
import com.feel.mall.db.domain.MallCartExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallCartService {
    @Resource
    private MallCartMapper mallCartMapper;

    public MallCart queryExist(Integer goodsId, Integer productId, Integer userId) {
        MallCartExample example = new MallCartExample();
        example.or().andGoodsIdEqualTo(goodsId).andProductIdEqualTo(productId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return mallCartMapper.selectOneByExample(example);
    }

    public void add(MallCart cart) {
        cart.setAddTime(LocalDateTime.now());
        cart.setUpdateTime(LocalDateTime.now());
        mallCartMapper.insertSelective(cart);
    }

    public int updateById(MallCart cart) {
        cart.setUpdateTime(LocalDateTime.now());
        return mallCartMapper.updateByPrimaryKeySelective(cart);
    }

    public List<MallCart> queryByUid(int userId) {
        MallCartExample example = new MallCartExample();
        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return mallCartMapper.selectByExample(example);
    }


    public List<MallCart> queryByUidAndChecked(Integer userId) {
        MallCartExample example = new MallCartExample();
        example.or().andUserIdEqualTo(userId).andCheckedEqualTo(true).andDeletedEqualTo(false);
        return mallCartMapper.selectByExample(example);
    }

    public int delete(List<Integer> productIdList, int userId) {
        MallCartExample example = new MallCartExample();
        example.or().andUserIdEqualTo(userId).andProductIdIn(productIdList);
        return mallCartMapper.logicalDeleteByExample(example);
    }

    public MallCart findById(Integer id) {
        return mallCartMapper.selectByPrimaryKey(id);
    }

    public int updateCheck(Integer userId, List<Integer> idsList, Boolean checked) {
        MallCartExample example = new MallCartExample();
        example.or().andUserIdEqualTo(userId).andProductIdIn(idsList).andDeletedEqualTo(false);
        MallCart cart = new MallCart();
        cart.setChecked(checked);
        cart.setUpdateTime(LocalDateTime.now());
        return mallCartMapper.updateByExampleSelective(cart, example);
    }

    public void clearGoods(Integer userId) {
        MallCartExample example = new MallCartExample();
        example.or().andUserIdEqualTo(userId).andCheckedEqualTo(true);
        MallCart cart = new MallCart();
        cart.setDeleted(true);
        mallCartMapper.updateByExampleSelective(cart, example);
    }

    public List<MallCart> querySelective(Integer userId, Integer goodsId, Integer page, Integer limit, String sort, String order) {
        MallCartExample example = new MallCartExample();
        MallCartExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(goodsId)) {
            criteria.andGoodsIdEqualTo(goodsId);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallCartMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        mallCartMapper.logicalDeleteByPrimaryKey(id);
    }

    public boolean checkExist(Integer goodsId) {
        MallCartExample example = new MallCartExample();
        example.or().andGoodsIdEqualTo(goodsId).andCheckedEqualTo(true).andDeletedEqualTo(false);
        return mallCartMapper.countByExample(example) != 0;
    }
}

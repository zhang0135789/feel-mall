package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallOrderGoodsMapper;
import com.feel.mall.db.domain.MallOrderGoods;
import com.feel.mall.db.domain.MallOrderGoodsExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallOrderGoodsService {
    @Resource
    private MallOrderGoodsMapper mallOrderGoodsMapper;

    public int add(MallOrderGoods orderGoods) {
        orderGoods.setAddTime(LocalDateTime.now());
        orderGoods.setUpdateTime(LocalDateTime.now());
        return mallOrderGoodsMapper.insertSelective(orderGoods);
    }

    public List<MallOrderGoods> queryByOid(Integer orderId) {
        MallOrderGoodsExample example = new MallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        return mallOrderGoodsMapper.selectByExample(example);
    }

    public List<MallOrderGoods> findByOidAndGid(Integer orderId, Integer goodsId) {
        MallOrderGoodsExample example = new MallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return mallOrderGoodsMapper.selectByExample(example);
    }

    public MallOrderGoods findById(Integer id) {
        return mallOrderGoodsMapper.selectByPrimaryKey(id);
    }

    public void updateById(MallOrderGoods orderGoods) {
        orderGoods.setUpdateTime(LocalDateTime.now());
        mallOrderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
    }

    public Short getComments(Integer orderId) {
        MallOrderGoodsExample example = new MallOrderGoodsExample();
        example.or().andOrderIdEqualTo(orderId).andDeletedEqualTo(false);
        long count = mallOrderGoodsMapper.countByExample(example);
        return (short) count;
    }

    public boolean checkExist(Integer goodsId) {
        MallOrderGoodsExample example = new MallOrderGoodsExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return mallOrderGoodsMapper.countByExample(example) != 0;
    }
}

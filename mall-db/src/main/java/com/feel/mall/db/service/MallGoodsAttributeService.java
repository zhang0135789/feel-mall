package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallGoodsAttributeMapper;
import com.feel.mall.db.domain.MallGoodsAttribute;
import com.feel.mall.db.domain.MallGoodsAttributeExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallGoodsAttributeService {
    @Resource
    private MallGoodsAttributeMapper mallGoodsAttributeMapper;

    public List<MallGoodsAttribute> queryByGid(Integer goodsId) {
        MallGoodsAttributeExample example = new MallGoodsAttributeExample();
        example.or().andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        return mallGoodsAttributeMapper.selectByExample(example);
    }

    public void add(MallGoodsAttribute goodsAttribute) {
        goodsAttribute.setAddTime(LocalDateTime.now());
        goodsAttribute.setUpdateTime(LocalDateTime.now());
        mallGoodsAttributeMapper.insertSelective(goodsAttribute);
    }

    public MallGoodsAttribute findById(Integer id) {
        return mallGoodsAttributeMapper.selectByPrimaryKey(id);
    }

    public void deleteByGid(Integer gid) {
        MallGoodsAttributeExample example = new MallGoodsAttributeExample();
        example.or().andGoodsIdEqualTo(gid);
        mallGoodsAttributeMapper.logicalDeleteByExample(example);
    }
}

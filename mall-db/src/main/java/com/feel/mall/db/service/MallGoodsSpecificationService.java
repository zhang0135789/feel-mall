package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallGoodsSpecificationMapper;
import com.feel.mall.db.domain.MallGoodsSpecification;
import com.feel.mall.db.domain.MallGoodsSpecificationExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MallGoodsSpecificationService {
    @Resource
    private MallGoodsSpecificationMapper mallGoodsSpecificationMapper;

    public List<MallGoodsSpecification> queryByGid(Integer id) {
        MallGoodsSpecificationExample example = new MallGoodsSpecificationExample();
        example.or().andGoodsIdEqualTo(id).andDeletedEqualTo(false);
        return mallGoodsSpecificationMapper.selectByExample(example);
    }

    public MallGoodsSpecification findById(Integer id) {
        return mallGoodsSpecificationMapper.selectByPrimaryKey(id);
    }

    public void deleteByGid(Integer gid) {
        MallGoodsSpecificationExample example = new MallGoodsSpecificationExample();
        example.or().andGoodsIdEqualTo(gid);
        mallGoodsSpecificationMapper.logicalDeleteByExample(example);
    }

    public void add(MallGoodsSpecification goodsSpecification) {
        goodsSpecification.setAddTime(LocalDateTime.now());
        goodsSpecification.setUpdateTime(LocalDateTime.now());
        mallGoodsSpecificationMapper.insertSelective(goodsSpecification);
    }

    /**
     * [
     * {
     * name: '',
     * valueList: [ {}, {}]
     * },
     * {
     * name: '',
     * valueList: [ {}, {}]
     * }
     * ]
     *
     * @param id
     * @return
     */
    public Object getSpecificationVoList(Integer id) {
        List<MallGoodsSpecification> goodsSpecificationList = queryByGid(id);

        Map<String, VO> map = new HashMap<>();
        List<VO> specificationVoList = new ArrayList<>();

        for (MallGoodsSpecification goodsSpecification : goodsSpecificationList) {
            String specification = goodsSpecification.getSpecification();
            VO goodsSpecificationVo = map.get(specification);
            if (goodsSpecificationVo == null) {
                goodsSpecificationVo = new VO();
                goodsSpecificationVo.setName(specification);
                List<MallGoodsSpecification> valueList = new ArrayList<>();
                valueList.add(goodsSpecification);
                goodsSpecificationVo.setValueList(valueList);
                map.put(specification, goodsSpecificationVo);
                specificationVoList.add(goodsSpecificationVo);
            } else {
                List<MallGoodsSpecification> valueList = goodsSpecificationVo.getValueList();
                valueList.add(goodsSpecification);
            }
        }

        return specificationVoList;
    }

    private class VO {
        private String name;
        private List<MallGoodsSpecification> valueList;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<MallGoodsSpecification> getValueList() {
            return valueList;
        }

        public void setValueList(List<MallGoodsSpecification> valueList) {
            this.valueList = valueList;
        }
    }

}

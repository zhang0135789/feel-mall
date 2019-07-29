package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallBrandMapper;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.domain.MallBrand;
import com.feel.mall.db.domain.MallBrand.Column;
import com.feel.mall.db.domain.MallBrandExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallBrandService {
    @Resource
    private MallBrandMapper mallBrandMapper;
    private Column[] columns = new Column[]{Column.id, Column.name, Column.desc, Column.picUrl, Column.floorPrice};

    public List<MallBrand> query(Integer page, Integer limit, String sort, String order) {
        MallBrandExample example = new MallBrandExample();
        example.or().andDeletedEqualTo(false);
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, limit);
        return mallBrandMapper.selectByExampleSelective(example, columns);
    }

    public List<MallBrand> query(Integer page, Integer limit) {
        return query(page, limit, null, null);
    }

    public MallBrand findById(Integer id) {
        return mallBrandMapper.selectByPrimaryKey(id);
    }

    public List<MallBrand> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        MallBrandExample example = new MallBrandExample();
        MallBrandExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(id)) {
            criteria.andIdEqualTo(Integer.valueOf(id));
        }
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return mallBrandMapper.selectByExample(example);
    }

    public int updateById(MallBrand brand) {
        brand.setUpdateTime(LocalDateTime.now());
        return mallBrandMapper.updateByPrimaryKeySelective(brand);
    }

    public void deleteById(Integer id) {
        mallBrandMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(MallBrand brand) {
        brand.setAddTime(LocalDateTime.now());
        brand.setUpdateTime(LocalDateTime.now());
        mallBrandMapper.insertSelective(brand);
    }

    public List<MallBrand> all() {
        MallBrandExample example = new MallBrandExample();
        example.or().andDeletedEqualTo(false);
        return mallBrandMapper.selectByExample(example);
    }
}

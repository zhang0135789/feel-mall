package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallCategoryMapper;
import com.feel.mall.db.domain.MallCategory;
import com.feel.mall.db.domain.MallCategoryExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallCategoryService {
    @Resource
    private MallCategoryMapper mallCategoryMapper;
    private MallCategory.Column[] CHANNEL = {MallCategory.Column.id, MallCategory.Column.name, MallCategory.Column.iconUrl};

    public List<MallCategory> queryL1WithoutRecommend(int offset, int limit) {
        MallCategoryExample example = new MallCategoryExample();
        example.or().andLevelEqualTo("L1").andNameNotEqualTo("推荐").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return mallCategoryMapper.selectByExample(example);
    }

    public List<MallCategory> queryL1(int offset, int limit) {
        MallCategoryExample example = new MallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return mallCategoryMapper.selectByExample(example);
    }

    public List<MallCategory> queryL1() {
        MallCategoryExample example = new MallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return mallCategoryMapper.selectByExample(example);
    }

    public List<MallCategory> queryByPid(Integer pid) {
        MallCategoryExample example = new MallCategoryExample();
        example.or().andPidEqualTo(pid).andDeletedEqualTo(false);
        return mallCategoryMapper.selectByExample(example);
    }

    public List<MallCategory> queryL2ByIds(List<Integer> ids) {
        MallCategoryExample example = new MallCategoryExample();
        example.or().andIdIn(ids).andLevelEqualTo("L2").andDeletedEqualTo(false);
        return mallCategoryMapper.selectByExample(example);
    }

    public MallCategory findById(Integer id) {
        return mallCategoryMapper.selectByPrimaryKey(id);
    }

    public List<MallCategory> querySelective(String id, String name, Integer page, Integer size, String sort, String order) {
        MallCategoryExample example = new MallCategoryExample();
        MallCategoryExample.Criteria criteria = example.createCriteria();

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
        return mallCategoryMapper.selectByExample(example);
    }

    public int updateById(MallCategory category) {
        category.setUpdateTime(LocalDateTime.now());
        return mallCategoryMapper.updateByPrimaryKeySelective(category);
    }

    public void deleteById(Integer id) {
        mallCategoryMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(MallCategory category) {
        category.setAddTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        mallCategoryMapper.insertSelective(category);
    }

    public List<MallCategory> queryChannel() {
        MallCategoryExample example = new MallCategoryExample();
        example.or().andLevelEqualTo("L1").andDeletedEqualTo(false);
        return mallCategoryMapper.selectByExampleSelective(example, CHANNEL);
    }
}

package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallKeywordMapper;
import com.feel.mall.db.domain.MallKeyword;
import com.feel.mall.db.domain.MallKeywordExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallKeywordService {
    @Resource
    private MallKeywordMapper mallKeywordMapper;

    public MallKeyword queryDefault() {
        MallKeywordExample example = new MallKeywordExample();
        example.or().andIsDefaultEqualTo(true).andDeletedEqualTo(false);
        return mallKeywordMapper.selectOneByExample(example);
    }

    public List<MallKeyword> queryHots() {
        MallKeywordExample example = new MallKeywordExample();
        example.or().andIsHotEqualTo(true).andDeletedEqualTo(false);
        return mallKeywordMapper.selectByExample(example);
    }

    public List<MallKeyword> queryByKeyword(String keyword, Integer page, Integer limit) {
        MallKeywordExample example = new MallKeywordExample();
        example.setDistinct(true);
        example.or().andKeywordLike("%" + keyword + "%").andDeletedEqualTo(false);
        PageHelper.startPage(page, limit);
        return mallKeywordMapper.selectByExampleSelective(example, MallKeyword.Column.keyword);
    }

    public List<MallKeyword> querySelective(String keyword, String url, Integer page, Integer limit, String sort, String order) {
        MallKeywordExample example = new MallKeywordExample();
        MallKeywordExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        if (!StringUtils.isEmpty(url)) {
            criteria.andUrlLike("%" + url + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallKeywordMapper.selectByExample(example);
    }

    public void add(MallKeyword keywords) {
        keywords.setAddTime(LocalDateTime.now());
        keywords.setUpdateTime(LocalDateTime.now());
        mallKeywordMapper.insertSelective(keywords);
    }

    public MallKeyword findById(Integer id) {
        return mallKeywordMapper.selectByPrimaryKey(id);
    }

    public int updateById(MallKeyword keywords) {
        keywords.setUpdateTime(LocalDateTime.now());
        return mallKeywordMapper.updateByPrimaryKeySelective(keywords);
    }

    public void deleteById(Integer id) {
        mallKeywordMapper.logicalDeleteByPrimaryKey(id);
    }
}

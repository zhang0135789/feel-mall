package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallSearchHistoryMapper;
import com.feel.mall.db.domain.MallSearchHistory;
import com.feel.mall.db.domain.MallSearchHistoryExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallSearchHistoryService {
    @Resource
    private MallSearchHistoryMapper mallSearchHistoryMapper;

    public void save(MallSearchHistory searchHistory) {
        searchHistory.setAddTime(LocalDateTime.now());
        searchHistory.setUpdateTime(LocalDateTime.now());
        mallSearchHistoryMapper.insertSelective(searchHistory);
    }

    public List<MallSearchHistory> queryByUid(int uid) {
        MallSearchHistoryExample example = new MallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid).andDeletedEqualTo(false);
        example.setDistinct(true);
        return mallSearchHistoryMapper.selectByExampleSelective(example, MallSearchHistory.Column.keyword);
    }

    public void deleteByUid(int uid) {
        MallSearchHistoryExample example = new MallSearchHistoryExample();
        example.or().andUserIdEqualTo(uid);
        mallSearchHistoryMapper.logicalDeleteByExample(example);
    }

    public List<MallSearchHistory> querySelective(String userId, String keyword, Integer page, Integer size, String sort, String order) {
        MallSearchHistoryExample example = new MallSearchHistoryExample();
        MallSearchHistoryExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andKeywordLike("%" + keyword + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return mallSearchHistoryMapper.selectByExample(example);
    }
}

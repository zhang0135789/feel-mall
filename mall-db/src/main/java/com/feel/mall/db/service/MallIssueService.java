package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallIssueMapper;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.domain.MallIssue;
import com.feel.mall.db.domain.MallIssueExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallIssueService {
    @Resource
    private MallIssueMapper mallIssueMapper;

    public void deleteById(Integer id) {
        mallIssueMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(MallIssue issue) {
        issue.setAddTime(LocalDateTime.now());
        issue.setUpdateTime(LocalDateTime.now());
        mallIssueMapper.insertSelective(issue);
    }

    public List<MallIssue> querySelective(String question, Integer page, Integer limit, String sort, String order) {
        MallIssueExample example = new MallIssueExample();
        MallIssueExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(question)) {
            criteria.andQuestionLike("%" + question + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallIssueMapper.selectByExample(example);
    }

    public int updateById(MallIssue issue) {
        issue.setUpdateTime(LocalDateTime.now());
        return mallIssueMapper.updateByPrimaryKeySelective(issue);
    }

    public MallIssue findById(Integer id) {
        return mallIssueMapper.selectByPrimaryKey(id);
    }
}

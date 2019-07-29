package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallFeedbackMapper;
import com.feel.mall.db.domain.MallFeedback;
import com.feel.mall.db.domain.MallFeedbackExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Yogeek
 * @date 2018/8/27 11:39
 */
@Service
public class MallFeedbackService {
    @Autowired
    private MallFeedbackMapper mallFeedbackMapper;

    public Integer add(MallFeedback feedback) {
        feedback.setAddTime(LocalDateTime.now());
        feedback.setUpdateTime(LocalDateTime.now());
        return mallFeedbackMapper.insertSelective(feedback);
    }

    public List<MallFeedback> querySelective(Integer userId, String username, Integer page, Integer limit, String sort, String order) {
        MallFeedbackExample example = new MallFeedbackExample();
        MallFeedbackExample.Criteria criteria = example.createCriteria();

        if (userId != null) {
            criteria.andUserIdEqualTo(userId);
        }
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameLike("%" + username + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallFeedbackMapper.selectByExample(example);
    }
}

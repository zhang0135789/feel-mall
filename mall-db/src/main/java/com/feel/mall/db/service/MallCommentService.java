package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallCommentMapper;
import com.feel.mall.db.domain.MallComment;
import com.feel.mall.db.domain.MallCommentExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallCommentService {
    @Resource
    private MallCommentMapper mallCommentMapper;

    public List<MallComment> queryGoodsByGid(Integer id, int offset, int limit) {
        MallCommentExample example = new MallCommentExample();
        example.setOrderByClause(MallComment.Column.addTime.desc());
        example.or().andValueIdEqualTo(id).andTypeEqualTo((byte) 0).andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return mallCommentMapper.selectByExample(example);
    }

    public List<MallComment> query(Byte type, Integer valueId, Integer showType, Integer offset, Integer limit) {
        MallCommentExample example = new MallCommentExample();
        example.setOrderByClause(MallComment.Column.addTime.desc());
        if (showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andHasPictureEqualTo(true).andDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        PageHelper.startPage(offset, limit);
        return mallCommentMapper.selectByExample(example);
    }

    public int count(Byte type, Integer valueId, Integer showType) {
        MallCommentExample example = new MallCommentExample();
        if (showType == 0) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        } else if (showType == 1) {
            example.or().andValueIdEqualTo(valueId).andTypeEqualTo(type).andHasPictureEqualTo(true).andDeletedEqualTo(false);
        } else {
            throw new RuntimeException("showType不支持");
        }
        return (int) mallCommentMapper.countByExample(example);
    }

    public int save(MallComment comment) {
        comment.setAddTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        return mallCommentMapper.insertSelective(comment);
    }

    public List<MallComment> querySelective(String userId, String valueId, Integer page, Integer size, String sort, String order) {
        MallCommentExample example = new MallCommentExample();
        MallCommentExample.Criteria criteria = example.createCriteria();

        // type=2 是订单商品回复，这里过滤
        criteria.andTypeNotEqualTo((byte) 2);

        if (!StringUtils.isEmpty(userId)) {
            criteria.andUserIdEqualTo(Integer.valueOf(userId));
        }
        if (!StringUtils.isEmpty(valueId)) {
            criteria.andValueIdEqualTo(Integer.valueOf(valueId)).andTypeEqualTo((byte) 0);
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return mallCommentMapper.selectByExample(example);
    }

    public void deleteById(Integer id) {
        mallCommentMapper.logicalDeleteByPrimaryKey(id);
    }

    public String queryReply(Integer id) {
        MallCommentExample example = new MallCommentExample();
        example.or().andTypeEqualTo((byte) 2).andValueIdEqualTo(id);
        List<MallComment> commentReply = mallCommentMapper.selectByExampleSelective(example, MallComment.Column.content);
        // 目前业务只支持回复一次
        if (commentReply.size() == 1) {
            return commentReply.get(0).getContent();
        }
        return null;
    }

    public MallComment findById(Integer id) {
        return mallCommentMapper.selectByPrimaryKey(id);
    }
}

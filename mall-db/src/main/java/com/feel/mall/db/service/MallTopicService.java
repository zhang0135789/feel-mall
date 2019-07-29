package com.feel.mall.db.service;

import com.feel.mall.db.dao.MallTopicMapper;
import com.github.pagehelper.PageHelper;
import com.feel.mall.db.domain.MallTopic;
import com.feel.mall.db.domain.MallTopic.Column;
import com.feel.mall.db.domain.MallTopicExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallTopicService {
    @Resource
    private MallTopicMapper mallTopicMapper;
    private Column[] columns = new Column[]{Column.id, Column.title, Column.subtitle, Column.price, Column.picUrl, Column.readCount};

    public List<MallTopic> queryList(int offset, int limit) {
        return queryList(offset, limit, "add_time", "desc");
    }

    public List<MallTopic> queryList(int offset, int limit, String sort, String order) {
        MallTopicExample example = new MallTopicExample();
        example.or().andDeletedEqualTo(false);
        example.setOrderByClause(sort + " " + order);
        PageHelper.startPage(offset, limit);
        return mallTopicMapper.selectByExampleSelective(example, columns);
    }

    public int queryTotal() {
        MallTopicExample example = new MallTopicExample();
        example.or().andDeletedEqualTo(false);
        return (int) mallTopicMapper.countByExample(example);
    }

    public MallTopic findById(Integer id) {
        MallTopicExample example = new MallTopicExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        return mallTopicMapper.selectOneByExampleWithBLOBs(example);
    }

    public List<MallTopic> queryRelatedList(Integer id, int offset, int limit) {
        MallTopicExample example = new MallTopicExample();
        example.or().andIdEqualTo(id).andDeletedEqualTo(false);
        List<MallTopic> topics = mallTopicMapper.selectByExample(example);
        if (topics.size() == 0) {
            return queryList(offset, limit, "add_time", "desc");
        }
        MallTopic topic = topics.get(0);

        example = new MallTopicExample();
        example.or().andIdNotEqualTo(topic.getId()).andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        List<MallTopic> relateds = mallTopicMapper.selectByExampleWithBLOBs(example);
        if (relateds.size() != 0) {
            return relateds;
        }

        return queryList(offset, limit, "add_time", "desc");
    }

    public List<MallTopic> querySelective(String title, String subtitle, Integer page, Integer limit, String sort, String order) {
        MallTopicExample example = new MallTopicExample();
        MallTopicExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        if (!StringUtils.isEmpty(subtitle)) {
            criteria.andSubtitleLike("%" + subtitle + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return mallTopicMapper.selectByExampleWithBLOBs(example);
    }

    public int updateById(MallTopic topic) {
        topic.setUpdateTime(LocalDateTime.now());
        MallTopicExample example = new MallTopicExample();
        example.or().andIdEqualTo(topic.getId());
        return mallTopicMapper.updateByExampleSelective(topic, example);
    }

    public void deleteById(Integer id) {
        mallTopicMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(MallTopic topic) {
        topic.setAddTime(LocalDateTime.now());
        topic.setUpdateTime(LocalDateTime.now());
        mallTopicMapper.insertSelective(topic);
    }


}

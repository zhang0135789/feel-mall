package com.feel.mall.db.service;

import com.github.pagehelper.PageHelper;
import com.feel.mall.db.dao.MallLogMapper;
import com.feel.mall.db.domain.MallLog;
import com.feel.mall.db.domain.MallLogExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MallLogService {
    @Resource
    private MallLogMapper mallLogMapper;

    public void deleteById(Integer id) {
        mallLogMapper.logicalDeleteByPrimaryKey(id);
    }

    public void add(MallLog log) {
        log.setAddTime(LocalDateTime.now());
        log.setUpdateTime(LocalDateTime.now());
        mallLogMapper.insertSelective(log);
    }

    public List<MallLog> querySelective(String name, Integer page, Integer size, String sort, String order) {
        MallLogExample example = new MallLogExample();
        MallLogExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andAdminLike("%" + name + "%");
        }
        criteria.andDeletedEqualTo(false);

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return mallLogMapper.selectByExample(example);
    }
}

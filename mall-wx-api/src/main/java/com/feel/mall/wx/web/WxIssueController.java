package com.feel.mall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.feel.mall.core.util.ResponseUtil;
import com.feel.mall.core.validator.Order;
import com.feel.mall.core.validator.Sort;
import com.feel.mall.db.domain.MallIssue;
import com.feel.mall.db.service.MallIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wx/issue")
@Validated
public class WxIssueController {
    private final Log logger = LogFactory.getLog(WxIssueController.class);

    @Autowired
    private MallIssueService issueService;

    /**
     * 帮助中心
     */
    @RequestMapping("/list")
    public Object list(String question,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<MallIssue> issueList = issueService.querySelective(question, page, size, sort, order);
        return ResponseUtil.okList(issueList);
    }

}

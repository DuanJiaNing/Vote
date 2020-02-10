package com.duan.vote.api;

import com.duan.vote.common.PageModel;
import com.duan.vote.common.ResultModel;
import com.duan.vote.config.Config;
import com.duan.vote.dto.TopicCriteriaDTO;
import com.duan.vote.dto.TopicDTO;
import com.duan.vote.dto.TopicSummaryDTO;
import com.duan.vote.dto.UserDTO;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.TopicService;
import com.duan.vote.service.UserService;
import com.duan.vote.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/topic")
@Slf4j
public class TopicController {

    @Reference
    private TopicService topicService;

    @Autowired
    private Config config;

    @Reference
    private UserService userService;

    @GetMapping("/{id}")
    public ResultModel<TopicSummaryDTO> get(@PathVariable Integer id) {
        TopicSummaryDTO summary = topicService.getSummary(id);
        if (summary == null) {
            return ResultUtils.checked("主题不存在");
        }

        return ResultUtils.success(summary);
    }

    @GetMapping("/list/my/interest")
    public ResultModel<PageModel<TopicSummaryDTO>> listMyInterestTopic(@RequestParam(required = false) String keyWord,
                                                                       @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                                       @RequestHeader("uid") String uid) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.checked("用户不存在");
        }
        TopicCriteriaDTO criteria = newDefaultTopicCriteriaDTO(pageSize, pageNum);
        criteria.setUserId(user.getId());
        criteria.setKeyword(keyWord);
        PageInfo<TopicSummaryDTO> page = topicService.listInterest(criteria);
        return ResultUtils.successPaged(page);
    }

    @GetMapping("/list/my")
    public ResultModel<PageModel<TopicSummaryDTO>> listMyTopic(@RequestParam(required = false) String keyWord,
                                                               @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                               @RequestHeader("uid") String uid) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.checked("用户不存在");
        }
        TopicCriteriaDTO criteria = newDefaultTopicCriteriaDTO(pageSize, pageNum);
        criteria.setUserId(user.getId());
        criteria.setKeyword(keyWord);
        PageInfo<TopicSummaryDTO> page = topicService.listSummary(criteria);
        return ResultUtils.successPaged(page);
    }

    @GetMapping("/list")
    public ResultModel<PageModel<TopicSummaryDTO>> listAllTopic(@RequestParam(required = false) String keyWord,
                                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        TopicCriteriaDTO criteria = newDefaultTopicCriteriaDTO(pageSize, pageNum);
        criteria.setKeyword(keyWord);
        PageInfo<TopicSummaryDTO> page = topicService.listSummary(criteria);
        return ResultUtils.successPaged(page);
    }

    @GetMapping("/list/my/history")
    public ResultModel<PageModel<TopicDTO>> listMyAddTopicHistory(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                                  @RequestHeader("uid") String uid) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.checked("用户不存在");
        }

        TopicCriteriaDTO criteria = newDefaultTopicCriteriaDTO(pageSize, pageNum);
        criteria.setUserId(user.getId());
        PageInfo<TopicDTO> page = topicService.simpleList(criteria);
        return ResultUtils.successPaged(page);
    }

    private TopicCriteriaDTO newDefaultTopicCriteriaDTO(Integer pageSize, Integer pageNum) {
        TopicCriteriaDTO criteria = new TopicCriteriaDTO();
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return criteria;
    }

    @PostMapping
    public ResultModel<TopicDTO> add(@RequestBody TopicDTO topic, @RequestHeader("uid") String uid) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.checked("用户不存在");
        }

        if (StringUtils.isBlank(topic.getTitle())) {
            return ResultUtils.checked("请输入标题");
        }

        Config.Topic topicC = config.getTopic();
        if (topic.getTitle().length() > topicC.getWordLimit()) {
            return ResultUtils.checked("标题字数需要控制在 " + topicC.getWordLimit() + " 字以内");
        }

        if (StringUtils.isNotBlank(topic.getNotes()) && topic.getNotes().length() > topicC.getNotesLimit()) {
            return ResultUtils.checked("备注字数需要控制在 " + topicC.getNotesLimit() + " 字以内");
        }

        try {
            TopicDTO tdto = topicService.add(topic.getTitle(), topic.getNotes(), user.getId());
            return ResultUtils.success(tdto);
        } catch (ServiceException e) {
            return ResultUtils.fail(e);
        }
    }
}

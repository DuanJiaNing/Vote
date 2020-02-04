package com.duan.vote.api;

import com.duan.service.TopicService;
import com.duan.service.dto.TopicCriteriaDTO;
import com.duan.service.dto.TopicDTO;
import com.duan.service.enums.TopicStatus;
import com.duan.service.exceptions.TopicException;
import com.duan.vote.common.PageModel;
import com.duan.vote.common.ResultModel;
import com.duan.vote.config.Config;
import com.duan.vote.dto.InterestTopicStatsCriteriaDTO;
import com.duan.vote.dto.TopicStatsCriteriaDTO;
import com.duan.vote.dto.TopicSummaryDTO;
import com.duan.vote.dto.UserDTO;
import com.duan.vote.exceptions.CheckedException;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.TopicStatsService;
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

    @Reference
    private TopicStatsService topicStatsService;

    @Autowired
    private Config config;

    @Reference
    private UserService userService;

    @GetMapping("/{id}")
    public ResultModel<TopicSummaryDTO> get(@PathVariable Integer id) {
        TopicSummaryDTO summary = topicStatsService.getSummary(id);
        if (summary == null) {
            return ResultUtils.checked("主题不存在");
        }

        return ResultUtils.success(summary);
    }

    @GetMapping("/list/my/interest")
    public ResultModel<PageModel<TopicSummaryDTO>> listMyInterestTopic(@RequestParam(required = false) String keyWord,
                                                                       @RequestParam(required = false) Integer keyWordType,
                                                                       @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                                       @RequestHeader("uid") String uid) {
        UserDTO owner = userService.getUserByUid(uid);
        if (owner == null) {
            return ResultUtils.checked("用户不存在");
        }
        InterestTopicStatsCriteriaDTO criteria = newDefaultInterestTopicStatsCriteriaDTO(pageSize, pageNum);
        criteria.setOwnerId(owner.getId());
        try {
            handleKeyWord(keyWordType, keyWord, criteria, true);
        } catch (Throwable e) {
            return ResultUtils.processException(e);
        }
        PageInfo<TopicSummaryDTO> page = topicStatsService.listInterest(criteria);
        return ResultUtils.successPaged(page);
    }

    private void handleKeyWord(Integer keyWordType, String keyWord, TopicStatsCriteriaDTO criteria,
                               boolean allowSpecUser) throws Throwable {
        if (keyWordType != null) {
            switch (keyWordType) {
                case 2: // 用户 id
                    if (allowSpecUser) {
                        UserDTO user = userService.getUserById(Integer.parseInt(keyWord));
                        if (user == null) {
                            throw new CheckedException("用户不存在");
                        }
                        criteria.setUserId(user.getUid());
                        break;
                    } else {
                        throw new ServiceException("not allowed to search by userId");
                    }
                case 3: // 主题 id
                    criteria.setId(Integer.valueOf(keyWord));
                    break;
                default:
                    criteria.setTitle(keyWord);
            }
        } else if (StringUtils.isNotBlank(keyWord)) {
            criteria.setTitle(keyWord);
        }
    }

    private InterestTopicStatsCriteriaDTO newDefaultInterestTopicStatsCriteriaDTO(Integer pageSize, Integer pageNum) {
        InterestTopicStatsCriteriaDTO criteria = new InterestTopicStatsCriteriaDTO();
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        criteria.setStatus(TopicStatus.FINE.getCode());
        criteria.setAppId(config.getAppId());
        return criteria;
    }

    @GetMapping("/list/my")
    public ResultModel<PageModel<TopicSummaryDTO>> listMyTopic(@RequestParam(required = false) String keyWord,
                                                               @RequestParam(required = false) Integer keyWordType,
                                                               @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                               @RequestHeader("uid") String uid) {
        UserDTO owner = userService.getUserByUid(uid);
        if (owner == null) {
            return ResultUtils.checked("用户不存在");
        }
        TopicStatsCriteriaDTO criteria = newDefaultTopicStatsCriteriaDTO(pageSize, pageNum);
        criteria.setUserId(owner.getUid());
        try {
            handleKeyWord(keyWordType, keyWord, criteria, false);
        } catch (Throwable e) {
            return ResultUtils.processException(e);
        }
        PageInfo<TopicSummaryDTO> page = topicStatsService.listSummary(criteria);
        return ResultUtils.successPaged(page);
    }

    @GetMapping("/list")
    public ResultModel<PageModel<TopicSummaryDTO>> listAllTopic(@RequestParam(required = false) String keyWord,
                                                                @RequestParam(required = false) Integer keyWordType,
                                                                @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                                @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        TopicStatsCriteriaDTO criteria = newDefaultTopicStatsCriteriaDTO(pageSize, pageNum);
        try {
            handleKeyWord(keyWordType, keyWord, criteria, true);
        } catch (Throwable e) {
            return ResultUtils.processException(e);
        }
        PageInfo<TopicSummaryDTO> page = topicStatsService.listSummary(criteria);
        return ResultUtils.successPaged(page);
    }

    private TopicStatsCriteriaDTO newDefaultTopicStatsCriteriaDTO(Integer pageSize, Integer pageNum) {
        TopicStatsCriteriaDTO criteria = new TopicStatsCriteriaDTO();
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        criteria.setStatus(TopicStatus.FINE.getCode());
        criteria.setAppId(config.getAppId());
        return criteria;
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
        criteria.setUserId(user.getUid());
        PageInfo<TopicDTO> page = topicService.simpleList(criteria);
        return ResultUtils.successPaged(page);
    }

    private TopicCriteriaDTO newDefaultTopicCriteriaDTO(Integer pageSize, Integer pageNum) {
        TopicCriteriaDTO criteria = new TopicCriteriaDTO();
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        criteria.setStatus(TopicStatus.FINE.getCode());
        criteria.setAppId(config.getAppId());
        return criteria;
    }

    @PostMapping("/add")
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
            TopicDTO tdto = topicService.add(topic.getTitle(), topic.getNotes(), uid, config.getAppId());
            return ResultUtils.success(tdto);
        } catch (TopicException e) {
            return ResultUtils.fail(e);
        }
    }
}

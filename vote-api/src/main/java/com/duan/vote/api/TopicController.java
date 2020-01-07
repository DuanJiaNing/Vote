package com.duan.vote.api;

import com.duan.service.CommentService;
import com.duan.service.TopicService;
import com.duan.service.dto.CommentDTO;
import com.duan.service.dto.PageCondition;
import com.duan.service.dto.TopicDTO;
import com.duan.service.exceptions.TopicException;
import com.duan.vote.common.PageModel;
import com.duan.vote.common.ResultModel;
import com.duan.vote.config.Config;
import com.duan.vote.manager.ControllerManager;
import com.duan.vote.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Reference
    private TopicService topicService;

    @Reference
    private CommentService commentService;

    @Autowired
    private ControllerManager controllerManager;

    @Autowired
    private Config config;

    @PostMapping
    public ResultModel<TopicDTO> add(@RequestParam String title, @RequestParam(required = false) String notes) {
        if (StringUtils.isBlank(title)) {
            return ResultUtils.error("请输入标题");
        }

        Config.Topic topicC = config.getTopic();
        if (title.length() > topicC.getWordLimit()) {
            return ResultUtils.error("标题字数需要控制在 " + topicC.getWordLimit() + " 字以内");
        }

        if (StringUtils.isNotBlank(notes) && notes.length() > topicC.getNotesLimit()) {
            return ResultUtils.error("备注字数需要控制在 " + topicC.getNotesLimit() + " 字以内");
        }

        try {
            TopicDTO topic = topicService.add(title, notes);
            return ResultUtils.success(topic);
        } catch (TopicException e) {
            return ResultUtils.fail(e);
        }
    }

    @GetMapping("/{topicId}")
    public ResultModel<TopicDTO> get(@PathVariable Integer topicId) {
        TopicDTO topic = topicService.get(topicId);
        return ResultUtils.success(topic);
    }

    @GetMapping("/list")
    public ResultModel<PageModel<TopicDTO>> list(@RequestParam(defaultValue = "1") Integer pageNum,
                                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        if (pageNum < 0 || pageSize <= 0) {
            return ResultUtils.fail("pageNum or pageSize incorrect", HttpStatus.BAD_REQUEST);
        }

        PageInfo<TopicDTO> page = topicService.list(new PageCondition(pageNum, pageSize));
        if (ResultUtils.emptyPage(page)) {
            return ResultUtils.success(null);
        }

        return ResultUtils.successPaged(page);
    }

    @PutMapping("/{topicId}/like")
    public ResultModel<TopicDTO> like(@PathVariable Integer topicId) {
        try {
            TopicDTO topic = topicService.like(topicId);
            return ResultUtils.success(topic);
        } catch (TopicException e) {
            return ResultUtils.fail(e);
        }
    }

    @PutMapping("/{topicId}/dislike")
    public ResultModel<TopicDTO> dislike(@PathVariable Integer topicId) {
        try {
            TopicDTO topic = topicService.dislike(topicId);
            return ResultUtils.success(topic);
        } catch (TopicException e) {
            return ResultUtils.fail(e);
        }
    }

    @GetMapping("/{topicId}/comments")
    public ResultModel<PageModel<CommentDTO>> allTopicComments(@PathVariable Integer topicId,
                                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                                               @RequestParam(defaultValue = "10") Integer pageSize) {
        if (pageNum < 0 || pageSize <= 0) {
            return ResultUtils.fail("pageNum or pageSize incorrect", HttpStatus.BAD_REQUEST);
        }

        PageInfo<CommentDTO> page = commentService.listByTopic(topicId, new PageCondition(pageNum, pageSize));
        if (ResultUtils.emptyPage(page)) {
            return ResultUtils.success(null);
        }

        return ResultUtils.successPaged(page);
    }

}

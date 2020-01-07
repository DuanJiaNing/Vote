package com.duan.vote.api;

import com.duan.service.CommentService;
import com.duan.service.dto.CommentDTO;
import com.duan.service.exceptions.CommentException;
import com.duan.vote.common.ResultModel;
import com.duan.vote.config.Config;
import com.duan.vote.manager.CommonManager;
import com.duan.vote.utils.ResultUtils;
import org.apache.dubbo.common.utils.StringUtils;
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
@RequestMapping("/api/comment")
public class CommentController {

    @Reference
    private CommentService commentService;

    @Autowired
    private CommonManager commonManager;

    @Autowired
    private Config config;

    @PostMapping
    public ResultModel<CommentDTO> add(@RequestParam String content, @RequestParam Integer topicId) {
        if (StringUtils.isBlank(content)) {
            return ResultUtils.error("请输入内容");
        }

        Config.Comment commentC = config.getComment();
        if (content.length() > commentC.getWordLimit()) {
            return ResultUtils.error("字数需要控制在 " + commentC.getWordLimit() + " 字以内");
        }

        if (!commonManager.topicExist(topicId)) {
            return ResultUtils.fail("topic is not exist: id=" + topicId, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            CommentDTO comment = commentService.add(content, topicId);
            return ResultUtils.success(comment);
        } catch (CommentException e) {
            return ResultUtils.fail(e);
        }
    }

    @GetMapping("/{commentId}")
    public ResultModel<CommentDTO> get(@PathVariable Integer commentId) {
        CommentDTO comment = commentService.get(commentId);
        return ResultUtils.success(comment);
    }

    @PutMapping("/{commentId}/dislike")
    public ResultModel<CommentDTO> dislike(@PathVariable Integer commentId) {
        try {
            CommentDTO comment = commentService.dislike(commentId);
            return ResultUtils.success(comment);
        } catch (CommentException e) {
            return ResultUtils.fail(e);
        }
    }

    @PutMapping("/{commentId}/like")
    public ResultModel<CommentDTO> like(@PathVariable Integer commentId) {
        try {
            CommentDTO comment = commentService.like(commentId);
            return ResultUtils.success(comment);
        } catch (CommentException e) {
            return ResultUtils.fail(e);
        }
    }

}

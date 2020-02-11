package com.duan.vote.api;

import com.duan.vote.common.CommentVoteVo;
import com.duan.vote.common.PageModel;
import com.duan.vote.common.ResultModel;
import com.duan.vote.config.Config;
import com.duan.vote.dto.CommentCriteriaDTO;
import com.duan.vote.dto.CommentDTO;
import com.duan.vote.dto.CommentSummaryDTO;
import com.duan.vote.dto.UserDTO;
import com.duan.vote.enums.Vote;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.CommentService;
import com.duan.vote.service.UserService;
import com.duan.vote.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2020/02/10.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Reference
    private UserService userService;

    @Reference
    private CommentService commentService;

    @Autowired
    private Config config;

    @PostMapping("/vote")
    public ResultModel<CommentSummaryDTO> vote(@RequestBody CommentVoteVo vo, @RequestHeader("uid") String uid) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.checked("用户不存在");
        }

        if (Vote.valueOf(vo.getVote()) == null) {
            return ResultUtils.fail("invalid parameter value: vote=" + vo.getVote());
        }

        try {
            return ResultUtils.success(commentService.vote(user.getId(), vo.getCommentId(), Vote.valueOf(vo.getVote())));
        } catch (ServiceException e) {
            return ResultUtils.fail(e);
        }
    }

    @GetMapping("/list")
    public ResultModel<PageModel<CommentSummaryDTO>> listComment(@RequestHeader("uid") String uid,
                                                                 @RequestParam Integer topicId,
                                                                 @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.checked("用户不存在");
        }

        CommentCriteriaDTO criteria = new CommentCriteriaDTO();
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        criteria.setMyUserId(user.getId());
        criteria.setTopicId(topicId);
        PageInfo<CommentSummaryDTO> page = commentService.listSummary(criteria);
        return ResultUtils.successPaged(page);
    }

    @PostMapping
    public ResultModel<CommentDTO> add(@RequestBody CommentDTO dto, @RequestHeader("uid") String uid) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.checked("用户不存在");
        }
        if (dto.getTopicId() == null) {
            return ResultUtils.fail("missing parameter: topicId");
        }

        if (dto.getVote() == null) {
            return ResultUtils.fail("missing parameter: vote");
        }
        if (Vote.valueOf(dto.getVote()) == null) {
            return ResultUtils.fail("invalid parameter value: vote=" + dto.getVote());
        }

        if (StringUtils.isBlank(dto.getContent())) {
            return ResultUtils.checked("请输入投票说明");
        }

        Config.Comment commentF = config.getComment();
        if (dto.getContent().length() > commentF.getWordLimit()) {
            return ResultUtils.checked("字数需要控制在 " + commentF.getWordLimit() + " 字以内");
        }

        try {
            return ResultUtils.success(commentService.add(dto.getContent(), dto.getTopicId(),
                    user.getId(), Vote.valueOf(dto.getVote())));
        } catch (ServiceException e) {
            return ResultUtils.fail(e);
        }
    }
}

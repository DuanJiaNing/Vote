package com.duan.vote.api;

import com.duan.service.TopicService;
import com.duan.service.dto.TopicDTO;
import com.duan.service.exceptions.TopicException;
import com.duan.vote.common.ResultModel;
import com.duan.vote.config.Config;
import com.duan.vote.dto.UserDTO;
import com.duan.vote.service.UserService;
import com.duan.vote.utils.ResultUtils;
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
public class TopicController {

    @Reference
    private TopicService topicService;

    @Autowired
    private Config config;

    @Reference
    private UserService userService;

    @PostMapping("/add")
    public ResultModel<TopicDTO> add(@RequestBody TopicDTO topic, @RequestHeader("uid") String uid) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.error("用户不存在");
        }

        if (StringUtils.isBlank(topic.getTitle())) {
            return ResultUtils.error("请输入标题");
        }

        Config.Topic topicC = config.getTopic();
        if (topic.getTitle().length() > topicC.getWordLimit()) {
            return ResultUtils.error("标题字数需要控制在 " + topicC.getWordLimit() + " 字以内");
        }

        if (StringUtils.isNotBlank(topic.getNotes()) && topic.getNotes().length() > topicC.getNotesLimit()) {
            return ResultUtils.error("备注字数需要控制在 " + topicC.getNotesLimit() + " 字以内");
        }

        try {
            TopicDTO tdto = topicService.add(topic.getTitle(), topic.getNotes(), uid, config.getAppId());
            return ResultUtils.success(tdto);
        } catch (TopicException e) {
            return ResultUtils.fail(e);
        }
    }
}

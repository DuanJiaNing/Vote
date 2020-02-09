package com.duan.vote.api;

import com.duan.vote.common.ResultModel;
import com.duan.vote.config.Config;
import com.duan.vote.dto.CommentDTO;
import com.duan.vote.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Reference
    private UserService userService;

    @Autowired
    private Config config;

    @PostMapping()
    public ResultModel<CommentDTO> add(@RequestBody CommentDTO dto, @RequestHeader("uid") String uid) {
        return null;
    }
}

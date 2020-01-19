package com.duan.vote.api;


import com.duan.vote.common.ResultModel;
import com.duan.vote.dto.UserDTO;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.UserService;
import com.duan.vote.utils.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2020/1/11.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private UserService userService;

    @PostMapping("/login")
    public ResultModel<UserDTO> login(@RequestBody String uidKey) {
        if (StringUtils.isBlank(uidKey)) {
            return ResultUtils.fail("uidKey is required");
        }

        try {
            UserDTO uid = userService.getUserUid(uidKey);
            return ResultUtils.success(uid);
        } catch (ServiceException e) {
            return ResultUtils.fail(e);
        }
    }

}

package com.duan.vote.api;

import com.duan.vote.common.PageModel;
import com.duan.vote.common.ResultModel;
import com.duan.vote.config.Config;
import com.duan.vote.dto.SearchHistoryCriteriaDTO;
import com.duan.vote.dto.SearchHistoryDTO;
import com.duan.vote.dto.UserDTO;
import com.duan.vote.exceptions.ServiceException;
import com.duan.vote.service.SearchService;
import com.duan.vote.service.UserService;
import com.duan.vote.utils.ResultUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2020/01/19.
 *
 * @author DuanJiaNing
 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Reference
    private SearchService searchService;

    @Reference
    private UserService userService;

    @Autowired
    private Config config;

    @GetMapping("/history")
    public ResultModel<PageModel<SearchHistoryDTO>> history(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                            @RequestHeader("uid") String uid) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.checked("用户不存在");
        }

        SearchHistoryCriteriaDTO criteria = new SearchHistoryCriteriaDTO();
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        criteria.setUserId(user.getId());
        PageInfo<SearchHistoryDTO> page = searchService.list(criteria);
        return ResultUtils.successPaged(page);
    }

    @PostMapping
    public ResultModel<SearchHistoryDTO> add(@RequestBody String content, @RequestHeader("uid") String uid) {
        UserDTO user = userService.getUserByUid(uid);
        if (user == null) {
            return ResultUtils.checked("用户不存在");
        }

        if (StringUtils.isBlank(content)) {
            return ResultUtils.checked("请输入关键字");
        }

        Config.Search searchC = config.getSearch();
        if (content.length() > searchC.getWordLimit()) {
            return ResultUtils.checked("字数需要控制在 " + searchC.getWordLimit() + " 字以内");
        }

        try {
            SearchHistoryDTO tdto = searchService.add(content, user.getId());
            return ResultUtils.success(tdto);
        } catch (ServiceException e) {
            return ResultUtils.fail(e);
        }
    }
}

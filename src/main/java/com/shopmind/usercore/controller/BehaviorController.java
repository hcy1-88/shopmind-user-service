package com.shopmind.usercore.controller;

import com.shopmind.framework.annotation.RequireAuth;
import com.shopmind.framework.context.ResultContext;
import com.shopmind.usercore.dto.request.BehaviorCreatedRequestDTO;
import com.shopmind.usercore.dto.request.UserBehaviorRequest;
import com.shopmind.usercore.dto.response.UserBehaviorResponseDTO;
import com.shopmind.usercore.service.UserBehaviorsService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * Author: huangcy
 * Date: 2026-01-01
 */
@RestController
@RequestMapping("/behavior")
public class BehaviorController {

    @Resource
    private UserBehaviorsService userBehaviorsService;

    /**
     * 获取用户最近 day 天的行为历史，并过滤出 targetType
     * @return 用户行为响应对象
     */
    @PostMapping("/{userId}")
    public ResultContext<List<UserBehaviorResponseDTO>> getsUserHistoryBehavior(
            @PathVariable("userId") Long userId,
            @Valid @RequestBody UserBehaviorRequest userBehaviorRequest) {
        if (userBehaviorRequest.getUserId() == null) {
            userBehaviorRequest.setUserId(userId);
        }
        List<UserBehaviorResponseDTO> userBehaviors = userBehaviorsService.findUserBehaviors(userBehaviorRequest);
        return ResultContext.success(userBehaviors);
    }

    @PostMapping("/creation")
    public ResultContext<Void> createBehavior(@Valid @RequestBody BehaviorCreatedRequestDTO behaviorCreatedRequestDTO) {
        userBehaviorsService.createUserBehavior(behaviorCreatedRequestDTO);
        return ResultContext.success();
    }

}

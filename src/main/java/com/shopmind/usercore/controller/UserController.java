package com.shopmind.usercore.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.shopmind.framework.annotation.RequireAuth;
import com.shopmind.framework.constant.ServiceNameConstant;
import com.shopmind.framework.constant.ShopmindHeaderConstant;
import com.shopmind.framework.context.ResultContext;
import com.shopmind.framework.context.UserContext;
import com.shopmind.usercore.dto.request.SetPasswordRequest;
import com.shopmind.usercore.dto.request.UpdateUserRequest;
import com.shopmind.usercore.dto.response.UserResponseDTO;
import com.shopmind.usercore.exception.UserServiceClientException;
import com.shopmind.usercore.service.UsersService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * Description: 用户接口
 * Author: huangcy
 * Date: 2025-12-12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Resource
    private UsersService usersService;

    /**
     * 手机号查询用户
     * @param phoneNumber 手机号
     * @return 用户
     */
    @GetMapping("/by-phone/{phoneNumber}")
    public UserResponseDTO getUserByPhone(@PathVariable("phoneNumber") String phoneNumber){
        return usersService.getUserByPhoneNumber(phoneNumber);
    }

    /**
     * 注册新用户（手机号）
     * @param phoneNumber 手机号
     * @return 用户
     */
    @PostMapping("/register-by-phone")
    public UserResponseDTO registerByPhone(@RequestParam String phoneNumber){
        return usersService.createUserByPhoneNumber(phoneNumber);
    }

    /**
     * 根据 id 查询用户
     * @param userId 用户 id
     * @return 用户
     */
    @GetMapping("/{userId}")
    public UserResponseDTO getUserByUserId(@PathVariable("userId") Long userId){
        return usersService.getByUserId(userId);
    }

    /**
     * 修改用户
     * @param userId 用户 id
     * @param request 用户信息
     * @return 更新后的用户
     */
    @PostMapping("/{userId}/update")
    public UserResponseDTO updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UpdateUserRequest request) {
        return usersService.updateUser(userId, request);
    }

    /**
     * 为已登录的用户重置密码
     * @param request 密码
     */
    @RequireAuth
    @PostMapping("/password")
    public void updatePasswordForLoggedIn(
            @RequestHeader(ShopmindHeaderConstant.Calling_SERVICE_HEADER) String callerService,
            @Valid @RequestBody SetPasswordRequest request) {
        if (StrUtil.equals(callerService, ServiceNameConstant.AUTH_SERVICE)) {
            throw new UserServiceClientException("USER0006");
        }
        usersService.updatePassword(UserContext.userId(), request.getNewPassword());
    }


    /**
     * 手机号设置密码
     */
    @PostMapping("/password/{phoneNumber}")
    public void setPasswordByPhoneNumber(
            @RequestHeader(ShopmindHeaderConstant.Calling_SERVICE_HEADER) String callerService,
            @PathVariable("phoneNumber") String phoneNumber,
            @Valid @RequestBody SetPasswordRequest request) {
        if (StrUtil.equals(callerService, ServiceNameConstant.AUTH_SERVICE)) {
            throw new UserServiceClientException("USER0006");
        }
        Preconditions.checkNotNull(phoneNumber, "手机号不能为空");
        usersService.setPasswordByPhoneNumber(phoneNumber, request.getNewPassword());
    }
}

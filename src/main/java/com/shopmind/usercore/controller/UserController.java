package com.shopmind.usercore.controller;

import com.shopmind.framework.context.ResultContext;
import com.shopmind.usercore.dto.request.UpdateUserRequest;
import com.shopmind.usercore.dto.response.UserResponseDTO;
import com.shopmind.usercore.service.UsersService;
import jakarta.annotation.Resource;
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
    public ResultContext<UserResponseDTO> getUserByPhone(@PathVariable("phoneNumber") String phoneNumber){
        UserResponseDTO userByPhoneNumber = usersService.getUserByPhoneNumber(phoneNumber);
        return ResultContext.success(userByPhoneNumber);
    }

    /**
     * 注册新用户（手机号）
     * @param phoneNumber 手机号
     * @return 用户
     */
    @PostMapping("/register-by-phone")
    public ResultContext<UserResponseDTO> registerByPhone(@RequestParam String phoneNumber){
        UserResponseDTO userByPhoneNumber = usersService.createUserByPhoneNumber(phoneNumber);
        return ResultContext.success(userByPhoneNumber);
    }

    /**
     * 根据 id 查询用户
     * @param userId 用户 id
     * @return 用户
     */
    @GetMapping("/{userId}")
    ResultContext<UserResponseDTO> getUserByUserId(@PathVariable("userId") String userId){
        UserResponseDTO byUserId = usersService.getByUserId(userId);
        return ResultContext.success(byUserId);
    }

    /**
     * 修改用户
     * @param userId 用户 id
     * @param request 用户信息
     * @return 更新后的用户
     */
    @PostMapping("/{userId}/update")
    public ResultContext<UserResponseDTO> updateUser(
            @PathVariable("userId") Long userId,
            @RequestBody UpdateUserRequest request) {
        UserResponseDTO updated = usersService.updateUser(userId, request);
        return ResultContext.success(updated);
    }

}

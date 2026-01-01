package com.shopmind.usercore.controller;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.shopmind.framework.annotation.RequireAuth;
import com.shopmind.framework.constant.ServiceNameConstant;
import com.shopmind.framework.constant.ShopmindHeaderConstant;
import com.shopmind.framework.context.ResultContext;
import com.shopmind.framework.context.UserContext;
import com.shopmind.usercore.dto.business.InterestDto;
import com.shopmind.usercore.dto.business.UserPreferencesDto;
import com.shopmind.usercore.dto.request.AddressRequestDto;
import com.shopmind.usercore.dto.request.SetPasswordRequest;
import com.shopmind.usercore.dto.request.UpdateUserRequest;
import com.shopmind.usercore.dto.response.AddressResponseDto;
import com.shopmind.usercore.dto.response.UserInterestsResponseDTO;
import com.shopmind.usercore.dto.response.UserResponseDTO;
import com.shopmind.usercore.exception.UserServiceException;
import com.shopmind.usercore.service.InterestService;
import com.shopmind.usercore.service.UsersAddressesService;
import com.shopmind.usercore.service.UsersPreferencesService;
import com.shopmind.usercore.service.UsersService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Resource
    private UsersPreferencesService usersPreferencesService;

    @Resource
    private UsersAddressesService userAddressesService;

    @Resource
    private InterestService interestService;

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
    public ResultContext<UserResponseDTO> getUserByUserId(@PathVariable("userId") Long userId){
        UserResponseDTO byUserId = usersService.getByUserId(userId);
        return ResultContext.success(byUserId);
    }


    /**
     * 为已登录的用户重置密码
     * @param request 密码
     */
    @RequireAuth
    @PostMapping("/password")
    public ResultContext<Void> updatePasswordForLoggedIn(
            @RequestHeader(ShopmindHeaderConstant.Calling_SERVICE_HEADER) String callerService,
            @Valid @RequestBody SetPasswordRequest request) {
        if (!StrUtil.equals(callerService, ServiceNameConstant.AUTH_SERVICE)) {
            throw new UserServiceException("USER0006");
        }
        usersService.updatePassword(UserContext.userId(), request.getNewPassword());
        return ResultContext.success();
    }


    /**
     * 手机号设置密码
     */
    @PostMapping("/password/{phoneNumber}")
    public ResultContext<Void> setPasswordByPhoneNumber(
            @RequestHeader(ShopmindHeaderConstant.Calling_SERVICE_HEADER) String callerService,
            @PathVariable("phoneNumber") String phoneNumber,
            @Valid @RequestBody SetPasswordRequest request) {
        if (!StrUtil.equals(callerService, ServiceNameConstant.AUTH_SERVICE)) {
            throw new UserServiceException("USER0006");
        }
        Preconditions.checkNotNull(phoneNumber, "手机号不能为空");
        usersService.setPasswordByPhoneNumber(phoneNumber, request.getNewPassword());
        return ResultContext.success();
    }

    @RequireAuth
    @PostMapping("/me/profile")
    public ResultContext<UserResponseDTO> updateUserProfile(@RequestBody UpdateUserRequest request) {
        UserResponseDTO userResponseDTO = usersService.updateUser(UserContext.userId(), request);
        return ResultContext.success(userResponseDTO);
    }

    @RequireAuth
    @GetMapping("/me/preferences")
    public ResultContext<UserPreferencesDto> getUserPreferences (){
        UserPreferencesDto res = usersPreferencesService.getUserPreferencesByUserId(UserContext.userId());
        return ResultContext.success(res);
    }

    @RequireAuth
    @PutMapping("/me/preferences")
    public ResultContext<UserPreferencesDto> updateUserPreferences (@RequestBody UserPreferencesDto userPreferencesDto){
        UserPreferencesDto res = usersPreferencesService.updateUserPreferencesByUserId(UserContext.userId(), userPreferencesDto);
        return ResultContext.success(res);
    }



    @GetMapping("/{userId}/address")
    public ResultContext<List<AddressResponseDto>> getAddress(@PathVariable Long userId) {
        List<AddressResponseDto> addressesByUserId = userAddressesService.getAddressesByUserId(userId);
        return ResultContext.success(addressesByUserId);
    }

    @PostMapping("/{userId}/address")
    public ResultContext<AddressResponseDto> addAddress(@PathVariable Long userId, @RequestBody AddressRequestDto addressRequestDto) {
        AddressResponseDto addressResponseDto = userAddressesService.addAddress(userId, addressRequestDto);
        return ResultContext.success(addressResponseDto);
    }

    @PostMapping("/{userId}/address/{addressId}")
    public ResultContext<AddressResponseDto> updateAddress(
            @PathVariable("userId") Long userId,
            @PathVariable("addressId") Long addressId,
            @RequestBody AddressRequestDto addressRequestDto) {
        AddressResponseDto res = userAddressesService.updateAddress(userId, addressId, addressRequestDto);
        return  ResultContext.success(res);
    }

    @DeleteMapping("/{userId}/address/{addressId}")
    public ResultContext<Void> deleteAddress (@PathVariable("userId") Long userId, @PathVariable("addressId")  Long addressId) {
        userAddressesService.deleteAddress(userId, addressId);
        return ResultContext.success();
    }

    @PostMapping("/{userId}/address/default/{addressId}")
    public ResultContext<Void> setDefaultAddress(@PathVariable("userId") Long userId, @PathVariable("addressId") Long addressId ) {
        userAddressesService.setDefaultAddress(userId, addressId);
        return ResultContext.success();
    }

    @GetMapping("/common/interests")
    public ResultContext<List<InterestDto>> getCommonInterests() {
        List<InterestDto> allInterest = interestService.getAllInterest();
        return ResultContext.success(allInterest);
    }

    @GetMapping("/interests")
    public ResultContext<UserInterestsResponseDTO> getUserInterestsByUserId(@RequestParam("userId") Long userId) {
        UserInterestsResponseDTO res = usersPreferencesService.getUserInterestsByUserId(userId);
        return ResultContext.success(res);
    }
}

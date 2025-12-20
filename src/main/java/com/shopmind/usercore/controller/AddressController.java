package com.shopmind.usercore.controller;

import com.shopmind.framework.context.ResultContext;
import com.shopmind.usercore.dto.request.AddressRequestDto;
import com.shopmind.usercore.dto.response.AddressResponseDto;
import com.shopmind.usercore.service.UsersAddressesService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * Author: huangcy
 * Date: 2025-12-19
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Resource
    private UsersAddressesService userAddressesService;

    @GetMapping("/user/{userId}")
    public ResultContext<List<AddressResponseDto>> getAddress(@PathVariable Long userId) {
        List<AddressResponseDto> addressesByUserId = userAddressesService.getAddressesByUserId(userId);
        return ResultContext.success(addressesByUserId);
    }

    @PostMapping("/user/{userId}")
    public ResultContext<AddressResponseDto> addAddress(@PathVariable Long userId, @RequestBody AddressRequestDto addressRequestDto) {
        AddressResponseDto addressResponseDto = userAddressesService.addAddress(userId, addressRequestDto);
        return ResultContext.success(addressResponseDto);
    }

    @PostMapping("/{addressId}/user/{userId}")
    public ResultContext<AddressResponseDto> updateAddress(
            @PathVariable("addressId") Long addressId,
            @PathVariable("userId") Long userId,
            @RequestBody AddressRequestDto addressRequestDto) {
        AddressResponseDto res = userAddressesService.updateAddress(userId, addressId, addressRequestDto);
        return  ResultContext.success(res);
    }
    
    @DeleteMapping("/{addressId}/user/{userId}")
    public ResultContext<Void> deleteAddress (@PathVariable("addressId")  Long addressId, @PathVariable("userId") Long userId) {
        userAddressesService.deleteAddress(userId, addressId);
        return ResultContext.success();
    }

    @PostMapping("/{addressId}/default/user/{userId}")
    public ResultContext<Void> setDefaultAddress(@PathVariable("addressId") Long addressId, @PathVariable("userId") Long userId) {
        userAddressesService.setDefaultAddress(userId, addressId);
        return ResultContext.success();
    }
}

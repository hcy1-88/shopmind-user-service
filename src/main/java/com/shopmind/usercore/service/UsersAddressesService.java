package com.shopmind.usercore.service;

import com.shopmind.usercore.dto.request.AddressRequestDto;
import com.shopmind.usercore.dto.response.AddressResponseDto;
import com.shopmind.usercore.entity.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hcy18
* @description 针对表【sm_users_addresses(用户收货地址表)】的数据库操作Service
* @createDate 2025-12-12 20:21:36
*/
public interface UsersAddressesService extends IService<UserAddress> {
    /**
     * 根据用户 id 获取地址
     */
    List<AddressResponseDto> getAddressesByUserId(Long userId);

    /**
     * 新增地址
     */
    AddressResponseDto addAddress(Long userId, AddressRequestDto addressRequestDto);

    /**
     * 更新地址
     */
    AddressResponseDto updateAddress(Long userId, Long addressId, AddressRequestDto addressRequestDto);

    /**
     * 删除地址
     */
    void deleteAddress(Long userId, Long addressId);

    /**
     * 设置默认地址
     */
    void setDefaultAddress(Long userId, Long addressId);
}

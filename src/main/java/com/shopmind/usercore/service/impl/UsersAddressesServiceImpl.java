package com.shopmind.usercore.service.impl;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopmind.framework.id.IdGenerator;
import com.shopmind.usercore.client.BaiduGeocodingClient;
import com.shopmind.usercore.dto.request.AddressRequestDto;
import com.shopmind.usercore.dto.response.AddressResponseDto;
import com.shopmind.usercore.entity.UserAddress;
import com.shopmind.usercore.service.UsersAddressesService;
import com.shopmind.usercore.mapper.UsersAddressesMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author hcy18
* @description 针对表【sm_users_addresses(用户收货地址表)】的数据库操作Service实现
* @createDate 2025-12-12 20:21:36
*/
@Service
public class UsersAddressesServiceImpl extends ServiceImpl<UsersAddressesMapper, UserAddress> implements UsersAddressesService{

    @Resource
    private IdGenerator idGenerator;

    @Resource
    private UsersAddressesMapper usersAddressesMapper;

    @Resource
    private BaiduGeocodingClient baiduGeocodingClient;

    @Override
    public List<AddressResponseDto> getAddressesByUserId(Long userId) {
        LambdaQueryWrapper<UserAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAddress::getUserId, userId).isNull(UserAddress::getDeletedAt);
        List<UserAddress> userAddresses = this.getBaseMapper().selectList(wrapper);
        return userAddresses.stream().map(
                address -> {
                    AddressResponseDto addressResponseDto = new AddressResponseDto();
                    BeanUtils.copyProperties(address, addressResponseDto);
                    return addressResponseDto;
                }
        ).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressResponseDto addAddress(Long userId, AddressRequestDto addressRequestDto) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressRequestDto, userAddress);
        // 补充
        userAddress.setId(idGenerator.nextId());
        userAddress.setUserId(userId);
        userAddress.setIsDefault(BooleanUtil.isTrue(addressRequestDto.getIsDefault()));
        userAddress.setLocation(baiduGeocodingClient.createAddressLocation(addressRequestDto));

        // 注意，如果设置了默认地址，此用户其它地址为非默认
        if (userAddress.getIsDefault()) {
            usersAddressesMapper.markUnDefaultAddressByUserId(userId);
        }
        // 保存
        this.save(userAddress);
        AddressResponseDto addressResponseDto = new AddressResponseDto();
        BeanUtils.copyProperties(userAddress, addressResponseDto);
        return addressResponseDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AddressResponseDto updateAddress(Long userId, Long addressId, AddressRequestDto addressRequestDto) {
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressRequestDto, userAddress);
        userAddress.setId(addressId);
        userAddress.setUserId(userId);
        userAddress.setLocation(baiduGeocodingClient.createAddressLocation(addressRequestDto));

        // 注意，如果设置了默认地址，此用户其它地址为非默认
        if (userAddress.getIsDefault()) {
            usersAddressesMapper.markUnDefaultAddressByUserId(userId);
        }

        this.updateById(userAddress);
        AddressResponseDto addressResponseDto = new AddressResponseDto();
        BeanUtils.copyProperties(userAddress, addressResponseDto);
        return addressResponseDto;
    }

    @Override
    public void deleteAddress(Long userId, Long addressId) {
        LambdaQueryWrapper<UserAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAddress::getUserId, userId).eq(UserAddress::getId, addressId);
        this.baseMapper.delete(wrapper);
    }

    @Override
    public void setDefaultAddress(Long userId, Long addressId) {
        usersAddressesMapper.markUnDefaultAddressByUserId(userId);
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setIsDefault(true);
        this.baseMapper.updateById(userAddress);
    }
}





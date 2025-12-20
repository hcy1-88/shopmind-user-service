package com.shopmind.usercore.mapper;

import com.shopmind.usercore.entity.UserAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
* @author hcy18
* @description 针对表【sm_users_addresses(用户收货地址表)】的数据库操作Mapper
* @createDate 2025-12-12 20:21:36
* @Entity com.shopmind.entity.UsersAddresses
*/
public interface UsersAddressesMapper extends BaseMapper<UserAddress> {
    /**
     * 对用户 userId 下的地址全部标记为非默认
     * @param userId 用户 id
     */
    void markUnDefaultAddressByUserId(@Param("userId") Long userId);
}





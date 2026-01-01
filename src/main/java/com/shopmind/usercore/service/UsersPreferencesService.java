package com.shopmind.usercore.service;

import com.shopmind.usercore.dto.business.UserPreferencesDto;
import com.shopmind.usercore.dto.response.UserInterestsResponseDTO;
import com.shopmind.usercore.entity.UserPreference;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hcy18
* @description 针对表【sm_users_preferences(用户偏好设置表)】的数据库操作Service
* @createDate 2025-12-12 20:21:36
*/
public interface UsersPreferencesService extends IService<UserPreference> {
    /**
     * 获取用户偏好
     * @param userId 用户 id
     * @return 返回用户 id
     */
    UserPreferencesDto getUserPreferencesByUserId(Long userId);

    /**
     * 更新用户偏好
     * @param userId 用户 id
     * @param userPreferencesDto 偏好
     * @return 返回更新
     */
    UserPreferencesDto updateUserPreferencesByUserId(Long userId, UserPreferencesDto userPreferencesDto);

    /**
     * 创建用户偏好
     * @param userId 用户 id
     * @param userPreferencesDto 用户偏好
     * @return 用户偏好
     */
    UserPreferencesDto createUserPreferences(Long userId, UserPreferencesDto userPreferencesDto);

    /**
     * 用户的兴趣爱好获取
     * @param userId 用户 id
     * @return 兴趣
     */
    UserInterestsResponseDTO getUserInterestsByUserId(Long userId);
}

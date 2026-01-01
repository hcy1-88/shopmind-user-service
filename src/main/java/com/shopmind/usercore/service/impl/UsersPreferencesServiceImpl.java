package com.shopmind.usercore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopmind.framework.id.IdGenerator;
import com.shopmind.usercore.dto.business.InterestDto;
import com.shopmind.usercore.dto.business.UserPreferencesDto;
import com.shopmind.usercore.dto.response.UserInterestsResponseDTO;
import com.shopmind.usercore.entity.UserPreference;
import com.shopmind.usercore.service.InterestService;
import com.shopmind.usercore.service.UsersPreferencesService;
import com.shopmind.usercore.mapper.UsersPreferencesMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author hcy18
* @description 针对表【sm_users_preferences(用户偏好设置表)】的数据库操作Service实现
* @createDate 2025-12-12 20:21:36
*/
@Service
public class UsersPreferencesServiceImpl extends ServiceImpl<UsersPreferencesMapper, UserPreference> implements UsersPreferencesService{

    @Resource
    private IdGenerator idGenerator;

    @Resource
    private InterestService interestService;

    @Override
    public UserPreferencesDto getUserPreferencesByUserId(Long userId) {
        LambdaQueryWrapper<UserPreference> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserPreference::getUserId, userId);
        UserPreference up = this.getOne(wrapper);
        if (up == null) {
            return null;
        }
        UserPreferencesDto res = new UserPreferencesDto();
        BeanUtils.copyProperties(up,res);
        return res;
    }

    @Override
    public UserPreferencesDto updateUserPreferencesByUserId(Long userId, UserPreferencesDto userPreferencesDto) {
        // 先查
        LambdaQueryWrapper<UserPreference> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserPreference::getUserId, userId);
        UserPreference userPreference = this.getOne(wrapper);
        if (userPreference == null) {
            return this.createUserPreferences(userId, userPreferencesDto);
        }
        // 更新
        userPreference.setInterests(userPreferencesDto.getInterests());
        userPreference.setLanguage(userPreferencesDto.getLanguage());
        this.updateById(userPreference);
        // 转换
        UserPreferencesDto res = new UserPreferencesDto();
        BeanUtils.copyProperties(userPreference, res);
        return res;
    }

    @Override
    public UserPreferencesDto createUserPreferences(Long userId, UserPreferencesDto userPreferencesDto) {
        UserPreference userPreference = new UserPreference();
        userPreference.setId(idGenerator.nextId());
        userPreference.setUserId(userId);
        userPreference.setInterests(userPreferencesDto.getInterests());
        userPreference.setLanguage(userPreferencesDto.getLanguage());
        this.save(userPreference);
        return userPreferencesDto;
    }

    @Override
    public UserInterestsResponseDTO getUserInterestsByUserId(Long userId) {
        // 1, 先查所有兴趣
        List<InterestDto> allInterest = interestService.getAllInterest();

        // 2，查询用户
        UserPreference up = this.lambdaQuery().select(UserPreference::getInterests).eq(UserPreference::getUserId, userId).one();
        List<String> codes = up.getInterests();

        // 3，兴趣转 code - name
        Map<String, String> map = new HashMap<>();
        codes.forEach(code -> allInterest.stream()
                .filter(interest -> interest.getCode().equals(code))
                .findFirst()
                .ifPresent(interest -> map.put(code, interest.getName())));

        UserInterestsResponseDTO res = new UserInterestsResponseDTO();
        res.setUserId(userId);
        res.setInterests(map);
        return res;
    }
}





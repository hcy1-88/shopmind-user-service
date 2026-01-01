package com.shopmind.usercore.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopmind.usercore.dto.request.UserBehaviorRequest;
import com.shopmind.usercore.dto.response.UserBehaviorResponseDTO;
import com.shopmind.usercore.entity.UsersBehavior;
import com.shopmind.usercore.service.UserBehaviorsService;
import com.shopmind.usercore.mapper.UsersBehaviorsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author hcy18
* @description 针对表【sm_users_behaviors(用户行为追踪表)】的数据库操作Service实现
* @createDate 2025-12-12 20:21:36
*/
@Service
public class UserBehaviorsServiceImpl extends ServiceImpl<UsersBehaviorsMapper, UsersBehavior> implements UserBehaviorsService {

    @Override
    public List<UserBehaviorResponseDTO> findUserBehaviors(UserBehaviorRequest userBehaviorRequest) {
        LambdaQueryChainWrapper<UsersBehavior> wrapper = this.lambdaQuery().eq(UsersBehavior::getUserId, userBehaviorRequest.getUserId());
        if (StrUtil.isNotEmpty(userBehaviorRequest.getBehaviorType())) {
            wrapper.eq(UsersBehavior::getBehaviorType, userBehaviorRequest.getBehaviorType());
        }

        if (StrUtil.isNotEmpty(userBehaviorRequest.getTargetType())) {
            wrapper.eq(UsersBehavior::getTargetType, userBehaviorRequest.getTargetType());
        }

        // 查询最近 day 天的行为记录，并按创建时间降序
        wrapper.apply(String.format("created_at >= NOW() - INTERVAL '%d days'", userBehaviorRequest.getDay()))
                .orderByDesc(UsersBehavior::getCreatedAt);

        List<UsersBehavior> behaviors = wrapper.list();

        return behaviors.stream().map(this::entityToDTO).toList();
    }

    private UserBehaviorResponseDTO entityToDTO(UsersBehavior behavior) {
        UserBehaviorResponseDTO dto = new UserBehaviorResponseDTO();
        dto.setUserId(behavior.getUserId());
        dto.setBehaviorType(behavior.getBehaviorType());
        dto.setTargetType(behavior.getTargetType());
        dto.setTargetId(behavior.getTargetId());
        dto.setSearchKeyword(behavior.getSearchKeyword());
        dto.setCreatedAt(behavior.getCreatedAt());
        return dto;
    }
}





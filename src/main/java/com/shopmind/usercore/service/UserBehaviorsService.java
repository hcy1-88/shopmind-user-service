package com.shopmind.usercore.service;

import com.shopmind.usercore.dto.request.UserBehaviorRequest;
import com.shopmind.usercore.dto.response.UserBehaviorResponseDTO;
import com.shopmind.usercore.entity.UsersBehavior;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hcy18
* @description 针对表【sm_users_behaviors(用户行为追踪表)】的数据库操作Service
* @createDate 2025-12-12 20:21:36
*/
public interface UserBehaviorsService extends IService<UsersBehavior> {

    /**
     * 查询用户行为
     * @param userBehaviorRequest 查询参数
     * @return 行为
     */
    List<UserBehaviorResponseDTO> findUserBehaviors(UserBehaviorRequest userBehaviorRequest);

}

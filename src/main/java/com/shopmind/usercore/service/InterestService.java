package com.shopmind.usercore.service;

import com.shopmind.usercore.dto.business.InterestDto;
import com.shopmind.usercore.entity.Interest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author hcy18
* @description 针对表【sm_interest(用户兴趣标签（用于 LLM 推荐、个性化 prompt）)】的数据库操作Service
* @createDate 2025-12-21 21:19:07
*/
public interface InterestService extends IService<Interest> {
    List<InterestDto> getAllInterest();
}

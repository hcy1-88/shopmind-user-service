package com.shopmind.usercore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopmind.usercore.dto.business.InterestDto;
import com.shopmind.usercore.entity.Interest;
import com.shopmind.usercore.service.InterestService;
import com.shopmind.usercore.mapper.InterestMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author hcy18
* @description 针对表【sm_interest(用户兴趣标签（用于 LLM 推荐、个性化 prompt）)】的数据库操作Service实现
* @createDate 2025-12-21 21:19:07
*/
@Service
public class InterestServiceImpl extends ServiceImpl<InterestMapper, Interest> implements InterestService{
    @Override
    public List<InterestDto> getAllInterest() {
        LambdaQueryWrapper<Interest> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Interest::getEnabled, true);
        List<Interest> list = this.list(queryWrapper);
        return list.stream().map(entity -> {
            InterestDto dto = new InterestDto();
            BeanUtil.copyProperties(entity,dto);
            return dto;
        }).toList();
    }
}





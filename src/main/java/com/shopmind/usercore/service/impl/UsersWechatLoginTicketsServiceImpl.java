package com.shopmind.usercore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopmind.usercore.entity.UserWechatLoginTicket;
import com.shopmind.usercore.service.UsersWechatLoginTicketsService;
import com.shopmind.usercore.mapper.UsersWechatLoginTicketsMapper;
import org.springframework.stereotype.Service;

/**
* @author hcy18
* @description 针对表【sm_users_wechat_login_tickets(微信登录临时票据表)】的数据库操作Service实现
* @createDate 2025-12-12 20:21:36
*/
@Service
public class UsersWechatLoginTicketsServiceImpl extends ServiceImpl<UsersWechatLoginTicketsMapper, UserWechatLoginTicket>
    implements UsersWechatLoginTicketsService{

}





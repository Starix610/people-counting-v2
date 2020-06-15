package com.weiyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weiyun.mapper.ThUserMapper;
import com.weiyun.entity.ThUser;
import com.weiyun.service.ThUserService;
import org.springframework.stereotype.Service;

/**
 * (ThUser)表服务实现类
 *
 * @author Starix
 * @since 2020-06-15 12:53:20
 */
@Service
public class ThUserServiceImpl extends ServiceImpl<ThUserMapper, ThUser> implements ThUserService {

}
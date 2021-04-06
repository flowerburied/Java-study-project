package com.atguigu.mpdemo1010.mapper;

import com.atguigu.mpdemo1010.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository //加申明暴露出去

public interface UserMapper extends BaseMapper<User> {
}

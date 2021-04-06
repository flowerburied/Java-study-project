package com.atguigu.mpdemo1010;

import com.atguigu.mpdemo1010.entity.User;
import com.atguigu.mpdemo1010.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class Mpdemo1010ApplicationTests {


    @Autowired //注入接口
    private UserMapper userMapper;

    //查询user表中的所有数据
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("孙悟空");
        user.setAge(30);
        user.setEmail("1222@qq.com");

//        user.setCreateTime(new Date()); 手动设置时间
//        user.setUpdateTime(new Date());

        int insert = userMapper.insert(user);
        System.out.println("insert" + insert);
    }

    @Test
//    修改操作
    public void updateUser() {
        User user = new User();
        user.setId(1379349865282326530L);
        user.setAge(110);
        int row = userMapper.updateById(user);
        System.out.println("row" + row);

    }

    @Test
//    测试乐观锁
    public void testOptimisticLocker() {

//        根据id查询数据
        User user = userMapper.selectById(1379353182859321346L);

//进行修改
        user.setAge(200);
        userMapper.updateById(user);
    }
}

package com.atguigu.mpdemo1010;

import com.atguigu.mpdemo1010.entity.User;
import com.atguigu.mpdemo1010.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.ls.LSOutput;

import java.sql.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class Mpdemo1010ApplicationTests {


    @Autowired //注入接口
    private UserMapper userMapper;

    //查询user表中的所有数据
    @Test
    void findAll() {
//        SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("钟离");
        user.setAge(6);
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

        //WARNING: An illegal reflective access operation has occurred出现这个版本要1.8
        //根据id查询数据
        User user = userMapper.selectById(1379353182859321346L);

        //进行修改
        user.setAge(210);
        userMapper.updateById(user);
    }

    @Test
    //多个id批量查询
    public void testSelectDemo1() {

        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println("多个id的批量查询" + users);
    }

    @Test
//    条件查询
    public void testSelectByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jone");
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);

        users.forEach(System.out::println);


    }

    //    分页查询
    @Test
    public void testPage() {
//        创建一个page对象
//        传入2个参数: 当前页 和 每页显示记录数
        Page<User> page = new Page<>(1, 3);

//        调用mp的分页方法

        userMapper.selectPage(page, null);

        System.out.println(page.getCurrent());  //获取当前页
        System.out.println(page.getRecords());  //每页数据list集合
        System.out.println(page.getSize());  //每页显示记录数
        System.out.println(page.getTotal()); //总记录数
        System.out.println(page.getPages());  //总页数

        System.out.println(page.hasNext());  //是否有下一页 true false
        System.out.println(page.hasPrevious());  //是否有上一页

    }

    @Test
//    物理删除
    public void testDeleteById() {
        int result = userMapper.deleteById(1379353182859321346L);

        System.out.println(result);
    }

    @Test
//    批量删除

    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(result);
    }


    @Test
    public void testSelectQuery() {

        //创建QueryWrapper对象

        QueryWrapper<User> wrapper = new QueryWrapper<>();

        //通过QueryWrapper方法查询
        //ge gt le lt  大于 大等 小于 下等
        //SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND age >= ?
//        wrapper.ge("age",30);
//        List<User> users = userMapper.selectList(wrapper);
//        System.out.println(users);
        //eq ne 等于 不等于
//        SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND name = ?
//        wrapper.eq("name","Sandy");
//        List<User> users = userMapper.selectList(wrapper);
//        System.out.println(users);
        //between ... 范围
//        SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND age BETWEEN ? AND ?
//                wrapper.between("age",20,30);

        //like 模糊查询
//        SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE
//                deleted=0
//        AND name LIKE '%i%'
//        wrapper.like("name","i");
        //orderBy ... 排序
//        wrapper.orderByDesc("id"); //降序
//        SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 ORDER BY id ASC
//        wrapper.orderByAsc("id");  //升序
        //last 拼接
//        wrapper.last("limit 1");
        //指定查询的列
        wrapper.select("name", "id");

        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }


}

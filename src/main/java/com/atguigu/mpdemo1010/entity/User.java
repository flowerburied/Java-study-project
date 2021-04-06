package com.atguigu.mpdemo1010.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data   //自动生成get set 有参无参
public class User {
    @TableId(type = IdType.ID_WORKER)
    //AUTU   自动增长
    //INPUT 自己输入设置id值
    //NONE 自己输入设置id值
    //UUID 随机生成唯一值
    //ID_WORKER  mp自带策略，生成19位值，适用于数字类型    snowflake雪花算法
    //ID_WORKER_STR   mp自带策略，生成19位值，适用于字符串类型

    private Long id;
    private String name;
    private Integer age;
    private String email;

    //自动填充
    @TableField(fill = FieldFill.INSERT) //添加
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE) //添加修改
    private Date updateTime;

    @Version //乐观锁
    @TableField(fill = FieldFill.INSERT) //添加
    private Integer version; //版本号

}

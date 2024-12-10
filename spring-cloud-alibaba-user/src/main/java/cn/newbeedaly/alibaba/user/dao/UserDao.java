package cn.newbeedaly.alibaba.user.dao;

import cn.newbeedaly.alibaba.user.dao.mapper.UserMapper;
import cn.newbeedaly.alibaba.user.dao.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UserDao extends ServiceImpl<UserMapper, User> implements IService<User> {

}

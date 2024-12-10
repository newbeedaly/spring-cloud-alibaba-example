package cn.newbeedaly.alibaba.user.service.impl;

import cn.newbeedaly.alibaba.user.dao.UserDao;
import cn.newbeedaly.alibaba.user.dao.po.User;
import cn.newbeedaly.alibaba.user.service.IUserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Primary
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(Long id) {
        return userDao.getById(id);
    }

}

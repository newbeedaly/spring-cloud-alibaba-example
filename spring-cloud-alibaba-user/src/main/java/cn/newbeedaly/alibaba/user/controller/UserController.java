package cn.newbeedaly.alibaba.user.controller;

import cn.newbeedaly.alibaba.user.dao.po.User;
import cn.newbeedaly.alibaba.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

}

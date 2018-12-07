package com.immoc.miaosha.controller;

import com.immoc.miaosha.domain.MiaoshaUser;
import com.immoc.miaosha.redis.RedisService;
import com.immoc.miaosha.result.Result;
import com.immoc.miaosha.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by buer on 2018/12/7.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/info")
    @ResponseBody
    public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {

//        System.out.println(i +1);
        return Result.success(user);
    }
}

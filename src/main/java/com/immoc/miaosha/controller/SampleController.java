package com.immoc.miaosha.controller;

import com.immoc.miaosha.domain.User;
import com.immoc.miaosha.redis.RedisService;
import com.immoc.miaosha.redis.UserKey;
import com.immoc.miaosha.result.CodeMsg;
import com.immoc.miaosha.result.Result;
import com.immoc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by buer on 2018/12/5.
 */
@Controller
@RequestMapping("/demo")
public class SampleController {

    //自动注入
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    //1.rest api json输出
    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("heloo, immoc");
    }

    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
        User user = userService.getById(1);
        return Result.success(user);
    }

    //事物测试
    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
        userService.tx();
        return Result.success(true);
    }
    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {
        User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user  = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById, ""+1, user);//UserKey:id1
        return Result.success(true);
    }

//    2.页面
    @RequestMapping("/thymleaf")
    public String thymleaf(Model model){
        model.addAttribute("name", "buer");
        return "hello";
    }
}

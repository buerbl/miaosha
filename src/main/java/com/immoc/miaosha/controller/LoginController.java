package com.immoc.miaosha.controller;

import com.immoc.miaosha.result.CodeMsg;
import com.immoc.miaosha.result.Result;
import com.immoc.miaosha.service.MiaoshaUserService;
import com.immoc.miaosha.util.ValidatorUtil;
import com.immoc.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by buer on 2018/12/5.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    MiaoshaUserService userService;
    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo   loginVo){
        log.info(loginVo.toString());
//        //参数校验
//        String passInput = loginVo.getPassword();
//        String moboile = loginVo.getMobile();
//        //密码
//        if (StringUtils.isEmpty(passInput)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        //号码
//        if (StringUtils.isEmpty(moboile)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        //格式
//        if (!ValidatorUtil.isMobile(moboile)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }

        //使用springboot集成的jsp303参数（一种java规范）


        //登录
       userService.login(response, loginVo);
       return Result.success(true);

//        return null;
    }


}

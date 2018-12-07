package com.immoc.miaosha.service;

import com.immoc.miaosha.dao.MiaoshaUserDao;
import com.immoc.miaosha.domain.MiaoshaUser;
import com.immoc.miaosha.exception.GlobalException;
import com.immoc.miaosha.redis.MiaoshaUserKey;
import com.immoc.miaosha.redis.RedisService;
import com.immoc.miaosha.result.CodeMsg;
import com.immoc.miaosha.util.MD5Util;
import com.immoc.miaosha.util.UUIDUtil;
import com.immoc.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by buer on 2018/12/5.
 */
@Service
public class MiaoshaUserService {
    public static final String COOKI_NAME_TOKEN = "token";
    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;
    public MiaoshaUser getById(long id) {
        //取缓存
        MiaoshaUser user = redisService.get(MiaoshaUserKey.getById, ""+id, MiaoshaUser.class);
        if(user != null) {
            return user;
        }
        //取数据库
        user = miaoshaUserDao.getById(id);
        if(user != null) {
            redisService.set(MiaoshaUserKey.getById, ""+id, user);
        }
        return user;
    }

    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }

        MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo){
        if (loginVo == null){
            //把异常抛出去，被全局异常拦截，然后控制台显示，方便查看，编程技巧
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        MiaoshaUser user = getById(Long.parseLong(mobile));

        if (user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        System.out.println(dbPass);

        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        System.out.println(calcPass);
        if(!calcPass.equals(dbPass)) {
            System.out.println("Asssssssss");
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);

        }
        //生成cookie
        String token	 = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;

    }

    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisService.set(MiaoshaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}

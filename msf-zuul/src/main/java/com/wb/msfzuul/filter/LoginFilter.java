package com.wb.msfzuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.wb.msfcore.util.JSONUtil;
import com.wb.msfcore.vo.ResponseResult;
import com.wb.msfzuul.jwt.JWTTokenUtil;
import com.wb.msfzuul.jwt.JWTUserDetails;
import com.wb.msfzuul.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClaseName wangbin
 * @Auther wangbin
 * @Date 2019-04-29
 * @Version
 **/
public class LoginFilter  extends ZuulFilter {
    /**
     * 指定该Filter的类型
     *
     */
    @Value("${jwt.secret}")
    private String secret;


    @Value("${jwt.expiration}")
    private String expiration;


    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String filterType() {
        return "pre";
    }

//    /**
//     * 指定该Filter执行的顺序（Filter从小到大执行）
//     * DEBUG_FILTER_ORDER = 1;
//     * FORM_BODY_WRAPPER_FILTER_ORDER = -1;
//     * PRE_DECORATION_FILTER_ORDER = 5;
//     * RIBBON_ROUTING_FILTER_ORDER = 10;
//     * SEND_ERROR_FILTER_ORDER = 0;
//     * SEND_FORWARD_FILTER_ORDER = 500;
//     * SEND_RESPONSE_FILTER_ORDER = 1000;
//     * SIMPLE_HOST_ROUTING_FILTER_ORDER = 100;
//     * SERVLET_30_WRAPPER_FILTER_ORDER = -2;
//     * SERVLET_DETECTION_FILTER_ORDER = -3;
//     */
    @Override
    public int filterOrder() {
        return 5;
    }

    /**
     * 指定需要执行该Filter的规则
     * 返回true则执行run()
     * 返回false则不执行run()
     */
    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestUrl = request.getRequestURL().toString();

        // 请求URL内不包含login或join则需要经过该过滤器，即执行run()
        return !requestUrl.contains("login") && !requestUrl.contains("getVerificationCode");
    }

    /**
     * 该Filter具体的执行活动
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletRequest request = ctx.getRequest();
        String authToken = request.getHeader("Authorization");
        HttpServletResponse Response =  ctx.getResponse();
//        String username = request.getParameter("username");
        // 若session中不包含userId，则这次请求视为未登录请求，不给予路由，而提示“请登录”
        System.out.println( JWTTokenUtil.validateToken( secret, authToken ));
        if (authToken == null || !JWTTokenUtil.validateToken( secret, authToken )) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(403);
            ctx.getResponse().setCharacterEncoding("UTF-8");
            ctx.setResponseBody("您没有访问权限");
        }else{
            if(JWTTokenUtil.isRefreshToken(secret, expiration,authToken )){
                String refreshToken=JWTTokenUtil.refreshToken(secret,expiration, authToken );
                System.out.println(refreshToken);
                Response.setHeader("refreshToken",refreshToken);
            }
        }

        return null;
    }
}
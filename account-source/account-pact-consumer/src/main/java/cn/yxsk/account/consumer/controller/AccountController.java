package cn.yxsk.account.consumer.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yxsk.account.consumer.component.AccountComponent;
import cn.yxsk.account.consumer.vo.AccountModel;
import cn.yxsk.account.consumer.vo.AccountResult;

@RestController
@RequestMapping("profile")
public class AccountController {
    @Autowired
    private AccountComponent accountComponent;

    @PostMapping("login")
    public AccountResult<String> login(String email, String password) {
        AccountResult<String> result = new AccountResult<>();
        result.setData("FAILED");
        if (!StringUtils.hasText(email)) {
            result.setCode(1);
            result.setMessage("邮箱信息不能为空");
            return result;
        }

        if (!StringUtils.hasText(password)) {
            result.setCode(2);
            result.setMessage("密码信息不能为空");
            return result;
        }

        Optional<AccountModel> requstResult = this.accountComponent.fetchAccount(email);
        if (requstResult.isPresent() && password.equals(requstResult.get().getPassword())) {
            result.setCode(0);
            result.setData("OK");
        } else {
            result.setCode(3);
            result.setMessage("用户名或者密码错误");
        }
        return result;
    }

    @PostMapping("register")
    public AccountResult<String> register(String email, String password, HttpServletRequest request) {
        AccountResult<String> result = new AccountResult<>();
        result.setData("FAILED");
        if (!StringUtils.hasText(email)) {
            result.setCode(1);
            result.setMessage("邮箱信息不能为空");
            return result;
        }
        
        if (!StringUtils.hasText(password)) {
            result.setCode(2);
            result.setMessage("密码信息不能为空");
            return result;
        }
        
        Optional<AccountModel> requstResult = this.accountComponent.fetchAccount(email);
        if (requstResult.isPresent()) {
            result.setCode(4);
            result.setMessage("该邮箱已经注册，不能多次注册");
            return result;
        }
        
        String registerIp = this.getLocalIp(request);
        this.accountComponent.saveAccount(email, password, registerIp);
        result.setCode(0);
        result.setData("OK");
        return result;
    }

    private String getLocalIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.hasText(ip) && !"UNKNOWN".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.hasText(ip) && !"UNKNOWN".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}

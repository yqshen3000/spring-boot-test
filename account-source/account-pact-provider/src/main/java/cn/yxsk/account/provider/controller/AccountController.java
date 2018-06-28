package cn.yxsk.account.provider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.yxsk.account.provider.model.AccountModel;
import cn.yxsk.account.provider.service.AccountService;
import cn.yxsk.account.provider.vo.AccountResult;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("getAccount")
    public AccountResult fetchAccount(String email) {
        AccountResult result = new AccountResult();
        result.setCode(0);
        this.accountService.fetchAccountModelByEmail(email).ifPresent(account -> result.setData(account));
        return result;
    }

    @PostMapping("saveAccount")
    public AccountResult saveAccount(AccountModel model) {
        model.setRegisterTime(System.currentTimeMillis() / 1000);
        this.accountService.saveAccount(model);
        AccountResult result = new AccountResult();
        result.setCode(0);
        result.setData(model.getId());
        return result;
    }
}

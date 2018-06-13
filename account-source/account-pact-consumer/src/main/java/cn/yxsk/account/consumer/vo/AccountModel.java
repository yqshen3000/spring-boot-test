package cn.yxsk.account.consumer.vo;

import lombok.Data;

@Data
public class AccountModel {
    private Long id;
    private String email;
    private String password;
    private String registerIp;
    private Long registerTime;
}

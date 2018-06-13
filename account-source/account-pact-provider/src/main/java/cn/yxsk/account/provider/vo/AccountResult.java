package cn.yxsk.account.provider.vo;

import lombok.Data;

@Data
public class AccountResult {
    private int code = -1;
    private String message;
    private Object data;
}

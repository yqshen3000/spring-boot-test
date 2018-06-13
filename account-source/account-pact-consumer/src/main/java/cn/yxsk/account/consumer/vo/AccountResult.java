package cn.yxsk.account.consumer.vo;

import lombok.Data;

@Data
public class AccountResult<T> {
    private int code = -1;
    private String message;
    private T data;
}

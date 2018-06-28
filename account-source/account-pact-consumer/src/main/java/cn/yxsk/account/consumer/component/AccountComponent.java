package cn.yxsk.account.consumer.component;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import cn.yxsk.account.consumer.vo.AccountModel;
import cn.yxsk.account.consumer.vo.AccountResult;
import lombok.NonNull;

@Component
public class AccountComponent {
    private static final String ACCOUNT_PROVIDER_HOST = "http://127.0.0.1:8080/";
    @Autowired
    private RestTemplate restTemplate;

    public Optional<AccountModel> fetchAccount(@NonNull String email) {
        String url = ACCOUNT_PROVIDER_HOST + "account/getAccount?email={0}";
        FetchAccount result = this.restTemplate.getForEntity(url, FetchAccount.class, email).getBody();
        return Optional.ofNullable(result.getData());
    }

    public boolean saveAccount(String email, String password, String registerIp) {
        String url = ACCOUNT_PROVIDER_HOST + "account/saveAccount";
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("email", email);
        requestEntity.add("password", password);
        requestEntity.add("registerIp", registerIp);
        return this.restTemplate.postForEntity(url, requestEntity, SaveAccount.class).getBody().getData() > 0;
    }

    // 偷懒，定义一下
    private static class FetchAccount extends AccountResult<AccountModel> {
    }

    private static class SaveAccount extends AccountResult<Long> {
    }
}

package cn.yxsk.account.provider.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import cn.yxsk.account.provider.model.AccountModel;

@Service
public class AccountService {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void saveAccount(AccountModel model) {
        StringBuilder sql = new StringBuilder(64);
        sql.append("INSERT INTO `").append(this.fetchTableName(model.getEmail())).append(
                "` (`email`, `password`, `registerIp`, `registerTime`) VALUES (:email, :password, :registerIp, :registerTime);");
        Map<String, Object> parms = new HashMap<>(4);
        parms.put("email", model.getEmail());
        parms.put("password", model.getPassword());
        parms.put("registerIp", model.getRegisterIp());
        parms.put("registerTime", model.getRegisterTime());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.namedParameterJdbcTemplate.update(sql.toString(), new MapSqlParameterSource(parms), keyHolder);
        model.setId(keyHolder.getKey().longValue());
    }

    private String fetchTableName(String email) {
        int index = (email.hashCode() & Integer.MAX_VALUE) % 4;
        return "Account" + index;
    }

    public Optional<AccountModel> fetchAccountModelByEmail(String email) {
        StringBuilder sql = new StringBuilder(64);
        sql.append("SELECT * FROM `").append(this.fetchTableName(email)).append("` WHERE email = :email");
        Map<String, Object> parms = new HashMap<>(1);
        parms.put("email", email);
        List<AccountModel> result = this.namedParameterJdbcTemplate.query(sql.toString(), parms,
                new BeanPropertyRowMapper<>(AccountModel.class));
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }
}

package io.github.nnkwrik.common.client;

import io.github.nnkwrik.common.dto.Response;
import io.github.nnkwrik.common.dto.SimpleUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用user-service的api
 * @author nnkwrik
 * @date 18/11/23 18:06
 */
@FeignClient(name = "user-service")
@RequestMapping("/user-service")
public interface UserClient {

    default Response<SimpleUser> getSimpleUser(String userId) {
        Map<String, String> json = new HashMap<>();
        json.put("openId", userId);
        return getSimpleUser(json);
    }

    /**
     * 获取用户openId的相关信息
     * @param openId
     * @return
     */
    //json 只包含 openId = "..." 这一项。但由于是String，直接传输会变成text/plain。索性包装成map
    @PostMapping("/simpleUser")
    Response<SimpleUser> getSimpleUser(@RequestBody Map<String, String> openId);

    /**
     * 获取用户openIdList的相关信息
     * @param openIdList
     * @return
     */
    @PostMapping("/simpleUserList")
    Response<HashMap<String, SimpleUser>> getSimpleUserList(@RequestBody List<String> openIdList);
}
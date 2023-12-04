package com.woke.working.api.user.feign;

import com.woke.working.api.constant.FeignConstant;
import com.woke.working.api.user.UserAuthApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = FeignConstant.PLATFORM_USER_AGENCIES)
public interface UserAuthFeign extends UserAuthApi {
}

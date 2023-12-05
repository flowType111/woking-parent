package com.woke.working.api.user.feign;

import com.woke.working.api.constant.FeignConstant;
import com.woke.working.api.user.UserAuthApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = FeignConstant.WORKING_USER)
public interface UserAuthFeign extends UserAuthApi {
}

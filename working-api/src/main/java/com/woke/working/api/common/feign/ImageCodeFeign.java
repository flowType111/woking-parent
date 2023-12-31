package com.woke.working.api.common.feign;

import com.woke.working.api.common.ImageCodeApi;
import com.woke.working.api.constant.FeignConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = FeignConstant.WORKING_COMMON)
public interface ImageCodeFeign extends ImageCodeApi {
}

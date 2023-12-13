package com.woke.working.api.common;

import com.woke.working.common.dto.common.OpenInterFaceDTO;
import com.woke.working.common.dto.common.OpenInterFacePage;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/open/api")
public interface OpenInterFaceApi {

    @PostMapping("/addOpenApi")
    ResponseVo addOpenApi(OpenInterFaceDTO openInterFaceDTO);

    @GetMapping("/deleteOpenApi")
    ResponseVo deleteOpenApi(String id);

    @PostMapping("/updateOpenApi")
    ResponseVo updateOpenApi(OpenInterFaceDTO openInterFaceDTO);

    @PostMapping("/selectOpenApi")
    ResponseVo selectOpenApi(OpenInterFacePage openInterFacePage);
}

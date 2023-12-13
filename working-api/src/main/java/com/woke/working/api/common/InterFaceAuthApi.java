package com.woke.working.api.common;

import com.woke.working.common.dto.common.InterFaceAuthDTO;
import com.woke.working.common.dto.common.InterFaceAuthPage;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接口白名单处理
 */
@RequestMapping("/interface/auth")
public interface InterFaceAuthApi {

    @PostMapping("/addInterFaceAuth")
    ResponseVo addInterFaceAuth(InterFaceAuthDTO interFaceAuthDTO);


    @GetMapping("/operateInterFaceAuth")
    ResponseVo operateInterFaceAuth(String id, String type);


    @PostMapping("/selectInterFaceAuth")
    ResponseVo selectInterFaceAuth(InterFaceAuthPage interFaceAuthPage);

    @PostMapping("/updateInterFaceAuth")
    ResponseVo updateInterFaceAuth(InterFaceAuthDTO interFaceAuthDTO);
}

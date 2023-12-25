package com.woke.working.common.service.controller;

import com.woke.working.api.common.InterFaceAuthApi;
import com.woke.working.common.dto.common.AccessTokenAuthDTO;
import com.woke.working.common.dto.common.InterFaceAuthDTO;
import com.woke.working.common.dto.common.InterFaceAuthPage;
import com.woke.working.common.service.service.InterFaceAuthService;
import com.woke.working.common.valid.ValidGroup;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterFaceAuthController implements InterFaceAuthApi {

    @Autowired
    private InterFaceAuthService interFaceAuthService;

    @Override
    public ResponseVo addInterFaceAuth(@RequestBody @Validated({ValidGroup.Insert.class}) InterFaceAuthDTO interFaceAuthDTO) {
        return interFaceAuthService.addInterFaceAuth(interFaceAuthDTO);
    }

    @Override
    public ResponseVo operateInterFaceAuth(@RequestParam("id") String id,@RequestParam("type") String type) {
        return interFaceAuthService.operateInterFaceAuth(id,type);
    }

    @Override
    public ResponseVo selectInterFaceAuth(@RequestBody InterFaceAuthPage interFaceAuthPage) {
        return interFaceAuthService.selectInterFaceAuth(interFaceAuthPage);
    }

    @Override
    public ResponseVo updateInterFaceAuth(@RequestBody @Validated({ValidGroup.Update.class}) InterFaceAuthDTO interFaceAuthDTO) {
        return interFaceAuthService.updateInterFaceAuth(interFaceAuthDTO);
    }

    @Override
    public ResponseVo selectInterFaceDetails(@RequestParam("id") String id) {
        return interFaceAuthService.selectInterFaceDetails(id);
    }

    @Override
    public ResponseVo interFaceAuth(@RequestBody AccessTokenAuthDTO accessTokenAuthDTO) {
        return interFaceAuthService.interFaceAuth(accessTokenAuthDTO);
    }
}

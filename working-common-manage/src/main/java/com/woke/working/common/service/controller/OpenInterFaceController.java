package com.woke.working.common.service.controller;

import com.woke.working.api.common.OpenInterFaceApi;
import com.woke.working.common.dto.common.OpenInterFaceDTO;
import com.woke.working.common.dto.common.OpenInterFacePage;
import com.woke.working.common.service.service.OpenInterFaceService;
import com.woke.working.common.valid.ValidGroup;
import com.woke.working.common.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenInterFaceController implements OpenInterFaceApi {

    @Autowired
    protected OpenInterFaceService openInterFaceService;

    @Override
    public ResponseVo addOpenApi(@RequestBody @Validated({ValidGroup.Insert.class}) OpenInterFaceDTO openInterFaceDTO) {
        return openInterFaceService.addOpenApi(openInterFaceDTO);
    }

    @Override
    public ResponseVo deleteOpenApi(@RequestParam("id") String id) {
        return openInterFaceService.deleteOpenApi(id);
    }

    @Override
    public ResponseVo updateOpenApi(@RequestBody @Validated({ValidGroup.Update.class}) OpenInterFaceDTO openInterFaceDTO) {
        return openInterFaceService.updateOpenApi(openInterFaceDTO);
    }

    @Override
    public ResponseVo selectOpenApi(OpenInterFacePage openInterFacePage) {
        return openInterFaceService.selectOpenApi(openInterFacePage);
    }

    @Override
    public ResponseVo selectAll() {
        return openInterFaceService.selectAll();
    }
}

package com.woke.working.common.service;

import com.woke.working.common.dto.common.OpenInterFaceDTO;
import com.woke.working.common.dto.common.OpenInterFacePage;
import com.woke.working.common.vo.ResponseVo;

public interface OpenInterFaceService {

    ResponseVo addOpenApi(OpenInterFaceDTO openInterFaceDTO);

    ResponseVo deleteOpenApi(String id);

    ResponseVo updateOpenApi(OpenInterFaceDTO openInterFaceDTO);

    ResponseVo selectOpenApi(OpenInterFacePage openInterFacePage);
}

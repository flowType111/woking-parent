package com.woke.working.web.service.factory;

import com.woke.working.common.dto.web.BlockChannelDTO;
import com.woke.working.web.config.PayChannelAbstractExecutor;
import com.woke.working.web.util.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * 卡密渠道服务类
 *
 */
@Service("blockChannelService")
public class BlockChannelService extends PayChannelAbstractExecutor {

    @Override
    public void execute() throws ParseException {
        BlockChannelDTO blockChannelDTO = (BlockChannelDTO) ThreadLocalUtil.getLocalVar();

    }
}

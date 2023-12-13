package com.woke.working.common.dto.user;

import com.woke.working.common.valid.ValidGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class SystemUserDTO {

    @NotNull(message = "id不能为空",groups = {ValidGroup.Update.class})
    private String id;

    @NotNull(message = "用户账号不能为空",groups = {ValidGroup.Insert.class})
    private String accountNo;

    @NotNull(message = "用户密码不能为空",groups = {ValidGroup.Insert.class})
    private String password;

    private String nickName;

    private List<String> roleList;
    
    private String selectedroles;
}

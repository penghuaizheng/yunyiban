package com.huashan.yebserver.domain.vo;

import com.huashan.yebserver.domain.MenuRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuRoleVo extends MenuRole {
    private Integer[] mids;

    public MenuRoleVo(MenuRole menuRole) {
        super(menuRole);
    }
}

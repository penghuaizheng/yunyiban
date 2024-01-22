package com.huashan.yebserver.domain.vo;

import com.huashan.yebserver.domain.Menu;
import com.huashan.yebserver.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuVo extends Menu {
    private List<MenuVo> children;

    public MenuVo(Menu menu) {
        super(menu);
    }
}

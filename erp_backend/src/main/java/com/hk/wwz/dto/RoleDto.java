package com.hk.wwz.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javafx.application.Application;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class RoleDto extends PageDto {

    private String companyId;

}

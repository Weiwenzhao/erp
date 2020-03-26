package com.hk.wwz.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class ConfigReqDto extends PageDto {

    private String companyId;
}

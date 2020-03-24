package com.hk.wwz.service;

import com.hk.wwz.pojo.ResultBean;

public interface KeyService {

    ResultBean addKeyToStr(String str, String name);

    ResultBean removeKeyToStr(String str);
}

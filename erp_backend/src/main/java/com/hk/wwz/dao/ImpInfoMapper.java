package com.hk.wwz.dao;

import com.hk.wwz.pojo.ImpInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpInfoMapper {
    ImpInfo getImpInfoByName(String name);

    int insertImpInfo(ImpInfo impInfo);

    int updateImpInfo(ImpInfo impInfo);

    int deleteImpInfo(String name);

}

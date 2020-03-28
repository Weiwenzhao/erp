package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hk.wwz.dao.RoleMapper;
import com.hk.wwz.dto.RoleDto;
import com.hk.wwz.pojo.ResponObj;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.Role;
import com.hk.wwz.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Resource
    RoleMapper roleDao;

    @Override
    public ResponObj<Integer> addRole(Role role) {
        ResponObj<Integer> resp = new ResponObj<>(-1,"add role failed!");
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",role.getName());
        queryWrapper.eq("company_id",role.getCompanyId());
        try{
            Role roleExists = roleDao.selectOne(queryWrapper);
            if(null != roleExists){
                resp.setCode(-1);
                resp.setMsg("当前角色已存在");
                return resp;
            }
            role.setCreateTime(new Timestamp(System.currentTimeMillis()));
           int tmp =  roleDao.insert(role);
            resp.setCode(0);
            resp.setMsg("添加成功");
            resp.setData(tmp);
            return resp;
        }catch (Exception e){
            log.error("添加角色信息异常：" + e.getMessage());
            resp.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Role> findAllByCompany(RoleDto roleDto) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id",roleDto.getCompanyId());
        Page<Role> page = new Page<>(roleDto.getPageNo(),roleDto.getPageSize());
        try{
            IPage<Role> ipage = roleDao.selectPage(page,queryWrapper);
            if(null != ipage.getRecords() && ipage.getRecords().size() > 0){
                return ipage.getRecords();
            }
        }catch (Exception e){
            log.error("查询角色信息，异常：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultBean deleteById(String id) {

        return null;
    }
}

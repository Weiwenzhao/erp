package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.response.OapiDepartmentGetResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.dingtalk.api.response.OapiUserListbypageResponse;
import com.hk.wwz.dao.DepartMapper;
import com.hk.wwz.dao.UserMapper;
import com.hk.wwz.pojo.Depart;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.pojo.User;
import com.hk.wwz.service.DingDingService;
import com.hk.wwz.service.SyncDataService;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class SyncDataServiceImpl implements SyncDataService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    DingDingService dingDingService;
    @Autowired
    DepartMapper departDao;
    @Autowired
    UserMapper userDao;

    @Override
    public ResultBean syncDepartData() throws ApiException {
        OapiGettokenResponse oapiGettokenResponse = dingDingService.getAccessToken();
        logger.info("获取到的鉴权信息是：" + JSON.toJSONString(oapiGettokenResponse));
        return new ResultBean(200, syncDepart(oapiGettokenResponse));

    }


    @Override
    public ResultBean syncUser() throws ApiException {
        OapiGettokenResponse oapiGettokenResponse = dingDingService.getAccessToken();
        logger.info("获取到的鉴权信息是：" + JSON.toJSONString(oapiGettokenResponse));
        return new ResultBean(200, syncUser(oapiGettokenResponse));
    }

    @Override
    public ResultBean syncInfo() throws ApiException {
        OapiGettokenResponse oapiGettokenResponse = dingDingService.getAccessToken();
        logger.info("获取到的鉴权信息是：" + JSON.toJSONString(oapiGettokenResponse));
        Map<String, Integer> map = new HashMap<>();
        map.put("depart", syncDepart(oapiGettokenResponse));
        map.put("user", syncUser(oapiGettokenResponse));
        return new ResultBean(200, JSON.toJSONString(map));
    }


    /*************************************同步部门************************************/
    private int syncDepart(OapiGettokenResponse oapiGettokenResponse) throws ApiException {
        int result = -1;
        if (oapiGettokenResponse.isSuccess()) {
            OapiDepartmentListResponse oapiDepartmentListResponse = dingDingService.getAllDepartList(oapiGettokenResponse.getAccessToken());
            List<OapiDepartmentListResponse.Department> departmentList = oapiDepartmentListResponse.getDepartment();
            logger.info("获取全部部门信息是：" + JSON.toJSONString(departmentList));
            List<Depart> departList = new ArrayList<>();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            for (OapiDepartmentListResponse.Department department : departmentList) {
                OapiDepartmentGetResponse oapiDepartmentGetResponse = dingDingService.getDepartInfoById(department.getId(), oapiGettokenResponse.getAccessToken());
                departList.add(new Depart(UUID.randomUUID().toString().substring(0, 31), department.getId() + "", department.getParentid() == null ? "root" : department.getParentid() + "", department.getName(), oapiDepartmentGetResponse, timestamp));
            }
            logger.info("获取到最新的部门信息是：" + JSON.toJSONString(departList));
            result = saveDepartInfo(departList);
        }
        return result;
    }

    private int saveDepartInfo(List<Depart> departList) {
        if (departList.size() > 0) {
            List<String> deleteDepartList = new ArrayList<>();
            List<Depart> addDepartList = new ArrayList<>();
            List<Depart> oldDepartList = departDao.getAllDepart();
            logger.info("获取到的所有旧部门信息是：" + JSON.toJSONString(oldDepartList));
            oldDepartList.forEach(depart -> {
                if (!departList.contains(depart)) {
                    deleteDepartList.add(depart.getIndexCode());
                }
            });
            logger.info("需要删除的部门信息是：" + JSON.toJSONString(deleteDepartList));
            if (deleteDepartList.size() > 0) {
                departDao.deleteDepart(deleteDepartList);
            }
            departList.forEach(depart -> {
                if (!oldDepartList.contains(depart)) {
                    addDepartList.add(depart);
                }
            });
            logger.info("需要新增的部门信息是：" + JSON.toJSONString(addDepartList));
            if (addDepartList.size() > 0) {
                departDao.addDepartList(addDepartList);
            }
            return 1;
        }
        return -1;
    }

    /*************************************同步用户************************************/
    private int syncUser(OapiGettokenResponse oapiGettokenResponse) throws ApiException {
        int result = -1;

        if (oapiGettokenResponse.isSuccess()) {
            List<Depart> departList = departDao.getAllDepart();
            List<OapiUserListbypageResponse.Userlist> userList = new ArrayList<>();
            if (departList != null) {
                for (Depart depart : departList) {
                    userList.addAll(getAllUser(Long.parseLong(depart.getIndexCode()), oapiGettokenResponse));
                }
            }
            //根据人员ID去重
            userList = userList.stream().collect(
                    collectingAndThen(
                            toCollection(() -> new TreeSet<>(comparing(OapiUserListbypageResponse.Userlist::getUserid))), ArrayList::new)
            );
            logger.info("获取全部员工信息是：" + JSON.toJSONString(userList));
            if (userList.size() > 0) {
                List<User> newUserList = new ArrayList<>();
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                userList.forEach(user -> {
                    newUserList.add(new User(
                            UUID.randomUUID().toString().substring(0, 31), user.getUserid(), user.getUnionid(), user.getName(), user.getMobile(),
                            user.getIsAdmin() ? 1 : 0, user.getIsBoss() ? 1 : 0, user.getDepartment(), user.getAvatar(), timestamp
                    ));
                });
                logger.info("获取到最新的员工信息是" + JSON.toJSONString(newUserList));
                result = saveUser(newUserList);
            }
        }
        return result;
    }

    private List<OapiUserListbypageResponse.Userlist> getAllUser(long departId, OapiGettokenResponse oapiGettokenResponse) throws ApiException {
        List<OapiUserListbypageResponse.Userlist> userList = new ArrayList<>();
        int i = 0, page = 100;
        Boolean flag = true;
        do {
            long begin = i * page, end = (i + 1) * page;
            OapiUserListbypageResponse oapiUserListbypageResponse = dingDingService.getAllUserList(begin, end, departId, oapiGettokenResponse.getAccessToken());
            i++;
            if (oapiUserListbypageResponse == null || (oapiUserListbypageResponse != null && oapiUserListbypageResponse.getUserlist().size() < page)) {
                flag = false;
            }
            if (oapiUserListbypageResponse != null) {
                userList.addAll(oapiUserListbypageResponse.getUserlist());
            }
            logger.info("部门ID是：" + departId + "的第" + i + "循环");
        } while (flag);
        return userList;
    }

    private int saveUser(List<User> userList) {
        if (userList.size() > 0) {
            List<User> oldUserList = userDao.getAllUser();
            List<User> addUserList = new ArrayList<>();
            List<String> deleteUserList = new ArrayList<>();
            oldUserList.forEach(user -> {
                if (!userList.contains(user)) {
                    deleteUserList.add(user.getUserId());
                }
            });
            logger.info("需要删除的用户ID是：" + JSON.toJSONString(deleteUserList));
            if (deleteUserList.size() > 0) {
                userDao.deleteUser(deleteUserList);
            }
            userList.forEach(user -> {
                if (!oldUserList.contains(user)) {
                    addUserList.add(user);
                }
            });
            logger.info("需要添加的新用户是：" + JSON.toJSONString(addUserList));
            if (addUserList.size() > 0) {
                userDao.addUserList(addUserList);
            }
            return 1;
        }
        return -1;
    }


}

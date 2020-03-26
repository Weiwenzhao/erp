package com.hk.wwz.service.impl;

import com.alibaba.fastjson.JSON;
import com.hk.wwz.dao.ImpInfoMapper;
import com.hk.wwz.pojo.ImpInfo;
import com.hk.wwz.pojo.ResultBean;
import com.hk.wwz.service.KeyService;
import com.hk.wwz.until.key.AddKey;
import com.hk.wwz.until.key.CreateKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

@Service
public class KeyServiceImpl implements KeyService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ImpInfoMapper impInfoDao;

    @Override
    public ResultBean addKeyToStr(String str, String name) {
        ResultBean resultBean = new ResultBean();
        ImpInfo old_impInfo = impInfoDao.getImpInfoByName(name);
        Map<String, String> keyPairMap = CreateKeys.createKeys(512);
        String publicKey = keyPairMap.get("publicKey");
        String privateKey = keyPairMap.get("privateKey");
        String addKeyResult = "";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            addKeyResult = AddKey.publicEncrypt(str, AddKey.getPublicKey(publicKey));
        } catch (NoSuchAlgorithmException e) {
            logger.error("加密失败！原因是：" + e);
            resultBean.setCode(500);
            resultBean.setMessage("加密失败");
        } catch (InvalidKeySpecException e) {
            logger.error("加密失败！原因是：" + e);
            resultBean.setCode(500);
            resultBean.setMessage("加密失败");
        }
        logger.info(addKeyResult.length() + "");
        ImpInfo new_impInfo = new ImpInfo(UUID.randomUUID().toString().substring(0, 31), name, addKeyResult, publicKey, privateKey, timestamp, timestamp);
        if (old_impInfo != null) {
            impInfoDao.updateImpInfo(new_impInfo);
        } else {
            impInfoDao.insertImpInfo(new_impInfo);
        }
        resultBean.setCode(200);
        resultBean.setMessage(JSON.toJSONString(new_impInfo));
        return resultBean;
    }


    @Override
    public ResultBean removeKeyToStr(String name) {
        ImpInfo impInfo = impInfoDao.getImpInfoByName(name);
        if (impInfo == null) {
            return new ResultBean(500, "不存在该name的加密值");
        } else {
            try {
                String removeKey = AddKey.privateDecrypt(impInfo.getValue(), AddKey.getPrivateKey(impInfo.getPrivateKey()));
                return new ResultBean(200, removeKey);
            } catch (NoSuchAlgorithmException e) {
                logger.error("解密失败！原因是：" + e);
                return new ResultBean(500, "解密失败");
            } catch (InvalidKeySpecException e) {
                logger.error("解密失败！原因是：" + e);
                return new ResultBean(500, "解密失败");
            }
        }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public static void test() throws NoSuchAlgorithmException, InvalidKeySpecException {
        Map<String, String> keyPairMap = CreateKeys.createKeys(512);
        System.out.println("-----公钥----\n" + keyPairMap.get("publicKey"));
        System.out.println("-----私钥----\n" + keyPairMap.get("privateKey"));
        //System.out.println("-----私钥----\n"+keyPairMap.get("modles"));

        String data = "abc122";

        //1.用公钥加密
        String encode = AddKey.publicEncrypt(data, AddKey.getPublicKey(keyPairMap.get("publicKey")));

        System.out.println("-----加密结果----\n" + encode);
        //1.用私钥解密
        String decodeResult = AddKey.privateDecrypt(encode, AddKey.getPrivateKey(keyPairMap.get("privateKey")));
        System.out.println("-----解密结果----\n" + decodeResult);
    }
}

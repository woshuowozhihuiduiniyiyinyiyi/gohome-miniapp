package com.hj.tj.gohome.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.hj.tj.gohome.config.WxMaConfiguration;
import com.hj.tj.gohome.config.jwt.TokenHelper;
import com.hj.tj.gohome.enums.ErrorMsgEnum;
import com.hj.tj.gohome.exception.CustomException;
import com.hj.tj.gohome.mapper.OwnerMapper;
import com.hj.tj.gohome.service.OwnerService;
import com.hj.tj.gohome.vo.login.WxLoginReqObj;
import com.hj.tj.gohome.vo.login.WxLoginResObj;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Resource
    private OwnerMapper ownerMapper;

    @Resource
    private TokenHelper tokenHelper;

    @Override
    public WxLoginResObj login(WxLoginReqObj wxLoginReqObj) throws Exception{
        WxMaService wxMaService = WxMaConfiguration.getWxMaService();
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(wxLoginReqObj.getCode());

        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(session.getSessionKey(),
                wxLoginReqObj.getEncryptedData(), wxLoginReqObj.getIv());

        if (Objects.isNull(userInfo)) {
            throw new CustomException(ErrorMsgEnum.GET_WX_UER_INFO_ERROR);
        }

        // TODO 插入一个owner
        // ownerMapper.insert()

        WxLoginResObj wxLoginResObj = new WxLoginResObj();
        wxLoginResObj.setId(1);
        wxLoginResObj.setNickname("唐杰");

        wxLoginResObj.setSid(tokenHelper.generate(1, "", ""));

        return wxLoginResObj;
    }
}

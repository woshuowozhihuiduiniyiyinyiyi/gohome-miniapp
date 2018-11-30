package com.hj.tj.gohome.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hj.tj.gohome.config.WxMaConfiguration;
import com.hj.tj.gohome.config.jwt.TokenHelper;
import com.hj.tj.gohome.entity.Owner;
import com.hj.tj.gohome.enums.ErrorMsgEnum;
import com.hj.tj.gohome.exception.CustomException;
import com.hj.tj.gohome.mapper.OwnerMapper;
import com.hj.tj.gohome.service.OwnerService;
import com.hj.tj.gohome.vo.login.WxLoginReqObj;
import com.hj.tj.gohome.vo.login.WxLoginResObj;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Resource
    private OwnerMapper ownerMapper;

    @Resource
    private TokenHelper tokenHelper;

    @Resource
    private WxMaService wxMaService;

    @Override
    public WxLoginResObj login(WxLoginReqObj wxLoginReqObj) throws Exception {
        WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(wxLoginReqObj.getCode());

        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(session.getSessionKey(),
                wxLoginReqObj.getEncryptedData(), wxLoginReqObj.getIv());

        if (Objects.isNull(userInfo)) {
            throw new CustomException(ErrorMsgEnum.GET_WX_UER_INFO_ERROR);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("open_id", userInfo.getOpenId());
        Owner owner = ownerMapper.selectOne(queryWrapper);
        if (Objects.isNull(owner)) {
            owner = new Owner();
            BeanUtils.copyProperties(userInfo, owner);

            owner.setWxNickname(userInfo.getNickName());
            owner.setGender(Integer.parseInt(userInfo.getGender()));
            ownerMapper.insert(owner);
        }

        WxLoginResObj wxLoginResObj = new WxLoginResObj();
        wxLoginResObj.setId(1);
        wxLoginResObj.setNickname("唐杰");
        wxLoginResObj.setAvatarUrl(owner.getAvatarUrl());

        wxLoginResObj.setSid(tokenHelper.generate(1, "miniapp", owner.getWxNickname()));

        return wxLoginResObj;
    }
}

package com.hj.tj.gohome.controller;

import com.hj.tj.gohome.json.ApiRequest;
import com.hj.tj.gohome.json.JsonResponse;
import com.hj.tj.gohome.service.OwnerService;
import com.hj.tj.gohome.utils.JSONUtils;
import com.hj.tj.gohome.vo.login.WxLoginReqObj;
import com.hj.tj.gohome.vo.login.WxLoginResObj;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/wechat/api")
public class OwnerController {

    @Resource
    private OwnerService ownerService;

    @PostMapping("/user/login")
    public JsonResponse login(@RequestBody ApiRequest apiRequest){
        WxLoginReqObj wxLoginReqObj = JSONUtils.toBean(apiRequest.getData(), WxLoginReqObj.class);

        WxLoginResObj wxLoginResObj = null;
        try {
            wxLoginResObj = ownerService.login(wxLoginReqObj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JsonResponse.newOk(wxLoginResObj);
    }
}

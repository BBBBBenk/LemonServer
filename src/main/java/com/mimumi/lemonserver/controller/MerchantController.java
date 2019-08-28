package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.Merchant;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

    @RequestMapping(value="/entering",method = RequestMethod.POST)
    public ResponseResult EnteringMerchant(Merchant record, String picArr) {
        ResponseResult result = new ResponseResult();
        User current = UserUtil.getCurrentUser();
        result.setData(merchantService.enteringMerchant(record, picArr, current));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/getenteringlist",method = RequestMethod.GET)
    public ResponseResult getEnteringList(int page, int rows) {
        ResponseResult result = new ResponseResult();
        result.setData(merchantService.selectByCond(page, rows));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/getdetail",method = RequestMethod.POST)
    public ResponseResult getEnterDetail(int merchantid){
        ResponseResult result = new ResponseResult();
        result.setData(merchantService.select(merchantid));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/getindextop",method = RequestMethod.GET)
    public ResponseResult getIndexTop(){
        ResponseResult result = new ResponseResult();
        result.setData(merchantService.getIndexMerchant());
        result.setStatus(Constants.SUCCESS);
        return result;
    }


}

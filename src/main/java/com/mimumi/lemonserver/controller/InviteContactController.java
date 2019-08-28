package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.InvitecontactTree;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.utils.UserUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inco")
public class InviteContactController extends BaseController {

    @RequestMapping(value = "/getfanlist",method = RequestMethod.GET)
    public ResponseResult getFansList() {
        ResponseResult result = new ResponseResult();
        result.setStatus(Constants.SUCCESS);
        List<InvitecontactTree> data = inviteContactService.getFansList(UserUtil.getCurrentUser().getUserid());
        for(InvitecontactTree item : data) {
            User forMark = item.getInviteesUser();
            forMark.setMobile(forMark.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));

            User forMark2 = item.getInviteesUser();
            forMark2.setMobile(forMark2.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));

            for(InvitecontactTree sitem : item.getSonfans()){
                User sforMark = sitem.getInviteesUser();
                sforMark.setMobile(sforMark.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));

                User sforMark2 = sitem.getInviteesUser();
                sforMark2.setMobile(sforMark2.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
            }
        }
        result.setData(data);
        return result;
    }

}

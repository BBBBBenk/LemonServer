package com.mimumi.lemonserver.controller;

import com.mimumi.lemonserver.condition.UserCondition;
import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.dto.ResponseResult;
import com.mimumi.lemonserver.entity.*;
import com.mimumi.lemonserver.enums.Constants;
import com.mimumi.lemonserver.exception.BusinessException;
import com.mimumi.lemonserver.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 */
@RestController
@RequestMapping("/user")
public class UserController extends  BaseController {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SMSUtil smsUtil;

    @RequestMapping(value="/checklogin",method = RequestMethod.POST)
    public ResponseResult checkLogin(String mobile, String password) {
        ResponseResult result=new ResponseResult();
        String encryPwd= MD5Util.md5Encode(password);
        User user= userService.getByMobile(mobile);
        if(user==null){
            throw new BusinessException("用户找不到");
        }
        if(!encryPwd.equals(user.getPassword())) {
            throw new BusinessException("密码不正确");
        }
        result.setStatus(Constants.SUCCESS);
        result.setData(JWTUtil.sign(mobile, encryPwd));
        return result;
    }

    @RequestMapping(value="/getregistcode",method = RequestMethod.POST)
    public ResponseResult getRegistCode( String mobile) {
        ResponseResult result=new ResponseResult();
        String smsCode = RandomUtil.genPhoneRandomNum(4);
        redisUtil.set("REGIST_"+mobile,smsCode,10*60);
        smsUtil.sendSms(mobile,"{\"code\":\""+smsCode+"\"}" ,"SMS_164266380");
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/getphonebyid",method = RequestMethod.POST)
    public ResponseResult getPhoneById(Integer goodid) {
        ResponseResult result=new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();

        Good goodInfo = goodService.getByGoodId(goodid);
        if(goodInfo.getStatus() ==  1){
            throw new BusinessException("该需求已结单，无法显示手机");
        }

        result.setData(goodService.getPhoneByGoodId(goodid).getPhone());
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/getpermiss",method = RequestMethod.POST)
    public ResponseResult getPermiss(Integer userid, Integer goodid) {
        ResponseResult result=new ResponseResult();
        Viporder check = new Viporder();
        check.setGoodid(goodid);
        check.setUserid(userid);
        Viporder resultCheck = vipOrderService.checkPaied(check);
        if(resultCheck != null) {
            result.setData(goodService.getPhoneByGoodId(resultCheck.getGoodid()).getPhone());
            result.setStatus(Constants.SUCCESS);
        }else{
            result.setStatus(Constants.FAIL);
        }
        return result;
    }

    @RequestMapping(value="/costpointphone",method = RequestMethod.POST)
    public ResponseResult costPointGetPhone(Integer userid, Integer goodid) {
        ResponseResult result=new ResponseResult();
        UserPermitLog bought = userPermitService.getByUserGoodId(userid, goodid);
        Good goodInfo = goodService.getByGoodId(goodid);
        if(goodInfo.getStatus() ==  1){
            throw new BusinessException("该需求已结单，无法显示手机");
        }
        if(bought == null){
            User currentUser = UserUtil.getCurrentUser();
            BigDecimal cost = BigDecimal.valueOf(Integer.parseInt(configService.getByKey("view_consume_integration").getConfigvalue()));
            BigDecimal nowHave = currentUser.getTotalpoints();
            if (nowHave.compareTo(cost) > -1){
                currentUser.setTotalpoints(nowHave.subtract(cost));   //扣积分
                userService.update(currentUser);
                //购买后插入记录 防止重复购买
                UserPermitLog log = new UserPermitLog();
                log.setGoodid(goodid);
                log.setUserid(currentUser.getUserid());
                log.setPermitview(1);
                userPermitService.insert(log);
                Invitecontact record = new Invitecontact();
                Invitecontact finded = new Invitecontact();
                User secProxy = null;
                User firstProxy = null;
                record.setInvitees(currentUser.getUserid());
                /* 查找二级代理 */
                finded = inviteContactService.getUpProxy(record);
                if(finded != null){
                    secProxy = finded.getInviterUser();   //二级代理
                }
                /* 查找一级代理 */
                record.setInvitees(secProxy.getUserid());
                finded = inviteContactService.getUpProxy(record);
                if(finded != null){
                    firstProxy = finded.getInviterUser(); //一级代理
                }
                BigDecimal amount = cost; //本次消费
                int divipercenter = Integer.parseInt(configService.getByKey("sec_proxy").getConfigvalue()); //获取二级代理分成百分比
                int topdiviPercent = Integer.parseInt(configService.getByKey("first_proxy").getConfigvalue());//获取一级代理分成百分比
                /* 分成 */
                userService.dividenAmountCoast(secProxy, firstProxy, currentUser, amount, divipercenter, topdiviPercent);
                /* 记录分成 */
                dividenLogService.insertLog(secProxy, firstProxy, currentUser, amount,  divipercenter  , topdiviPercent);
            } else {
                throw new BusinessException("积分余额不足");
            }
        }
        result.setData(goodService.getPhoneByGoodId(goodid).getPhone());
        result.setStatus(Constants.SUCCESS);

        return result;
    }
    private  final Logger logger= LoggerFactory.getLogger(UserController.class);
    @RequestMapping(value="/wxregist",method = RequestMethod.POST)
    public ResponseResult wxRegister(String registcode, String mobile, String avatarUrl, String city, String gender, String nickName, String openId, String province, String invitecode, String password){
        logger.info(String.format("registcode=%s，mobile=%s，openId=%s，", registcode, mobile, openId));
        ResponseResult result = new ResponseResult();
        if( !redisUtil.hasKey("REGIST_"+mobile)) {
            throw new BusinessException("验证码已过期，请重新获取");
        }
        String validateCode= redisUtil.get("REGIST_"+mobile).toString();
        if(!registcode.equals(validateCode)){
            throw new BusinessException("验证码不正确，请重新输入");
        }
        User condition = new User();
        condition.setMobile(mobile);
        String token = "";
        User isExists= userService.checkOpenidIsExists(openId);
        //boolean isExists = userService.checkMobileIsExists(condition);
        if(isExists != null){   //如果已存在 更新基本信息和绑定openID
            User isexis = userService.getByMobile(mobile);
            isexis.setNickname(nickName);
            isexis.setOpenid(openId);
            isexis.setCity(city);
            isexis.setProvince(province);
            isexis.setHeadimgurl(avatarUrl);
            isexis.setSex(gender);
            isexis.setM_phone_view(1); //隐私默认可见
            userService.update(isexis);
            token = JWTUtil.sign(isexis.getMobile(),isexis.getPassword());
            if(invitecode != null && invitecode != "") {
                Invitecontact con = new Invitecontact();
                con.setInvitecode(invitecode);
                con.setInvitees(isexis.getUserid());
                User conditionCode = new User();
                conditionCode.setCode(invitecode.trim());
                User inviter = userService.getByCode(conditionCode);
                if(inviter == null){
                    throw new BusinessException("邀请码错误，请重新打开小程序");
                }
                con.setInviter(inviter.getUserid());
                inviteContactService.insert(con);
            }
        } else {
            User Register = new User();
            if(password == null){
                password = "lemonWxUser";
            }
            Register.setPassword(MD5Util.md5Encode(password));
            Register.setUsertype(0);
            Register.setIsvip(1);
            Register.setTotalpoints(BigDecimal.ZERO);
            Register.setMobile(mobile);
            Register.setIsbacklist(0);
            Register.setNickname(nickName);
            Register.setOpenid(openId);
            Register.setCity(city);
            Register.setM_phone_view(1);
            Register.setProvince(province);
            Register.setHeadimgurl(avatarUrl);
            Register.setSex(gender);
            userService.insert(Register);
            token = JWTUtil.sign(Register.getMobile(), Register.getPassword());
            if(invitecode != null && invitecode != "") {
                Invitecontact con = new Invitecontact();
                con.setInvitecode(invitecode);
                con.setInvitees(Register.getUserid());
                User conditionCode = new User();
                conditionCode.setCode(invitecode);
                User inviter = userService.getByCode(conditionCode);
                if(inviter == null){
                    throw new BusinessException("邀请码错误，请重新打开小程序");
                }
                con.setInviter(inviter.getUserid());
                inviteContactService.insert(con);
            }
        }
        result.setStatus(Constants.SUCCESS);
        result.setData(token);
        return result;
    }

    @RequestMapping(value="/regist",method = RequestMethod.POST)
    public ResponseResult regist(User user,String registcode, String invitecode) {
        ResponseResult result = new ResponseResult();
       if( !redisUtil.hasKey("REGIST_"+user.getMobile())) {
            throw new BusinessException("验证码已过期，请重新获取");
        }
        String validateCode= redisUtil.get("REGIST_"+user.getMobile()).toString();
        if(!registcode.equals(validateCode)){
            throw new BusinessException("验证码不正确，请重新输入");
        }

        String encryPwd=MD5Util.md5Encode(user.getPassword());
        user.setPassword(encryPwd);
        user.setUsertype(0);
        user.setIsvip(0);
        user.setTotalpoints(BigDecimal.ZERO);
        user.setIsbacklist(0);
        boolean isExists=userService.checkMobileIsExists(user);
        /*if(isExists) {
            throw new BusinessException("手机号码已存在,请重新输入！");
        }*/

        if(isExists) {
            userService.registPassToReset(user);
            result.setMessage("已和微信数据合并");
        }else{
            userService.insert(user);
            if(invitecode != null && invitecode != "") {

                Invitecontact con = new Invitecontact();
                con.setInvitecode(invitecode);
                con.setInvitees(user.getUserid());
                User conditionCode = new User();
                conditionCode.setCode(invitecode.trim());
                User inviter = userService.getByCode(conditionCode);
                if(inviter == null){
                    throw new BusinessException("邀请码错误，请重新打开公众号");
                }
                con.setInviter(inviter.getUserid());
                Invitecontact hasContact = inviteContactService.checkHasContact(con);
                if(hasContact == null){
                    inviteContactService.insert(con);
                }
            }
        }
        //普通注册

        result.setStatus(Constants.SUCCESS);
        result.setData(JWTUtil.sign(user.getMobile(), encryPwd));
        return result;
    }

    @RequestMapping(value="/updpwd",method = RequestMethod.POST)
    public ResponseResult updPwd (String mobile,String forgetcode,String password) {
        ResponseResult result =new ResponseResult();;
        if( !redisUtil.hasKey("FORGET_"+mobile)) {
            throw new BusinessException("验证码已过期，请重新获取");
        }
        String validateCode= redisUtil.get("FORGET_"+mobile).toString();
        if(!forgetcode.equals(validateCode)){
            throw new BusinessException("验证码不正确，请重新输入");
        }
        User user=new User();
        user.setMobile(mobile);
        user.setPassword(MD5Util.md5Encode(password));
        userService.updatePassWord(user);
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public ResponseResult delete(int userid) {
        ResponseResult result = new ResponseResult();
        boolean tmp=userService.delete(userid);
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value="/getcurrentuser",method = RequestMethod.POST)
    public ResponseResult getCurrentUser() {
        ResponseResult result =new ResponseResult();;
        result.setStatus(Constants.SUCCESS);
        User currentUser = UserUtil.getCurrentUser();
        result.setData(currentUser);
        return  result;
    }

    @RequestMapping(value="/getbytoken",method = RequestMethod.GET)
    public ResponseResult getByToken(String token) {
        ResponseResult result =new ResponseResult();
        String mobile=JWTUtil.getMobile(token);
        User user=userService.getByMobile(mobile);
        result.setStatus(Constants.SUCCESS);
        result.setData(user);
        return  result;
    }

    @RequestMapping(value="/slience",method = RequestMethod.GET)
    public ResponseResult slienceUser(String mobile, Integer slienceMode) {
        ResponseResult result =new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        if(currentUser.getUsertype() == 9){
            result.setData(userService.slienceUser(mobile, slienceMode));
            result.setStatus(Constants.SUCCESS);
        }else{
            result.setMessage("您不是管理员，无权操作");
            result.setStatus(Constants.FAIL);
        }
        return  result;
    }

    @RequestMapping(value="/chargepoint",method = RequestMethod.POST)
    public ResponseResult chargePoints(String mobile, Integer points) {
        ResponseResult result =new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        if(currentUser.getUsertype() == 9){ //只有管理员才能充值
            result.setData(userService.chargePoints(mobile, points, currentUser.getUserid()));
            result.setStatus(Constants.SUCCESS);
            result.setMessage("充值成功");
        }else{
            result.setMessage("您不是管理员，无权操作");
            result.setStatus(Constants.FAIL);
        }
        return  result;
    }


    @RequestMapping(value = "/getpaginglist",method = RequestMethod.POST)
    public ResponseResult getPagingList(UserCondition condition, int page, int rows){
        ResponseResult result=new ResponseResult();
        PageVo<User> list=userService.selectByCond(condition, page, rows);
        result.setStatus(Constants.SUCCESS);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseResult updateUserInfo(User user){
        ResponseResult result=new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        currentUser.setNickname(user.getNickname());
        currentUser.setProvince(user.getProvince());
        currentUser.setCity(user.getCity());
        currentUser.setAddress(user.getAddress());
        currentUser.setHeadimgurl(user.getHeadimgurl());
        userService.updateUserInfo(currentUser);
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/getvip",method = RequestMethod.POST)
    public ResponseResult getVipList(){
        ResponseResult result = new ResponseResult();
        List<Config> vipInfo = new ArrayList<Config>();
        vipInfo.add(configService.getByKey("onemonth_vip"));
        vipInfo.add(configService.getByKey("thirdmonth_vip"));
        vipInfo.add(configService.getByKey("sixmonth_vip"));
        vipInfo.add(configService.getByKey("oneyears_vip"));
        result.setData(vipInfo);
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/openvip",method = RequestMethod.POST)
    public ResponseResult openVip(Config vipmode){
        ResponseResult result = new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        if(currentUser.getIsvip() == 1){
            throw new BusinessException("您已经是vip了");
        }
        if(userService.openVip(currentUser, vipmode)){
            accountLogService.insertLog(currentUser,vipmode);
            Invitecontact record = new Invitecontact();
            Invitecontact finded = new Invitecontact();
            User secProxy = null;
            User firstProxy = null;
            record.setInvitees(currentUser.getUserid());
            /* 查找二级代理 */
            finded = inviteContactService.getUpProxy(record);
            if(finded != null){
                secProxy = finded.getInviterUser();   //二级代理
            }
            /* 查找一级代理 */
            record.setInvitees(secProxy.getUserid());
            finded = inviteContactService.getUpProxy(record);
            if(finded != null){
                firstProxy = finded.getInviterUser(); //一级代理
            }
            BigDecimal amount = BigDecimal.valueOf(Integer.parseInt(vipmode.getConfigvalue())); //本次消费
            int divipercenter = Integer.parseInt(configService.getByKey("sec_proxy").getConfigvalue()); //获取二级代理分成百分比
            int topdiviPercent = Integer.parseInt(configService.getByKey("first_proxy").getConfigvalue());//获取一级代理分成百分比
            /* 分成 */
            userService.dividenAmountCoast(secProxy, firstProxy, currentUser, amount, divipercenter, topdiviPercent);
            /* 记录分成 */
            dividenLogService.insertLog(secProxy, firstProxy, currentUser, amount, divipercenter, topdiviPercent);
        }else{
            throw new BusinessException("开通失败");
        }
        result.setMessage("开通成功");
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/magetvip",method = RequestMethod.POST)
    public ResponseResult manageGetVipList(){
        ResponseResult result = new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        if(currentUser.getUsertype()==9){
            List<Config> vipInfo = new ArrayList<Config>();
            vipInfo.add(configService.getByKey("good_recovery_days"));
            vipInfo.add(configService.getByKey("view_consume_integration"));
            vipInfo.add(configService.getByKey("check_click_times"));
            vipInfo.add(configService.getByKey("onemonth_vip"));
            vipInfo.add(configService.getByKey("thirdmonth_vip"));
            vipInfo.add(configService.getByKey("sixmonth_vip"));
            vipInfo.add(configService.getByKey("oneyears_vip"));
            vipInfo.add(configService.getByKey("check_interval_hours"));
            result.setData(vipInfo);
            result.setStatus(Constants.SUCCESS);
        }else{
            result.setMessage("状态错误");
            result.setStatus(Constants.FAIL);
        }

        return result;
    }

    @RequestMapping(value = "/changepoint",method = RequestMethod.POST)
    public ResponseResult changePoint(String configStr){
        ResponseResult result = new ResponseResult();
        User currentUser = UserUtil.getCurrentUser();
        if(currentUser.getUsertype() == 9){
            result.setStatus(Constants.SUCCESS);
            configService.updateList(configStr);
            result.setMessage("修改成功");
        }else{
            result.setStatus(Constants.FAIL);
            result.setMessage("您不是管理员，无权操作");
        }

        return result;
    }

    @RequestMapping(value = "/checkopenid",method = RequestMethod.POST)
    public ResponseResult checkOpenidReg(String openid){
        ResponseResult result = new ResponseResult();
        User loginUser = userService.checkOpenidIsExists(openid);
        if(loginUser == null){  //没注册过
            result.setStatus(Constants.FAIL);
            result.setData(false);
        } else {    //注册过
            result.setData(JWTUtil.sign(loginUser.getMobile(), loginUser.getPassword()));
            result.setStatus(Constants.SUCCESS);
        }
        return result;
    }

    @RequestMapping(value = "/initcode",method = RequestMethod.GET)
    public ResponseResult initInviteCode(){
        ResponseResult result = new ResponseResult();
        result.setData(userService.initInviteCode(UserUtil.getCurrentUser()));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

    @RequestMapping(value = "/privacymodify",method = RequestMethod.POST)
    public ResponseResult privacyModify(int isView){
        ResponseResult result = new ResponseResult();
        User current = UserUtil.getCurrentUser();
        current.setM_phone_view(isView);
        result.setData(userService.updatemview(current));
        result.setStatus(Constants.SUCCESS);
        return result;
    }

}

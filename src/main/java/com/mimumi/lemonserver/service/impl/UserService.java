package com.mimumi.lemonserver.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mimumi.lemonserver.condition.UserCondition;
import com.mimumi.lemonserver.dto.PageVo;
import com.mimumi.lemonserver.entity.Config;
import com.mimumi.lemonserver.entity.Recharge;
import com.mimumi.lemonserver.entity.User;
import com.mimumi.lemonserver.mapper.RechargeMapper;
import com.mimumi.lemonserver.mapper.UserMapper;
import com.mimumi.lemonserver.service.inter.IUserService;
import com.mimumi.lemonserver.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2019/4/19.
 */
@Service
public class UserService  implements IUserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    RechargeMapper rechargeMapper;

    public User getByMobile(String mobile){
        return userMapper.getByMobile(mobile);
    }

    public User getByUserId(Integer userid){
        return userMapper.getByUserId(userid);
    }

    public boolean checkMobileIsExists(User user){
        return userMapper.checkMobileIsExists(user)>0;
    }

    public boolean insert(User user){
        return userMapper.insert(user)>0;
    }

    public boolean update(User user){
        return userMapper.update(user)>0;
    }

    public boolean updateUserInfo(User user){
        return userMapper.updateUserInfo(user)>0;
    }

    public boolean delete(Integer userid){
        return userMapper.delete(userid)>0;
    }

    public boolean updatePassWord(User user){
        return userMapper.updatePassWord( user)>0;
    }

    public User checkOpenidIsExists(String openid) {
        User condition = new User();
        condition.setOpenid(openid);
        return userMapper.checkOpenidIsExists(condition);
    }

    public PageVo<User> selectByCond(UserCondition condition, int page, int rows){
        Page pageHelper = PageHelper.startPage(page, rows, true);
        List<User> pageList=userMapper.selectByCond(condition);
        PageVo<User> pager=new PageVo<User>(pageHelper.getTotal());
        pager.setRows(pageList);
        return pager;
    }

    public boolean slienceUser(String mobile, Integer slienceMode) {
        User targe = getByMobile(mobile);
        targe.setIsbacklist(slienceMode);
        return update(targe);
    }

    public boolean chargePoints(String mobile, Integer points, Integer managerId) {
        User targe = getByMobile(mobile);
        targe.setTotalpoints(targe.getTotalpoints().add(BigDecimal.valueOf(points)));
        //记录充值操作
        Recharge log = new Recharge();
        log.setAmount(points.longValue());
        log.setTarget(targe.getUserid());
        log.setOperator(managerId);
        rechargeMapper.insert(log);
        return update(targe);
    }

    public int updateVipInactive(){
        return userMapper.updateVipInactive();
    }

    public boolean openVip(User user, Config vipmode) {
        BigDecimal bouth = BigDecimal.valueOf(Integer.parseInt(vipmode.getConfigvalue()));
        int rangeOfDate = 1;
        switch (vipmode.getConfigid()){
            case 19:
                rangeOfDate = 1;
                break;
            case 20:
                rangeOfDate = 3;
                break;
            case 21:
                rangeOfDate = 6;
                break;
            case 22:
                rangeOfDate = 12;
                break;
            default:
                rangeOfDate = 1;
                break;
        }
        if(user.getTotalpoints().compareTo(bouth) > -1){
            user.setTotalpoints(user.getTotalpoints().subtract(bouth)); //扣积分
            user.setIsvip(1);   //设置vip
            user.setEffectdate(new Date()); //设置vip开始时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, rangeOfDate);
            user.setEffectdateto(calendar.getTime());
            return update(user);
        }else{
            return false;
        }
    }

    public String initInviteCode(User currentuser) {

        String inviteCode = "";
        int isExists = 0;
        do {

            inviteCode = RandomUtil.genRandomNum(8);
            isExists = userMapper.checkExistCode(inviteCode);

        } while(isExists != 0);
        currentuser.setCode(inviteCode);
        userMapper.updateInviteCode(currentuser);
        return inviteCode;
    }

    public User getByCode(User record){ return userMapper.getByCode(record); }

    public void dividenAmountCoast(User secProxy, User firstProxy, User consumer, BigDecimal amount, int topdiviPercent, int diviPercent) {
        if(secProxy != null){
            BigDecimal divi = BigDecimal.valueOf((double)(diviPercent)/100);
            divi = amount.multiply(divi);
            secProxy.setTotalpoints(secProxy.getTotalpoints().add(divi));
            update(secProxy);
            if(firstProxy != null){
                BigDecimal pdivi = BigDecimal.valueOf((double)(diviPercent)/100);
                pdivi = amount.multiply(pdivi);
                firstProxy.setTotalpoints(firstProxy.getTotalpoints().add(pdivi));
                update(firstProxy);
            }
        }
    }

    public boolean updatemview(User record) {
        return userMapper.updatemview(record) > 0;
    }

    public boolean registPassToReset(User record) { return userMapper.registPassToReset(record) > 0; }
}

package cn.crowdos.lottery.domain.activity.service.partake;

import cn.crowdos.lottery.domain.activity.model.req.PartakeReq;
import cn.crowdos.lottery.domain.activity.model.vo.ActivityBillVO;
import cn.crowdos.lottery.domain.activity.repository.IActivityRepository;

import javax.annotation.Resource;
/**
 * @File : ActivityPartakeSupport
 * @Author : LiXin Huang, NWPU
 * @Desc : 活动领取模操作，一些通用的数据服务
 * @Email : iHuanglixin@outlook.com
 */

public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req){
        return activityRepository.queryActivityBill(req);
    }

}

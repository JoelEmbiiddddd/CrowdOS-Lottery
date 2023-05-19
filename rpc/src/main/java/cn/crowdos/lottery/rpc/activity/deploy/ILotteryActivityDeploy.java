package cn.crowdos.lottery.rpc.activity.deploy;

import cn.crowdos.lottery.rpc.activity.deploy.req.ActivityPageReq;
import cn.crowdos.lottery.rpc.activity.deploy.res.ActivityRes;
/**
 * @File : ILotteryActivityDeploy
 * @Author : LiXin Huang, NWPU
 * @Desc : 抽奖活动部署服务接口
 * @Email : iHuanglixin@outlook.com
 */

public interface ILotteryActivityDeploy {

    /**
     * 通过分页查询活动列表信息，给ERP运营使用
     *
     * @param req   查询参数
     * @return      查询结果
     */
    ActivityRes queryActivityListByPageForErp(ActivityPageReq req);

}

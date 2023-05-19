package cn.crowdos.lottery.application.process.draw;

import cn.crowdos.lottery.application.process.draw.req.DrawProcessReq;
import cn.crowdos.lottery.application.process.draw.res.DrawProcessResult;
import cn.crowdos.lottery.application.process.draw.res.RuleQuantificationCrowdResult;
import cn.crowdos.lottery.domain.rule.model.req.DecisionMatterReq;

/**
 * @File : IActivityDrawProcess
 * @Author : LiXin Huang, NWPU
 * @Desc : 活动抽奖流程编排接口
 * @Email : iHuanglixin@outlook.com
 */

public interface IActivityDrawProcess {

    /**
     * 执行抽奖流程
     * @param req 抽奖请求
     * @return    抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);

    /**
     * 规则量化人群，返回可参与的活动ID
     * @param req   规则请求
     * @return      量化结果，用户可以参与的活动ID
     */
    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req);

}

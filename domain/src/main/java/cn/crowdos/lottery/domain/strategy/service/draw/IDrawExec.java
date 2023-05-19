package cn.crowdos.lottery.domain.strategy.service.draw;

import cn.crowdos.lottery.domain.strategy.model.req.DrawReq;
import cn.crowdos.lottery.domain.strategy.model.res.DrawResult;
/**
 * @File :  IDrawExec
 * @Author : LiXin Huang, NWPU
 * @Desc : 抽奖执行接口
 * @Email : iHuanglixin@outlook.com
 */

public interface IDrawExec {

    /**
     * 抽奖方法
     * @param req 抽奖参数；用户ID、策略ID
     * @return    中奖结果
     */
    DrawResult doDrawExec(DrawReq req);

}

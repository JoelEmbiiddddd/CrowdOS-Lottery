package cn.crowdos.lottery.rpc.activity.booth;

import cn.crowdos.lottery.rpc.activity.booth.req.DrawReq;
import cn.crowdos.lottery.rpc.activity.booth.req.QuantificationDrawReq;
import cn.crowdos.lottery.rpc.activity.booth.res.DrawRes;
/**
 * @File : ILotteryActivityBooth
 * @Author : LiXin Huang, NWPU
 * @Desc : 抽奖活动展台接口
 * @Email : iHuanglixin@outlook.com
 */

public interface ILotteryActivityBooth {

    /**
     * 指定活动抽奖
     * @param drawReq 请求参数
     * @return        抽奖结果
     */
    DrawRes doDraw(DrawReq drawReq);

    /**
     * 量化人群抽奖
     * @param quantificationDrawReq 请求参数
     * @return                      抽奖结果
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);

}

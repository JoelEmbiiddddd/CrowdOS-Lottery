package cn.crowdos.lottery.domain.rule.service.engine;

import cn.crowdos.lottery.domain.rule.model.res.EngineResult;
import cn.crowdos.lottery.domain.rule.model.req.DecisionMatterReq;
/**
 * @File : EngineFilter
 * @Author : LiXin Huang, NWPU
 * @Desc : 规则过滤器引擎
 * @Email : iHuanglixin@outlook.com
 */

public interface EngineFilter {

    /**
     * 规则过滤器接口
     *
     * @param matter      规则决策物料
     * @return            规则决策结果
     */
    EngineResult process(final DecisionMatterReq matter);

}

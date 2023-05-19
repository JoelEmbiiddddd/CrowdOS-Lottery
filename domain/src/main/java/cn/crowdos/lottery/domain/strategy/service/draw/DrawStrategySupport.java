package cn.crowdos.lottery.domain.strategy.service.draw;

import cn.crowdos.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.crowdos.lottery.domain.strategy.model.vo.AwardBriefVO;
import cn.crowdos.lottery.domain.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;
/**
 * @File :  DrawStrategySupport
 * @Author : LiXin Huang, NWPU
 * @Desc : 抽奖策略数据支撑，一些通用的数据服务
 * @Email : iHuanglixin@outlook.com
 */

public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected IStrategyRepository strategyRepository;

    /**
     * 查询策略配置信息
     * 比如说 查询抽奖策略等
     *
     * @param strategyId 策略ID
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId){
        //多个表连接，查询策略配置信息
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详情信息
     *
     * @param awardId 奖品ID
     * @return 中奖详情
     */
    protected AwardBriefVO queryAwardInfoByAwardId(String awardId){
        return strategyRepository.queryAwardInfo(awardId);
    }

}

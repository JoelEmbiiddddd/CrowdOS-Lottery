package cn.crowdos.lottery.domain.strategy.service.algorithm.impl;

import cn.crowdos.lottery.common.Constants;
import cn.crowdos.lottery.domain.strategy.annotation.Strategy;
import cn.crowdos.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @File : SingleRateRandomDrawAlgorithm
 * @Author : LiXin Huang, NWPU
 * @Desc : 单项随机概率抽奖，抽到一个已经排掉的奖品则未中奖
 * @Email : iHuanglixin@outlook.com
 */

@Component("singleRateRandomDrawAlgorithm")
@Strategy(strategyMode = Constants.StrategyMode.SINGLE)
public class SingleRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        /**
         *   strategyID: 策略ID号
         *   excludeAwardIds： 显存还有的奖品
         */

        // 获取策略对应的元祖
        String[] rateTuple = super.rateTupleMap.get(strategyId);  //  存放概率与奖品对应的散列结果，strategyId -> rateTuple
        assert rateTuple != null;  // 判断是否为null

        // 随机索引
        int randomVal = this.generateSecureRandomIntCode(100);   // 生成百位随机抽奖码
        int idx = super.hashIdx(randomVal);  // 获得中奖码

        // 返回结果
        String awardId = rateTuple[idx];

        // 如果中奖ID命中排除奖品列表，则返回NULL
        if (excludeAwardIds.contains(awardId)) {   // 如果抽奖没了，就告诉用户未中奖
            return null;
        }

        return awardId;
    }

}

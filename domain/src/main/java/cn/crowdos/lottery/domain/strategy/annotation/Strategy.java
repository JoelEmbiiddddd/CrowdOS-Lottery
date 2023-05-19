package cn.crowdos.lottery.domain.strategy.annotation;

import cn.crowdos.lottery.common.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @File : Strategy
 * @Author : LiXin Huang, NWPU
 * @Desc : 抽奖策略模型注解
 * @Email : iHuanglixin@outlook.com
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Strategy {

    Constants.StrategyMode strategyMode();

}

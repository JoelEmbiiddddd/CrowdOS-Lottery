package cn.crowdos.lottery.domain.strategy.service.draw;

import cn.crowdos.lottery.domain.strategy.annotation.Strategy;
import cn.crowdos.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @File :  DrawConfig
 * @Author : LiXin Huang, NWPU
 * @Desc : 抽奖统一配置信息类
 * @Email : iHuanglixin@outlook.com
 */

public class DrawConfig {

    @Resource
    private List<IDrawAlgorithm> algorithmList;

    /**
     * 抽奖策略组
     */
    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmGroup = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        algorithmList.forEach(algorithm -> {
            Strategy strategy = AnnotationUtils.findAnnotation(algorithm.getClass(), Strategy.class); //是否有Strategy的注解，如果有，则返回注解，没有则返回空
            if (null != strategy) {
                drawAlgorithmGroup.put(strategy.strategyMode().getCode(), algorithm);
            }
        });
    }

}

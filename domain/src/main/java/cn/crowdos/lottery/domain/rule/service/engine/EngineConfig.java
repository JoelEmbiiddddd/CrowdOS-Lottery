package cn.crowdos.lottery.domain.rule.service.engine;

import cn.crowdos.lottery.common.Constants;
import cn.crowdos.lottery.domain.rule.service.logic.LogicFilter;
import cn.crowdos.lottery.domain.rule.service.logic.impl.UserAgeFilter;
import cn.crowdos.lottery.domain.rule.service.logic.impl.UserGenderFilter;
import jdk.nashorn.internal.ir.annotations.Reference;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @File : EngineConfig
 * @Author : LiXin Huang, NWPU
 * @Desc : 规则配置
 * @Email : iHuanglixin@outlook.com
 */

public class EngineConfig {

    protected static Map<String, LogicFilter> logicFilterMap = new ConcurrentHashMap<>();

    @Resource
    private UserAgeFilter userAgeFilter;
    @Resource
    private UserGenderFilter userGenderFilter;

    @PostConstruct
    public void init() {
        logicFilterMap.put("userAge", userAgeFilter);
        logicFilterMap.put("userGender", userGenderFilter);
    }

}

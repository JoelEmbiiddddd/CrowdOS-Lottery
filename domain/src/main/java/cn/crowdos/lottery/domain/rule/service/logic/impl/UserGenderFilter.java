package cn.crowdos.lottery.domain.rule.service.logic.impl;

import cn.crowdos.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.crowdos.lottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;
/**
 * @File : UserGenderFilter
 * @Author : LiXin Huang, NWPU
 * @Desc : 性别规则
 * @Email : iHuanglixin@outlook.com
 */

@Component
public class UserGenderFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("gender").toString();
    }
    
}

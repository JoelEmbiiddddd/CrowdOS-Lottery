package cn.crowdos.lottery.domain.rule.service.logic.impl;

import cn.crowdos.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.crowdos.lottery.domain.rule.service.logic.BaseLogic;
import org.springframework.stereotype.Component;
/**
 * @File : UserAgeFilter
 * @Author : LiXin Huang, NWPU
 * @Desc : 年龄规则
 * @Email : iHuanglixin@outlook.com
 */

@Component
public class UserAgeFilter extends BaseLogic {

    @Override
    public String matterValue(DecisionMatterReq decisionMatter) {
        return decisionMatter.getValMap().get("age").toString();
    }

}

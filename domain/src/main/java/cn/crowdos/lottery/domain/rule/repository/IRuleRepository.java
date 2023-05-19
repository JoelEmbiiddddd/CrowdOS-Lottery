package cn.crowdos.lottery.domain.rule.repository;

import cn.crowdos.lottery.domain.rule.model.aggregates.TreeRuleRich;
/**
 * @File : IRuleRepository
 * @Author : LiXin Huang, NWPU
 * @Desc : 规则信息仓储服务接口
 * @Email : iHuanglixin@outlook.com
 */

public interface IRuleRepository {

    /**
     * 查询规则决策树配置
     *
     * @param treeId    决策树ID
     * @return          决策树配置
     */
    TreeRuleRich queryTreeRuleRich(Long treeId);

}

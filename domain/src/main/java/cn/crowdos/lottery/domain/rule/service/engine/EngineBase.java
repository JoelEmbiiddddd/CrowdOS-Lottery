package cn.crowdos.lottery.domain.rule.service.engine;

import cn.crowdos.lottery.common.Constants;
import cn.crowdos.lottery.domain.rule.model.aggregates.TreeRuleRich;
import cn.crowdos.lottery.domain.rule.model.res.EngineResult;
import cn.crowdos.lottery.domain.rule.model.req.DecisionMatterReq;
import cn.crowdos.lottery.domain.rule.model.vo.TreeNodeVO;
import cn.crowdos.lottery.domain.rule.model.vo.TreeRootVO;
import cn.crowdos.lottery.domain.rule.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
/**
 * @File : EngineBase
 * @Author : LiXin Huang, NWPU
 * @Desc : 规则引擎基础类
 * @Email : iHuanglixin@outlook.com
 */

public abstract class EngineBase extends EngineConfig implements EngineFilter {

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);

    @Override
    public EngineResult process(DecisionMatterReq matter) {
        throw new RuntimeException("未实现规则引擎服务");
    }

    protected TreeNodeVO engineDecisionMaker(TreeRuleRich treeRuleRich, DecisionMatterReq matter) {
        TreeRootVO treeRoot = treeRuleRich.getTreeRoot();
        Map<Long, TreeNodeVO> treeNodeMap = treeRuleRich.getTreeNodeMap();

        // 规则树根ID
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNodeVO treeNodeInfo = treeNodeMap.get(rootNodeId);

        // 节点类型[NodeType]；1子叶、2果实
        while (Constants.NodeType.STEM.equals(treeNodeInfo.getNodeType())) {  // 循环遍历一个二叉树
            String ruleKey = treeNodeInfo.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(matter);
            Long nextNode = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLineInfoList());   // 来判断下一步是往左走还是往右走
            treeNodeInfo = treeNodeMap.get(nextNode);  // 拿到该枝叶节点的信息
            logger.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRoot.getTreeName(), matter.getUserId(), matter.getTreeId(), treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }

        return treeNodeInfo;
    }

}

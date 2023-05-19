package cn.crowdos.lottery.infrastructure.dao;

import cn.crowdos.lottery.infrastructure.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @File : RuleTreeNodeLineDao
 * @Author : LiXin Huang, NWPU
 * @Desc : 规则树节点连线DAO
 * @Email : iHuanglixin@outlook.com
 */

@Mapper
public interface RuleTreeNodeLineDao {

    /**
     * 查询规则树节点连线集合
     * @param req   入参
     * @return      规则树节点连线集合
     */
    List<RuleTreeNodeLine> queryRuleTreeNodeLineList(RuleTreeNodeLine req);

    /**
     * 查询规则树连线数量
     *
     * @param treeId    规则树ID
     * @return          规则树连线数量
     */
    int queryTreeNodeLineCount(Long treeId);

}

package cn.crowdos.lottery.infrastructure.dao;

import cn.crowdos.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * @File : IAwardDao
 * @Author : LiXin Huang, NWPU
 * @Desc : 奖品信息表DAO
 * @Email : iHuanglixin@outlook.com
 */

@Mapper
public interface IAwardDao {

    /**
     * 查询奖品信息
     *
     * @param awardId 奖品ID
     * @return        奖品信息
     */
    Award queryAwardInfo(String awardId);

    /**
     * 插入奖品配置
     *
     * @param list 奖品配置
     */
    void insertList(List<Award> list);

}

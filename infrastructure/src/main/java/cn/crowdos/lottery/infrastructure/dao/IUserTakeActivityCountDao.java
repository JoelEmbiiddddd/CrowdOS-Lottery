package cn.crowdos.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.crowdos.lottery.infrastructure.po.UserTakeActivityCount;
import org.apache.ibatis.annotations.Mapper;
/**
 * @File : IUserTakeActivityCountDao
 * @Author : LiXin Huang, NWPU
 * @Desc : 用户活动参与次数表Dao
 * @Email : iHuanglixin@outlook.com
 */

@Mapper
public interface IUserTakeActivityCountDao {

    /**
     * 查询用户领取次数信息
     *
     * @param userTakeActivityCountReq 请求入参【活动号、用户ID】
     * @return 领取结果
     */
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    /**
     * 插入领取次数信息
     *
     * @param userTakeActivityCount 请求入参
     */
    void insert(UserTakeActivityCount userTakeActivityCount);

    /**
     * 更新领取次数信息
     *
     * @param userTakeActivityCount 请求入参
     * @return 更新数量
     */
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);

}

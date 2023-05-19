package cn.crowdos.lottery.infrastructure.repository;

import cn.crowdos.lottery.domain.award.repository.IOrderRepository;
import cn.crowdos.lottery.infrastructure.dao.IUserStrategyExportDao;
import cn.crowdos.lottery.infrastructure.po.UserStrategyExport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
/**
 * @File : OrderRepository
 * @Author : LiXin Huang, NWPU
 * @Desc : 奖品表仓储服务
 * @Email : iHuanglixin@outlook.com
 */

@Repository
public class OrderRepository implements IOrderRepository {

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Override
    public void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId(uId);
        userStrategyExport.setOrderId(orderId);
        userStrategyExport.setAwardId(awardId);
        userStrategyExport.setGrantState(grantState);
        userStrategyExportDao.updateUserAwardState(userStrategyExport);
    }
}

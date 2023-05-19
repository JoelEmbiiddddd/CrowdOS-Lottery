package cn.crowdos.lottery.domain.award.service.goods;

import cn.crowdos.lottery.domain.award.repository.IOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
/**
 * @File : DistributionBase
 * @Author : LiXin Huang, NWPU
 * @Desc : 配送货物基础共用类
 * @Email : iHuanglixin@outlook.com
 */

public class DistributionBase {

    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    @Resource
    private IOrderRepository awardRepository;

    protected void updateUserAwardState(String uId, Long orderId, String awardId, Integer grantState) {
        awardRepository.updateUserAwardState(uId, orderId, awardId, grantState);
    }

}

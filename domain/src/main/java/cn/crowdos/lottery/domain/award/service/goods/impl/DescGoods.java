package cn.crowdos.lottery.domain.award.service.goods.impl;

import cn.crowdos.lottery.common.Constants;
import cn.crowdos.lottery.domain.award.model.req.GoodsReq;
import cn.crowdos.lottery.domain.award.model.res.DistributionRes;
import cn.crowdos.lottery.domain.award.service.goods.DistributionBase;
import cn.crowdos.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;
/**
 * @File : DescGoods
 * @Author : LiXin Huang, NWPU
 * @Desc : 描述类商品，以文字形式展示给用户
 * @Email : iHuanglixin@outlook.com
 */

@Component
public class DescGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.GrantState.COMPLETE.getCode());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }

}

package cn.crowdos.lottery.domain.award.service.factory;

import cn.crowdos.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;
/**
 * @File : DistributionGoodsFactory
 * @Author : LiXin Huang, NWPU
 * @Desc : 配送商品简单工厂，提供获取配送服务
 * @Email : iHuanglixin@outlook.com
 */

@Service
public class DistributionGoodsFactory extends GoodsConfig {

    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsMap.get(awardType);
    }

}

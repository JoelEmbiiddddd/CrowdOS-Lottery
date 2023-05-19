package cn.crowdos.lottery.domain.support.ids.policy;

import cn.crowdos.lottery.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
/**
 * @File :  RandomNumeric
 * @Author : LiXin Huang, NWPU
 * @Desc : 工具类生成 org.apache.commons.lang3.RandomStringUtils
 * @Email : iHuanglixin@outlook.com
 */

@Component
public class RandomNumeric implements IIdGenerator {

    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }

}

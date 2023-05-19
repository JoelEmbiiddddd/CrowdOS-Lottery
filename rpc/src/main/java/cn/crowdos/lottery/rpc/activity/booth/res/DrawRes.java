package cn.crowdos.lottery.rpc.activity.booth.res;

import cn.crowdos.lottery.common.Result;
import cn.crowdos.lottery.rpc.activity.booth.dto.AwardDTO;

import java.io.Serializable;
/**
 * @File : DrawRes
 * @Author : LiXin Huang, NWPU
 * @Desc : 抽奖结果
 * @Email : iHuanglixin@outlook.com
 */

public class DrawRes extends Result implements Serializable {

    private AwardDTO awardDTO;

    public DrawRes(String code, String info) {
        super(code, info);
    }

    public AwardDTO getAwardDTO() {
        return awardDTO;
    }

    public void setAwardDTO(AwardDTO awardDTO) {
        this.awardDTO = awardDTO;
    }

}

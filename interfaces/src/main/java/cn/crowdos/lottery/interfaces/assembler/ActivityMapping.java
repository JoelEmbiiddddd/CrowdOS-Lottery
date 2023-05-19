package cn.crowdos.lottery.interfaces.assembler;

import cn.crowdos.lottery.domain.activity.model.vo.ActivityVO;
import cn.crowdos.lottery.domain.strategy.model.vo.DrawAwardVO;
import cn.crowdos.lottery.rpc.activity.booth.dto.AwardDTO;
import cn.crowdos.lottery.rpc.activity.deploy.dto.ActivityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
/**
 * @File : ActivityMapping
 * @Author : LiXin Huang, NWPU
 * @Desc : 活动对象转换配置
 * @Email : iHuanglixin@outlook.com
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ActivityMapping extends IMapping<ActivityVO, ActivityDTO>{

    @Override
    List<ActivityDTO> sourceToTarget(List<ActivityVO> var1);

}

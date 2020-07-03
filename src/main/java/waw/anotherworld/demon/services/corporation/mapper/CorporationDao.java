package waw.anotherworld.demon.services.corporation.mapper;

import org.apache.ibatis.annotations.Mapper;
import waw.anotherworld.demon.services.corporation.model.CorporationVO;
import waw.anotherworld.demon.services.disclosure.model.DisClosureVO;

import java.util.List;

@Mapper
public interface CorporationDao {

    public int insertCorpList(List<CorporationVO> list);


}

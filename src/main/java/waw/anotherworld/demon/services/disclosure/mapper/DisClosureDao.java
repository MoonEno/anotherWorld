package waw.anotherworld.demon.services.disclosure.mapper;

import org.apache.ibatis.annotations.Mapper;
import waw.anotherworld.demon.services.disclosure.model.DisClosureVO;


@Mapper
public interface DisClosureDao {

    public int insertDisclosureInfo(DisClosureVO disClosureVO);

    public String selectAlreadySendInfoCheck();
}

package fence.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import fence.entity.UserEntity;

@Mapper
@Repository
public interface IUserDao extends IBaseDao<UserEntity> {

}

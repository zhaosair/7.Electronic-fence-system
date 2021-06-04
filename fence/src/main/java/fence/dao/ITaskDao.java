package fence.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import fence.entity.TaskEntity;

@Mapper
@Repository
public interface ITaskDao extends IBaseDao<TaskEntity> {

}

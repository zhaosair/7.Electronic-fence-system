package fence.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.util.Date;
import java.util.Date;
import java.util.Date;

@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity extends CommonEntity implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
	 *  id
	 */
	@ApiModelProperty(value = "id", name = "id")
    private Integer id;
    /**
	 *  name
	 */
	@ApiModelProperty(value = "name", name = "name")
    private String name;
    /**
	 *  uid
	 */
	@ApiModelProperty(value = "uid", name = "uid")
    private Integer uid;
    /**
	 *  rid
	 */
	@ApiModelProperty(value = "rid", name = "rid")
    private Integer rid;
    /**
	 *  creatime
	 */
	@ApiModelProperty(value = "creatime", name = "creatime")
    private Date creatime;
    /**
	 *  startime
	 */
	@ApiModelProperty(value = "startime", name = "startime")
    private Date startime;
    /**
	 *  endtime
	 */
	@ApiModelProperty(value = "endtime", name = "endtime")
    private Date endtime;
    /**
	 *  finishtime
	 */
	@ApiModelProperty(value = "finishtime", name = "finishtime")
    private Date finishtime;
    /**
	 *  status
	 */
	@ApiModelProperty(value = "status", name = "status")
    private Integer status;
    /**
	 *  startlng
	 */
	@ApiModelProperty(value = "startlng", name = "startlng")
    private String startlng;
    /**
	 *  startlat
	 */
	@ApiModelProperty(value = "startlat", name = "startlat")
    private String startlat;
    /**
	 *  endlng
	 */
	@ApiModelProperty(value = "endlng", name = "endlng")
    private String endlng;
    /**
	 *  endlat
	 */
	@ApiModelProperty(value = "endlat", name = "endlat")
    private String endlat;


}

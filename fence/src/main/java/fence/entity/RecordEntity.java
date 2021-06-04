package fence.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;

@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntity extends CommonEntity implements Serializable {

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
	 *  uid
	 */
	@ApiModelProperty(value = "uid", name = "uid")
    private Integer uid;
    /**
	 *  lng
	 */
	@ApiModelProperty(value = "lng", name = "lng")
    private String lng;
    /**
	 *  lat
	 */
	@ApiModelProperty(value = "lat", name = "lat")
    private String lat;
    /**
	 *  creatime
	 */
	@ApiModelProperty(value = "creatime", name = "creatime")
    private Date creatime;


}

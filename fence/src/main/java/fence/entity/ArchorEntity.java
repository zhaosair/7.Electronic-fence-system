package fence.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ArchorEntity extends CommonEntity implements Serializable {

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
	 *  order_sec
	 */
	@ApiModelProperty(value = "order_sec", name = "order_sec")
    private String order_sec;
    /**
	 *  rid
	 */
	@ApiModelProperty(value = "rid", name = "rid")
    private Integer rid;


}

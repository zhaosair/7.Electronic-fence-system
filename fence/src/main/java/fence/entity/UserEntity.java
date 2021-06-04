package fence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class UserEntity extends CommonEntity implements Serializable {

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
	 *  login
	 */
	@ApiModelProperty(value = "login", name = "login")
    private String login;
    /**
	 *  name
	 */
	@ApiModelProperty(value = "name", name = "name")
    private String name;
    /**
	 *  passwd
	 */
	@ApiModelProperty(value = "passwd", name = "passwd")
    private String passwd;
    /**
	 *  sex
	 */
	@ApiModelProperty(value = "sex", name = "sex")
    private String sex;
    /**
	 *  phone
	 */
	@ApiModelProperty(value = "phone", name = "phone")
    private String phone;
    /**
	 *  birthday
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "birthday", name = "birthday")
    private Date birthday;
    /**
	 *  logintime
	 */
	@ApiModelProperty(value = "logintime", name = "logintime")
    private Date logintime;
    /**
	 *  logoutime
	 */
	@ApiModelProperty(value = "logoutime", name = "logoutime")
    private Date logoutime;
    /**
	 *  status
	 */
	@ApiModelProperty(value = "status", name = "status")
    private Integer status;
    /**
	 *  dpid
	 */
	@ApiModelProperty(value = "dpid", name = "dpid")
    private Integer dpid;
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

	@ApiModelProperty(value = "dpname",name = "dpname")
	private String dpname;


}

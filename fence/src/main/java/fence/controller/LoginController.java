package fence.controller;

import fence.core.exception.BusinessException;
import fence.entity.UserEntity;
import fence.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;
import fence.entity.User;
import fence.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "login接口")
@RequestMapping("/login")
public class LoginController {

	private ILoginService service;
	private IUserService userService;

	@Autowired
	public LoginController(ILoginService service, IUserService userService) {
		this.service = service;
		this.userService = userService;
	}
	
	@ApiOperation(value = "登录")
	@PostMapping(value = "/doLogin")
	public void doLogin(@RequestBody User user, HttpServletRequest request,HttpServletResponse resp) throws JSONException, IOException {

		User currentUser = service.login(user);
		UserEntity ue=new UserEntity();
		ue.setLogin(user.getLogin());
		ue.setPasswd(user.getPasswd());
		List<UserEntity> ulist= userService.select(ue);
		if (currentUser != null) {
			request.getSession().setAttribute("user", user);
			System.out.println(ulist.get(0).toString());

			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json; charset=utf-8");
			PrintWriter out = null ;
			JSONObject res = new JSONObject();

			res.put("code",200);
			res.put("status","yes");
			res.put("msg","成功");
			res.put("uid",ulist.get(0).getId());
			res.put("name",ulist.get(0).getName());
			out = resp.getWriter();
			out.append(res.toString());
		} else {
			throw new BusinessException("用户名或密码错误");
		}
	}
	@ApiOperation(value = "登录")
	@PostMapping(value = "/auth")
	public void doLoginReact(@RequestBody User user, HttpServletRequest request, HttpServletResponse resp) throws IOException, JSONException {

		UserEntity ue=new UserEntity();
		ue.setLogin(user.getLogin());
		ue.setPasswd(user.getPasswd());
		List<UserEntity> ulist= userService.select(ue);
		if (ulist.size()==1) {
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json; charset=utf-8");
			PrintWriter out = null ;
			JSONObject res = new JSONObject();
			res.put("currentAuthority","admin");
			res.put("status","yes");
			res.put("data:","{yes}");
			res.put("access:","admin");
			out = resp.getWriter();
			out.append(res.toString());
			request.getSession().setAttribute("user",ulist.get(0));
			request.getSession().setAttribute("userId",ulist.get(0).getId());


		} else {
			throw new BusinessException("用户名或密码错误");
		}

	}
	@ApiOperation(value = "查询当前用户")
	@RequestMapping(value = "/currentUser",method=RequestMethod.GET)
	@ResponseBody
	public UserEntity currentUser(HttpServletRequest request) {

		UserEntity ue=new UserEntity();
		System.out.println(request.getSession().getAttribute("userId"));
		int ueid = (int) request.getSession().getAttribute("userId");
		ue.setId(ueid);
		ue=userService.select(ue).get(0);

		if (ue != null) {
			return ue;
		} else {
			throw new BusinessException(585,"游客状态未登陆");
		}

	}

	
	@ApiOperation(value = "退出登录")
	@PostMapping(value = "/doLogOut")
	public void doLogOut(HttpServletRequest request) {
		request.getSession().removeAttribute("user");

	}

}

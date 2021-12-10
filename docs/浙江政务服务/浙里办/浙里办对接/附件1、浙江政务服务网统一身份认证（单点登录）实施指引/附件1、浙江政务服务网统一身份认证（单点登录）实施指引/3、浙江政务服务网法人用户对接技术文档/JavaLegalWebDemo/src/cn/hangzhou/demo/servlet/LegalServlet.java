package cn.hangzhou.demo.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.timevale.TokenUtils;

import cn.hangzhou.demo.bean.UserBean;
import cn.hangzhou.demo.service.UserService;

public class LegalServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6187964647473231392L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 跳转Url
		String redirectUrl = "";
		//ssoticket是跨session用的，在原先链接里面加上&ssoticket=就可以实现跨session登录
		//System.out.println("ssoticket==" + request.getParameter("ssoticket"));

		// 第一步：从Request请求的参数中获取ssotoken
		String ssotoken = request.getParameter("ssotoken");
		System.out.println("ssotoken = " + ssotoken);
		//取具体办事事项地址（若此项有值，成功登录后请跳转此地址到具体事项，否则跳转系统首页）
		String gotoUrl = request.getQueryString();
		if (null != gotoUrl && !gotoUrl.trim().equals("")) {
			//清理事项地址前的“goto=”标识
			gotoUrl = gotoUrl.substring(5);
			System.out.println("登录后跳转到： " + gotoUrl);
		}
		
		// 第二步：通过ssotoken来验证获取到的法人信息数据包的有效性,并解析出法人信息
		Map legalInfo = TokenUtils.getUserInfo(ssotoken);
		
		// 验证失败，跳转登录失败的页面
		if (null == legalInfo) {
			redirectUrl = "fail.html";
		}
		
		// 验证成功
		else {
			System.out.println("政务服务网登录账号信息=" + legalInfo);
			
			// 第三步：获取政务服务网账户标识与自建系统中关联项信息，请以政务服务网中的userId作为用户关联项
			// 企业名称
			String companyName = legalInfo.get("CompanyName").toString();
			System.out.println("companyName=" + companyName);
			
			// 工商注册号
			String regNumber = null;
			Object regNumberObj = legalInfo.get("CompanyRegNumber");
			if (null != regNumberObj){
				regNumber = regNumberObj.toString();
			}				
			
			// 机构代码号
			String orgNumber = null;
			Object orgNumberObj = legalInfo.get("OrganizationNumber");
			if (null != orgNumberObj){
				orgNumber = orgNumberObj.toString();
			}				

			// 统一社会信用代码
			String uniscid = null;
			Object uniscidObj = legalInfo.get("uniscid");
			if (null != uniscidObj){
				uniscid = uniscidObj.toString();
			}			
			
			// 用户id，政务服务网账号唯一标识
			String userid = null;
			Object useridObj = legalInfo.get("userId");
			if (null != useridObj){
				userid = useridObj.toString();
				System.out.println("政务服务网账号唯一标识userId=" + userid);
			}			

			// 用户在政务服务网认证等级
			String realLevel = null;
			Object RealLevelObj = legalInfo.get("realLevel");
			if (null != RealLevelObj){
				realLevel = RealLevelObj.toString();
				System.out.println("用户在政务服务网认证等级RealLevel=" + realLevel);
			}				

			// 第四步：根据政务服务网账号唯一标识userId关联项在自建系统中查找是否存在此法人的账户信息
			// 本Demo中以123321作为政务服务网账号唯一标识userId，故意使其关联失败用于演示账户绑定页面
			UserBean userBean = UserService.findUser("123321");

			// 第五步：找不到关联用户，表示该法人第一次登陆自建系统， 显示页面由用户选择以此信息绑定自建系统中原有用户或在自建系统中新注册账号，账户与政务服务网账号唯一标识userid绑定
			if (null == userBean) {
				redirectUrl = "bind.html?companyName=" + URLEncoder.encode(companyName, "UTF8") + "&userId=" + URLEncoder.encode(userid, "UTF8");
			}
			
			// 第六步：找到了关联用户，显示法人信息
			else {
				//成功登录时，若gotoUrl有值，先将政务服务网账号唯一标识userid与现有的账号绑定，然后跳转到具体的办事事项，否则跳转系统首页
				redirectUrl = "success.html?companyName=" + URLEncoder.encode(companyName, "UTF8") + "&userId=" + URLEncoder.encode(userid, "UTF8");
			}
		}
		response.setHeader("refresh", "1;URL=" + redirectUrl);	
	}

}

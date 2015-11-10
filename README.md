# sapi
创建了一个移动app的后端架构。
主要采用**Servlet ＋ JavaBean + JDBC**的形式
前端提供了jsp测试代码
后期考虑把 JDBC模块改为Mybaties

#初步实现了：
1, 常用工具
2, 增加日志工具Log4j
3， 采用C3P0数据库连接池
4， 对请求进行签名过滤
5， 对请求进行登录过滤
6， 对request和response进行了封装，对外提供业务方法
&nbsp;&nbsp;**public void execute(BaseServeltModel http)**
7， 对业务中的异常、错误登提供了可json化的输出信息
8， 实现了任意对象的REST json化

#使用方式
每需要产生接口时，只需要新建文件，其内容示例如下,访问:http://localhost:8080/sapi/user/queryuser (post userId=123)：


```
@WebServlet(urlPatterns = "/user/queryuser")
public class QueryUserAction extends BaseServlet {

	private static final long serialVersionUID = -53767802964589779L;

	@Override
	public void execute(BaseServletModel http) {

		try {
			String userId = http.getParam("userId");
			UserBean user = UserDAO.getInstance().findUserById(userId);
			http.print(JSON.toHTTPJSON(user));
		} catch (BaseHTTPBean e) {
			http.print(JSON.toJSONString(e));
		}
	}
}
```

大大的简化了业务外围逻辑处理、对外接口提供的处理等

支持环境：
jdk >= 5.0
tomcat >= 7.0
mysql >= 5.0

版权：归[https://github.com/qq644531343](https://github.com/qq644531343)所有。授权请联系:644531343@qq.com



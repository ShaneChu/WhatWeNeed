package shane.view.contrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;
import shane.view.helper.impl.RequestHelperImpl;


public class ControlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ControlServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// 使用request、response对象创建视图助手（请求代理）对象
		IRequestHelper helper = (IRequestHelper)new RequestHelperImpl(request, response);
		// 通过请求代理类获得处理该请求的Command
		ICommand cmd = helper.getCommand();
		// 执行该Command的处理。返回一个视图路径（path的取值取决于你Command代码的实现）
		String path = cmd.execute(helper);
		// 派发视图
		this.dispatch(request, response, path);

	}

	// 专门用来处理转发的方法
	protected void dispatch(HttpServletRequest request, 
	HttpServletResponse response, String page) 
	throws javax.servlet.ServletException, java.io.IOException {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void init() throws ServletException {}

}

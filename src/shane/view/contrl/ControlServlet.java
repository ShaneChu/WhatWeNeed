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
		// ʹ��request��response���󴴽���ͼ���֣������������
		IRequestHelper helper = (IRequestHelper)new RequestHelperImpl(request, response);
		// ͨ������������ô���������Command
		ICommand cmd = helper.getCommand();
		// ִ�и�Command�Ĵ�������һ����ͼ·����path��ȡֵȡ������Command�����ʵ�֣�
		String path = cmd.execute(helper);
		// �ɷ���ͼ
		this.dispatch(request, response, path);

	}

	// ר����������ת���ķ���
	protected void dispatch(HttpServletRequest request, 
	HttpServletResponse response, String page) 
	throws javax.servlet.ServletException, java.io.IOException {
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	public void init() throws ServletException {}

}

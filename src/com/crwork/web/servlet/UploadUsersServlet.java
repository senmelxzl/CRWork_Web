package com.crwork.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.crwork.web.poi.UsersExcelUtil;
import com.crwork.web.util.DateUtil;

/**
 * Servlet implementation class UploadLDServlet
 */
@WebServlet("/UploadUsersServlet")
public class UploadUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TAG = "UploadUsersServlet";
	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "/root/crwork/userfiles/upload";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadUsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止
			writer.println("Error:form must set enctype=multipart/form-data");
			writer.flush();
			return;
		}

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 中文处理
		upload.setHeaderEncoding("UTF-8");

		// 构造临时路径来存储上传的文件

		// 如果目录不存在则创建
		File uploadDir = new File(UPLOAD_DIRECTORY);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// 解析请求的内容提取文件数据
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						/*
						 * String filePath = UPLOAD_DIRECTORY + File.separator +
						 * DateUtil.getCurrentDate().toString() + "-" + fileName;
						 */
						String filePath = getServletContext().getRealPath("/") + File.separator
								+ DateUtil.getCurrentDate().toString() + "-" + fileName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(TAG + filePath);
						// 保存文件到硬盘
						String result = "";
						item.write(storeFile);
						System.out.println(TAG + " file path:" + filePath);
						List<String[]> listusers = UsersExcelUtil.getExcelData(filePath);
						String IsUploaded_message = "上传失败";
						for (String[] users : listusers) {
							System.out.println(
									TAG + " Excel result: " + users[0] + " " + users[1] + " " + users[2] + " ");
						}
						if (listusers != null && listusers.size() > 0) {
							IsUploaded_message = "上传成功";
						}
						HttpSession session = request.getSession();
						session.setAttribute("IsUploaded_message", IsUploaded_message);
						session.setAttribute("message", "users");
						session.setAttribute("listusers", listusers);
					}
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message", "Error:" + ex.getMessage());
		}
		response.sendRedirect(request.getContextPath() + "/crwork_bsm_forms/upload.jsp");
	}

}

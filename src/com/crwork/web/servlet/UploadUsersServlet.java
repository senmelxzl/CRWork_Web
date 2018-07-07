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
	// �ϴ��ļ��洢Ŀ¼
	private static final String UPLOAD_DIRECTORY = "/root/crwork/userfiles/upload";

	// �ϴ�����
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
			// ���������ֹͣ
			writer.println("Error:form must set enctype=multipart/form-data");
			writer.flush();
			return;
		}

		// �����ϴ�����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// ������ʱ�洢Ŀ¼
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// ��������ļ��ϴ�ֵ
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// �����������ֵ (�����ļ��ͱ�����)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// ���Ĵ���
		upload.setHeaderEncoding("UTF-8");

		// ������ʱ·�����洢�ϴ����ļ�

		// ���Ŀ¼�������򴴽�
		File uploadDir = new File(UPLOAD_DIRECTORY);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// ���������������ȡ�ļ�����
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// ����������
				for (FileItem item : formItems) {
					// �����ڱ��е��ֶ�
					if (!item.isFormField()) {
						String fileName = new File(item.getName()).getName();
						/*
						 * String filePath = UPLOAD_DIRECTORY + File.separator +
						 * DateUtil.getCurrentDate().toString() + "-" + fileName;
						 */
						String filePath = getServletContext().getRealPath("/") + File.separator
								+ DateUtil.getCurrentDate().toString() + "-" + fileName;
						File storeFile = new File(filePath);
						// �ڿ���̨����ļ����ϴ�·��
						System.out.println(TAG + filePath);
						// �����ļ���Ӳ��
						String result = "";
						item.write(storeFile);
						System.out.println(TAG + " file path:" + filePath);
						List<String[]> listusers = UsersExcelUtil.getExcelData(filePath);
						String IsUploaded_message = "�ϴ�ʧ��";
						for (String[] users : listusers) {
							System.out.println(
									TAG + " Excel result: " + users[0] + " " + users[1] + " " + users[2] + " ");
						}
						if (listusers != null && listusers.size() > 0) {
							IsUploaded_message = "�ϴ��ɹ�";
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

package com.eazow.blog.servlet.admin.album;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.eazow.blog.dao.factory.DAOFactory;
import com.eazow.blog.entity.Image;
import com.eazow.blog.service.ImageService;
import com.eazow.blog.util.ImageUtil;

@SuppressWarnings("serial")
public class UploadImagesAdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �ļ����·��
		String savePath = this.getServletContext().getRealPath("/") + "album/";

		File saveDir = new File(savePath);
		// Ŀ¼������
		if (!saveDir.isDirectory())
			saveDir.mkdir();

		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

			// �����ڴ滺��������ֵ,��λbyte
			diskFileItemFactory.setSizeThreshold(1024000);

			ServletFileUpload servletFileUpload = new ServletFileUpload(
					diskFileItemFactory);

			// �����ļ�������5M
			servletFileUpload.setFileSizeMax(5000000);
			// �ܴ�С������20M
			servletFileUpload.setSizeMax(20000000);

			InputStream is = null;
			OutputStream os = null;
			int albumId = 0;
			List<FileItem> fileItemList = null;
			String fileName = "";
			String lowResolutionFileName = "";
			String url = "";
			String lowResolutionUrl = "";
			try {
				fileItemList = servletFileUpload.parseRequest(request);

				for (FileItem fileItem : fileItemList) {
					if (fileItem.isFormField()) {
						if ("albumId".equals(fileItem.getFieldName())) {
							String albumIdStr = fileItem.getString();
							albumId = Integer.parseInt(albumIdStr);
						}
					} else {
						// ����ͼ
						fileName = fileItem.getName().trim();
						fileName = System.currentTimeMillis()
								+ fileName.substring(fileName.indexOf("."));
						lowResolutionFileName = fileName.substring(0, fileName
								.indexOf("."))
								+ "_low"
								+ fileName.substring(fileName.indexOf("."));
						url = "album/" + fileName;
						lowResolutionUrl = url.substring(0, url.indexOf("."))
								+ "_low"
								+ fileName.substring(fileName.indexOf("."));
						// ���������
						os = new FileOutputStream(savePath + fileName);

						// ��ȡ�ļ�д����
						is = fileItem.getInputStream();

						int length = 0;
						byte[] buffer = new byte[8192];
						while ((length = is.read(buffer, 0, 8192)) != -1) {
							os.write(buffer, 0, length);
						}

						ImageUtil.createThumbnail(savePath + fileName, savePath
								+ lowResolutionFileName, 200);

						// ��ͼƬurl�������ݿ�
						ImageService imageService = DAOFactory
								.getImageServiceInstance();
						Image image = new Image(fileName, url,
								lowResolutionUrl, albumId);
						imageService.addImage(image);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != os)
					os.close();
				if (null != is)
					is.close();
			}

		}
		response.sendRedirect("manageAlbumsAdminServlet");
	}
}
package com.eazow.blog.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eazow.blog.util.StringUtil;

@SuppressWarnings("serial")
public class GetVerificationCodeServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");

		int imageWidth = 50;
		int imageHeight = 20;
		BufferedImage image = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D g = image.createGraphics();

		g.fillRect(1, 1, imageWidth - 2, imageHeight - 2);
		g.setColor(new Color(70, 70, 70));
		g.drawRect(0, 0, imageWidth, imageHeight);
		g.setFont(new Font("Courier New", Font.BOLD, 18));

		g.setColor(Color.red);

		String randomStr = StringUtil.getRandomString(4);

		g.drawString(randomStr, 5, 15);

		g.setColor(this.getRandomColor());

		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int x1 = random.nextInt(90);
			int y1 = random.nextInt(90);
			int x2 = random.nextInt(90);
			int y2 = random.nextInt(90);

			g.drawLine(x1, y1, x2, y2);
		}

		g.dispose(); // 释放绘图所用的系统资源

		HttpSession session = request.getSession();
		session.setAttribute("verificationCode", randomStr);

		ImageIO.write(image, "JPEG", response.getOutputStream());
	}

	// 生成随机颜色
	private Color getRandomColor() {
		Random random = new Random();

		int red = random.nextInt(100) + 100;
		int green = random.nextInt(120) + 100;
		int blue = random.nextInt(90) + 100;

		return new Color(red, green, blue);
	}
}

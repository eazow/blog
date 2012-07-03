package com.eazow.blog.service.impl;

import java.util.List;

import com.eazow.blog.dao.MottoDAO;
import com.eazow.blog.entity.Motto;
import com.eazow.blog.service.MottoService;

public class MottoServiceImpl implements MottoService {
	private MottoDAO mottoDAO;

	private static MottoService mottoService;

	private MottoServiceImpl(MottoDAO mottoDAO) {
		this.mottoDAO = mottoDAO;
	}

	public static MottoService getMottoServiceInstance(MottoDAO mottoDAO) {
		if (null == mottoService)
			mottoService = new MottoServiceImpl(mottoDAO);
		return mottoService;
	}

	public List<Motto> getAllMottos() {
		return this.mottoDAO.getAllMottos();
	}

	public boolean addMotto(Motto motto) {
		Motto mottoTemp = this.mottoDAO.getMotto(motto.getContent());
		// motto“—¥Ê‘⁄
		if (null != mottoTemp) {
			return false;
		}
		return this.mottoDAO.addMotto(motto);
	}

	public Motto getRandomMotto() {
		return this.mottoDAO.getRandomMotto();
	}
}

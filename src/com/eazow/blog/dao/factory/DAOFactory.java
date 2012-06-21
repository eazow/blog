package com.eazow.blog.dao.factory;

import com.eazow.blog.dao.AdminDAO;
import com.eazow.blog.dao.AlbumDAO;
import com.eazow.blog.dao.ArticleArchiveDAO;
import com.eazow.blog.dao.ArticleDAO;
import com.eazow.blog.dao.CategoryDAO;
import com.eazow.blog.dao.CommentDAO;
import com.eazow.blog.dao.DraftDAO;
import com.eazow.blog.dao.ImageDAO;
import com.eazow.blog.dao.MessageDAO;
import com.eazow.blog.dao.MottoDAO;
import com.eazow.blog.dao.TagArticleDAO;
import com.eazow.blog.dao.TagDAO;
import com.eazow.blog.dao.VisitRecordDAO;
import com.eazow.blog.dao.impl.AdminDAOImpl;
import com.eazow.blog.dao.impl.AlbumDAOImpl;
import com.eazow.blog.dao.impl.ArticleArchiveDAOImpl;
import com.eazow.blog.dao.impl.ArticleDAOImpl;
import com.eazow.blog.dao.impl.CategoryDAOImpl;
import com.eazow.blog.dao.impl.CommentDAOImpl;
import com.eazow.blog.dao.impl.DraftDAOImpl;
import com.eazow.blog.dao.impl.ImageDAOImpl;
import com.eazow.blog.dao.impl.MessageDAOImpl;
import com.eazow.blog.dao.impl.MottoDAOImpl;
import com.eazow.blog.dao.impl.TagArticleDAOImpl;
import com.eazow.blog.dao.impl.TagDAOImpl;
import com.eazow.blog.dao.impl.VisitRecordDAOImpl;
import com.eazow.blog.service.AdminService;
import com.eazow.blog.service.AlbumService;
import com.eazow.blog.service.ArticleArchiveService;
import com.eazow.blog.service.ArticleService;
import com.eazow.blog.service.CategoryService;
import com.eazow.blog.service.CommentService;
import com.eazow.blog.service.DraftService;
import com.eazow.blog.service.ImageService;
import com.eazow.blog.service.MessageService;
import com.eazow.blog.service.MottoService;
import com.eazow.blog.service.TagService;
import com.eazow.blog.service.VisitRecordService;
import com.eazow.blog.service.impl.AdminServiceImpl;
import com.eazow.blog.service.impl.AlbumServiceImpl;
import com.eazow.blog.service.impl.ArticleArchiveServiceImpl;
import com.eazow.blog.service.impl.ArticleServiceImpl;
import com.eazow.blog.service.impl.CategoryServiceImpl;
import com.eazow.blog.service.impl.CommentServiceImpl;
import com.eazow.blog.service.impl.DraftServiceImpl;
import com.eazow.blog.service.impl.ImageServiceImpl;
import com.eazow.blog.service.impl.MessageServiceImpl;
import com.eazow.blog.service.impl.MottoServiceImpl;
import com.eazow.blog.service.impl.TagServiceImpl;
import com.eazow.blog.service.impl.VisitRecordServiceImpl;


public class DAOFactory
{
	//Article
	public static ArticleDAO getArticleDAOInstance() 
	{
		return ArticleDAOImpl.getArticleDAOInstance();
	}
	public static ArticleService getArticleServiceInstance()
	{
		return ArticleServiceImpl.getArticleServiceInstance(getArticleDAOInstance(), getCategoryDAOInstance(), 
				getCommentDAOInstance(), getArticleArchiveDAOInstance(), getTagDAOInstance(), getTagArticleDAOInstance());
	}
	//Category
	public static CategoryDAO getCategoryDAOInstance()
	{
		return CategoryDAOImpl.getCategoryDAOInstance();
	}
	public static CategoryService getCategoryServiceInstance()
	{
		return CategoryServiceImpl.getCategoryServiceInstance(getCategoryDAOInstance(), getArticleDAOInstance());
	}
	//Comment
	public static CommentDAO getCommentDAOInstance()
	{
		return CommentDAOImpl.getCommentDAOInstance();
	}
	public static CommentService getCommentServiceInstance()
	{
		return CommentServiceImpl.getCommentServiceInstance(getCommentDAOInstance(), getArticleDAOInstance());
	}
	//Admin
	public static AdminDAO getAdminDAOInstance()
	{
		return AdminDAOImpl.getAdminDAOInstance();
	}
	public static AdminService getAdminServiceInstance()
	{
		return AdminServiceImpl.getAdminServiceInstance(getAdminDAOInstance());
	}
	//Image
	public static ImageDAO getImageDAOInstance()
	{
		return ImageDAOImpl.getImageDAOInstance();
	}
	public static ImageService getImageServiceInstance()
	{
		return ImageServiceImpl.getImageServiceInstance(getImageDAOInstance(), getAlbumDAOInstance());
	}
	//Album
	public static AlbumDAO getAlbumDAOInstance()
	{
		return AlbumDAOImpl.getAlbumDAOInstance();
	}
	public static AlbumService getAlbumServiceInstance()
	{
		return AlbumServiceImpl.getAlbumServiceInstance(getAlbumDAOInstance(), getImageDAOInstance());
	}
	//VisitRecord
	public static VisitRecordDAO getVisitRecordDAOInstance()
	{
		return VisitRecordDAOImpl.getVisitRecordDAOInstance();
	}
	public static VisitRecordService getVisitRecordServiceInstance()
	{
		return VisitRecordServiceImpl.getVisitRecordService(getVisitRecordDAOInstance());
	}
	//ArticleArchive
	public static ArticleArchiveDAO getArticleArchiveDAOInstance()
	{
		return ArticleArchiveDAOImpl.getArticleArchiveDAOInstance();
	}
	public static ArticleArchiveService getArticleArchiveServiceInstance()
	{
		return ArticleArchiveServiceImpl.getArticleArchiveServiceInstance(getArticleArchiveDAOInstance(), getArticleDAOInstance());
	}
	//Draft
	public static DraftDAO getDraftDAOInstance()
	{
		return DraftDAOImpl.getDraftDAOInstance();
	}
	public static DraftService getDraftServiceInstance()
	{
		return DraftServiceImpl.getDraftServiceInstance(getDraftDAOInstance(), getCategoryDAOInstance());
	}
	//Motto
	public static MottoDAO getMottoDAOInstance()
	{
		return MottoDAOImpl.getMottoDAOInstance();
	}
	public static MottoService getMottoServiceInstance()
	{
		return MottoServiceImpl.getMottoServiceInstance(getMottoDAOInstance());
	}
	//Tag
	public static TagDAO getTagDAOInstance()
	{
		return TagDAOImpl.getTagDAOInstance();
	}
	public static TagService getTagServiceInstance()
	{
		return TagServiceImpl.getTagServiceInstance(getTagDAOInstance(), getTagArticleDAOInstance(), getArticleDAOInstance());
	}
	//TagArticle
	public static TagArticleDAO getTagArticleDAOInstance()
	{
		return TagArticleDAOImpl.getTagArticleDAOInstance();
	}
	//Message¡Ù—‘
	public static MessageDAO getMessageDAOInstance()
	{
		return MessageDAOImpl.getMessageDAOInstance();
	}
	public static MessageService getMessageServiceInstance()
	{
		return MessageServiceImpl.getMessageServiceImpl(getMessageDAOInstance());
	}
}

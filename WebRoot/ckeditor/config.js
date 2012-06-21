/*
Copyright (c) 2003-2009, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config ) 
{ 
// Define changes to default configuration here. For example: 
// config.language = 'fr'; 
// config.uiColor = 'AADC6E';
	config.language = 'zh-cn';  
	config.extraPlugins = 'code'; 
	config.height = 400;
	config.uiColor = '#14B8C4';
	config.skin = 'kama';
	config.toolbar = [
						 ['Source'],
						 ['Image'],
						 ['SpecialChar'],
						 ['FontSize'],
						 ['TextColor'],
						 ['Smiley'],
						 ['Code'],
						 ['UIColor']
					 ]
}; 
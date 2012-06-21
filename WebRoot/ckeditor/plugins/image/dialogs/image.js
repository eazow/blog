﻿
/*
Copyright (c) 2003-2009, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/
(function () {
	var a = 1, b = 2, c = 4, d = 8, e = /^\s*(\d+)((px)|\%)?\s*$/i, f = /(^\s*(\d+)((px)|\%)?\s*$)|^$/i, g = /^\d+px$/, h = function () {
		var q = this.getValue(), r = this.getDialog(), s = q.match(e);
		if (s) {
			if (s[2] == "%") {
				m(r, false);
			}
			q = s[1];
		}
		if (r.lockRatio) {
			var t = r.originalElement;
			if (t.getCustomData("isReady") == "true") {
				if (this.id == "txtHeight") {
					if (q && q != "0") {
						q = Math.round(t.$.width * (q / t.$.height));
					}
					if (!isNaN(q)) {
						r.setValueOf("info", "txtWidth", q);
					}
				} else {
					if (q && q != "0") {
						q = Math.round(t.$.height * (q / t.$.width));
					}
					if (!isNaN(q)) {
						r.setValueOf("info", "txtHeight", q);
					}
				}
			}
		}
		i(r);
	}, i = function (q) {
		if (!q.originalElement || !q.preview) {
			return 1;
		}
		q.commitContent(c, q.preview);
		return 0;
	};
	function j() {
		var q = arguments, r = this.getContentElement("advanced", "txtdlgGenStyle");
		r && r.commit.apply(r, q);
		this.foreach(function (s) {
			if (s.commit && s.id != "txtdlgGenStyle") {
				s.commit.apply(s, q);
			}
		});
	}
	var k;
	function l(q) {
		if (k) {
			return;
		}
		k = 1;
		var r = this.getDialog(), s = r.imageElement;
		if (s) {
			this.commit(a, s);
			q = [].concat(q);
			var t = q.length, u;
			for (var v = 0; v < t; v++) {
				u = r.getContentElement.apply(r, q[v].split(":"));
				u && u.setup(a, s);
			}
		}
		k = 0;
	}
	var m = function (q, r) {
		var s = q.originalElement, t = CKEDITOR.document.getById("btnLockSizes");
		if (s.getCustomData("isReady") == "true") {
			if (r == "check") {
				var u = q.getValueOf("info", "txtWidth"), v = q.getValueOf("info", "txtHeight"), w = s.$.width * 1000 / s.$.height, x = u * 1000 / v;
				q.lockRatio = false;
				if (!u && !v) {
					q.lockRatio = true;
				} else {
					if (!isNaN(w) && !isNaN(x)) {
						if (Math.round(w) == Math.round(x)) {
							q.lockRatio = true;
						}
					}
				}
			} else {
				if (r != undefined) {
					q.lockRatio = r;
				} else {
					q.lockRatio = !q.lockRatio;
				}
			}
		} else {
			if (r != "check") {
				q.lockRatio = false;
			}
		}
		if (q.lockRatio) {
			t.removeClass("cke_btn_unlocked");
		} else {
			t.addClass("cke_btn_unlocked");
		}
		return q.lockRatio;
	}, n = function (q) {
		var r = q.originalElement;
		if (r.getCustomData("isReady") == "true") {
			q.setValueOf("info", "txtWidth", r.$.width);
			q.setValueOf("info", "txtHeight", r.$.height);
		}
		i(q);
	}, o = function (q, r) {
		if (q != a) {
			return;
		}
		function s(x, y) {
			var z = x.match(e);
			if (z) {
				if (z[2] == "%") {
					z[1] += "%";
					m(t, false);
				}
				return z[1];
			}
			return y;
		}
		var t = this.getDialog(), u = "", v = this.id == "txtWidth" ? "width" : "height", w = r.getAttribute(v);
		if (w) {
			u = s(w, u);
		}
		u = s(r.getStyle(v), u);
		this.setValue(u);
	}, p = function (q, r) {
		var s = function () {
			var v = this;
			var u = v.originalElement;
			u.setCustomData("isReady", "true");
			u.removeListener("load", s);
			u.removeListener("error", t);
			u.removeListener("abort", t);
			CKEDITOR.document.getById("ImagePreviewLoader").setStyle("display", "none");
			if (!v.dontResetSize) {
				n(v);
			}
			if (v.firstLoad) {
				m(v, "check");
			}
			v.firstLoad = false;
			v.dontResetSize = false;
		}, t = function () {
			var w = this;
			var u = w.originalElement;
			u.removeListener("load", s);
			u.removeListener("error", t);
			u.removeListener("abort", t);
			var v = CKEDITOR.getUrl(q.skinPath + "images/noimage.png");
			if (w.preview) {
				w.preview.setAttribute("src", v);
			}
			CKEDITOR.document.getById("ImagePreviewLoader").setStyle("display", "none");
			m(w, false);
		};
		return {
			title:r == "image" ? q.lang.image.title : q.lang.image.titleButton, minWidth:420, minHeight:310, 
			onShow:function () {
			var A = this;
			A.imageElement = false;
			A.linkElement = false;
			A.imageEditMode = false;
			A.linkEditMode = false;
			A.lockRatio = true;
			A.dontResetSize = false;
			A.firstLoad = true;
			A.addLink = false;
			CKEDITOR.document.getById("ImagePreviewLoader").setStyle("display", "none");
			A.preview = CKEDITOR.document.getById("previewImage");
			var u = A.getParentEditor(), v = A.getParentEditor().getSelection(), w = v.getSelectedElement(), x = w && w.getAscendant("a");
			A.originalElement = u.document.createElement("img");
			A.originalElement.setAttribute("alt", "");
			A.originalElement.setCustomData("isReady", "false");
			if (x) {
				A.linkElement = x;
				A.linkEditMode = true;
				var y = x.getChildren();
				if (y.count() == 1) {
					var z = y.getItem(0).getName();
					if (z == "img" || z == "input") {
						A.imageElement = y.getItem(0);
						if (A.imageElement.getName() == "img") {
							A.imageEditMode = "img";
						} else {
							if (A.imageElement.getName() == "input") {
								A.imageEditMode = "input";
							}
						}
					}
				}
				if (r == "image") {
					A.setupContent(b, x);
				}
			}
			if (w && w.getName() == "img" && !w.getAttribute("_cke_realelement") || w && w.getName() == "input" && w.getAttribute("type") == "image") {
				A.imageEditMode = w.getName();
				A.imageElement = w;
			}
			if (A.imageEditMode) {
				A.cleanImageElement = A.imageElement;
				A.imageElement = A.cleanImageElement.clone(true, true);
				A.setupContent(a, A.imageElement);
				m(A, true);
			} else {
				A.imageElement = u.document.createElement("img");
			}
			if (!CKEDITOR.tools.trim(A.getValueOf("info", "txtUrl"))) {
				A.preview.removeAttribute("src");
				A.preview.setStyle("display", "none");
			}
		}, onOk:function () {
			var v = this;
			if (v.imageEditMode) {
				var u = v.imageEditMode;
				if (r == "image" && u == "input" && confirm(q.lang.image.button2Img)) {
					u = "img";
					v.imageElement = q.document.createElement("img");
					v.imageElement.setAttribute("alt", "");
					q.insertElement(v.imageElement);
				} else {
					if (r != "image" && u == "img" && confirm(q.lang.image.img2Button)) {
						u = "input";
						v.imageElement = q.document.createElement("input");
						v.imageElement.setAttributes({type:"image", alt:""});
						q.insertElement(v.imageElement);
					} else {
						v.imageElement = v.cleanImageElement;
						delete v.cleanImageElement;
					}
				}
			} else {
				if (r == "image") {
					v.imageElement = q.document.createElement("img");
				} else {
					v.imageElement = q.document.createElement("input");
					v.imageElement.setAttribute("type", "image");
				}
				v.imageElement.setAttribute("alt", "");
			}
			if (!v.linkEditMode) {
				v.linkElement = q.document.createElement("a");
			}
			v.commitContent(a, v.imageElement);
			v.commitContent(b, v.linkElement);
			if (!v.imageElement.getAttribute("style")) {
				v.imageElement.removeAttribute("style");
			}
			if (!v.imageEditMode) {
				if (v.addLink) {
					if (!v.linkEditMode) {
						q.insertElement(v.linkElement);
						v.linkElement.append(v.imageElement, false);
					} else {
						q.insertElement(v.imageElement);
					}
				} else {
					q.insertElement(v.imageElement);
				}
			} else {
				if (!v.linkEditMode && v.addLink) {
					q.insertElement(v.linkElement);
					v.imageElement.appendTo(v.linkElement);
				} else {
					if (v.linkEditMode && !v.addLink) {
						q.getSelection().selectElement(v.linkElement);
						q.insertElement(v.imageElement);
					}
				}
			}
		}, onLoad:function () {
			var v = this;
			if (r != "image") {
				v.hidePage("Link");
			}
			var u = v._.element.getDocument();
			v.addFocusable(u.getById("btnResetSize"), 5);
			v.addFocusable(u.getById("btnLockSizes"), 5);
			v.commitContent = j;
		}, onHide:function () {
			var u = this;
			if (u.preview) {
				u.commitContent(d, u.preview);
			}
			if (u.originalElement) {
				u.originalElement.removeListener("load", s);
				u.originalElement.removeListener("error", t);
				u.originalElement.removeListener("abort", t);
				u.originalElement.remove();
				u.originalElement = false;
			}
			delete u.imageElement;
		}, contents:[{id:"info", label:q.lang.image.infoTab, accessKey:"I", elements:[{type:"vbox", padding:0, children:[{type:"html", html:"<span>" + CKEDITOR.tools.htmlEncode(q.lang.image.url) + "</span>"}, {type:"hbox", widths:["280px", "110px"], align:"right", children:[{id:"txtUrl", type:"text", label:"", onChange:function () {
			var u = this.getDialog(), v = this.getValue();
			if (v.length > 0) {
				u = this.getDialog();
				var w = u.originalElement;
				u.preview.removeStyle("display");
				w.setCustomData("isReady", "false");
				var x = CKEDITOR.document.getById("ImagePreviewLoader");
				if (x) {
					x.setStyle("display", "");
				}
				w.on("load", s, u);
				w.on("error", t, u);
				w.on("abort", t, u);
				w.setAttribute("src", v);
				u.preview.setAttribute("src", v);
				i(u);
			} else {
				if (u.preview) {
					u.preview.removeAttribute("src");
					u.preview.setStyle("display", "none");
				}
			}
		}, setup:function (u, v) {
			if (u == a) {
				var w = v.getAttribute("_cke_saved_src") || v.getAttribute("src"), x = this;
				this.getDialog().dontResetSize = true;
				x.setValue(w);
				x.setInitValue();
				x.focus();
			}
		}, commit:function (u, v) {
			var w = this;
			if (u == a && (w.getValue() || w.isChanged())) {
				v.setAttribute("_cke_saved_src", decodeURI(w.getValue()));
				v.setAttribute("src", decodeURI(w.getValue()));
			} else {
				if (u == d) {
					v.setAttribute("src", "");
					v.removeAttribute("src");
				}
			}
		}, validate:CKEDITOR.dialog.validate.notEmpty(q.lang.image.urlMissing)}, {type:"button", id:"browse", align:"center", label:q.lang.common.browseServer, hidden:true, filebrowser:"info:txtUrl"}]}]}, {id:"txtAlt", type:"text", label:q.lang.image.alt, accessKey:"A", "default":"", onChange:function () {
			i(this.getDialog());
		}, setup:function (u, v) {
			if (u == a) {
				this.setValue(v.getAttribute("alt"));
			}
		}, commit:function (u, v) {
			var w = this;
			if (u == a) {
				if (w.getValue() || w.isChanged()) {
					v.setAttribute("alt", w.getValue());
				}
			} else {
				if (u == c) {
					v.setAttribute("alt", w.getValue());
				} else {
					if (u == d) {
						v.removeAttribute("alt");
					}
				}
			}
		}}, {type:"hbox", widths:["140px", "240px"], children:[{type:"vbox", padding:10, children:[{type:"hbox", widths:["70%", "30%"], children:[{type:"vbox", padding:1, children:[{type:"text", width:"40px", id:"txtWidth", labelLayout:"horizontal", label:q.lang.image.width, onKeyUp:h, onChange:function () {
			l.call(this, "advanced:txtdlgGenStyle");
		}, validate:function () {
			var u = this.getValue().match(f);
			if (!u) {
				alert(q.lang.common.validateNumberFailed);
			}
			return !!u;
		}, setup:o, commit:function (u, v, w) {
			var x = this.getValue();
			if (u == a) {
				if (x) {
					v.setStyle("width", CKEDITOR.tools.cssLength(x));
				} else {
					if (!x && this.isChanged()) {
						v.removeStyle("width");
					}
				}
				!w && v.removeAttribute("width");
			} else {
				if (u == c) {
					var y = x.match(e);
					if (!y) {
						var z = this.getDialog().originalElement;
						if (z.getCustomData("isReady") == "true") {
							v.setStyle("width", z.$.width + "px");
						}
					} else {
						v.setStyle("width", x + "px");
					}
				} else {
					if (u == d) {
						v.removeAttribute("width");
						v.removeStyle("width");
					}
				}
			}
		}}, {type:"text", id:"txtHeight", width:"40px", labelLayout:"horizontal", label:q.lang.image.height, onKeyUp:h, onChange:function () {
			l.call(this, "advanced:txtdlgGenStyle");
		}, validate:function () {
			var u = this.getValue().match(f);
			if (!u) {
				alert(q.lang.common.validateNumberFailed);
			}
			return !!u;
		}, setup:o, commit:function (u, v, w) {
			var x = this.getValue();
			if (u == a) {
				if (x) {
					v.setStyle("height", CKEDITOR.tools.cssLength(x));
				} else {
					if (!x && this.isChanged()) {
						v.removeStyle("height");
					}
				}
				if (!w && u == a) {
					v.removeAttribute("height");
				}
			} else {
				if (u == c) {
					var y = x.match(e);
					if (!y) {
						var z = this.getDialog().originalElement;
						if (z.getCustomData("isReady") == "true") {
							v.setStyle("height", z.$.height + "px");
						}
					} else {
						v.setStyle("height", x + "px");
					}
				} else {
					if (u == d) {
						v.removeAttribute("height");
						v.removeStyle("height");
					}
				}
			}
		}}]}, {type:"html", style:"margin-top:10px;width:40px;height:40px;", onLoad:function () {
			var u = CKEDITOR.document.getById("btnResetSize"), v = CKEDITOR.document.getById("btnLockSizes");
			if (u) {
				u.on("click", function () {
					n(this);
				}, this.getDialog());
				u.on("mouseover", function () {
					this.addClass("cke_btn_over");
				}, u);
				u.on("mouseout", function () {
					this.removeClass("cke_btn_over");
				}, u);
			}
			if (v) {
				v.on("click", function () {
					var A = this;
					var w = m(A), x = A.originalElement, y = A.getValueOf("info", "txtWidth");
					if (x.getCustomData("isReady") == "true" && y) {
						var z = x.$.height / x.$.width * y;
						if (!isNaN(z)) {
							A.setValueOf("info", "txtHeight", Math.round(z));
							i(A);
						}
					}
				}, this.getDialog());
				v.on("mouseover", function () {
					this.addClass("cke_btn_over");
				}, v);
				v.on("mouseout", function () {
					this.removeClass("cke_btn_over");
				}, v);
			}
		}, html:"<div><a href=\"javascript:void(0)\" tabindex=\"-1\" title=\"" + q.lang.image.lockRatio + "\" class=\"cke_btn_locked\" id=\"btnLockSizes\"></a>" + "<a href=\"javascript:void(0)\" tabindex=\"-1\" title=\"" + q.lang.image.resetSize + "\" class=\"cke_btn_reset\" id=\"btnResetSize\"></a>" + "</div>"}]}, {type:"vbox", padding:1, children:[{type:"text", id:"txtBorder", width:"60px", labelLayout:"horizontal", label:q.lang.image.border, "default":"", onKeyUp:function () {
			i(this.getDialog());
		}, onChange:function () {
			l.call(this, "advanced:txtdlgGenStyle");
		}, validate:function () {
			var u = CKEDITOR.dialog.validate.integer(q.lang.common.validateNumberFailed);
			return u.apply(this);
		}, setup:function (u, v) {
			if (u == a) {
				var w, x = v.getStyle("border-width");
				x = x && x.match(/^(\d+px)(?: \1 \1 \1)?$/);
				w = x && parseInt(x[1], 10);
				!w && (w = v.getAttribute("border"));
				this.setValue(w);
			}
		}, commit:function (u, v, w) {
			var x = parseInt(this.getValue(), 10);
			if (u == a || u == c) {
				if (x) {
					v.setStyle("border-width", CKEDITOR.tools.cssLength(x));
					v.setStyle("border-style", "solid");
				} else {
					if (!x && this.isChanged()) {
						v.removeStyle("border-width");
						v.removeStyle("border-style");
						v.removeStyle("border-color");
					}
				}
				if (!w && u == a) {
					v.removeAttribute("border");
				}
			} else {
				if (u == d) {
					v.removeAttribute("border");
					v.removeStyle("border-width");
					v.removeStyle("border-style");
					v.removeStyle("border-color");
				}
			}
		}}, {type:"text", id:"txtHSpace", width:"60px", labelLayout:"horizontal", label:q.lang.image.hSpace, "default":"", onKeyUp:function () {
			i(this.getDialog());
		}, onChange:function () {
			l.call(this, "advanced:txtdlgGenStyle");
		}, validate:function () {
			var u = CKEDITOR.dialog.validate.integer(q.lang.common.validateNumberFailed);
			return u.apply(this);
		}, setup:function (u, v) {
			if (u == a) {
				var w, x, y, z = v.getStyle("margin-left"), A = v.getStyle("margin-right");
				z = z && z.match(g);
				A = A && A.match(g);
				x = parseInt(z, 10);
				y = parseInt(A, 10);
				w = x == y && x;
				!w && (w = v.getAttribute("hspace"));
				this.setValue(w);
			}
		}, commit:function (u, v, w) {
			var x = parseInt(this.getValue(), 10);
			if (u == a || u == c) {
				if (x) {
					v.setStyle("margin-left", CKEDITOR.tools.cssLength(x));
					v.setStyle("margin-right", CKEDITOR.tools.cssLength(x));
				} else {
					if (!x && this.isChanged()) {
						v.removeStyle("margin-left");
						v.removeStyle("margin-right");
					}
				}
				if (!w && u == a) {
					v.removeAttribute("hspace");
				}
			} else {
				if (u == d) {
					v.removeAttribute("hspace");
					v.removeStyle("margin-left");
					v.removeStyle("margin-right");
				}
			}
		}}, {type:"text", id:"txtVSpace", width:"60px", labelLayout:"horizontal", label:q.lang.image.vSpace, "default":"", onKeyUp:function () {
			i(this.getDialog());
		}, onChange:function () {
			l.call(this, "advanced:txtdlgGenStyle");
		}, validate:function () {
			var u = CKEDITOR.dialog.validate.integer(q.lang.common.validateNumberFailed);
			return u.apply(this);
		}, setup:function (u, v) {
			if (u == a) {
				var w, x, y, z = v.getStyle("margin-top"), A = v.getStyle("margin-bottom");
				z = z && z.match(g);
				A = A && A.match(g);
				x = parseInt(z, 10);
				y = parseInt(A, 10);
				w = x == y && x;
				!w && (w = v.getAttribute("vspace"));
				this.setValue(w);
			}
		}, commit:function (u, v, w) {
			var x = parseInt(this.getValue(), 10);
			if (u == a || u == c) {
				if (x) {
					v.setStyle("margin-top", CKEDITOR.tools.cssLength(x));
					v.setStyle("margin-bottom", CKEDITOR.tools.cssLength(x));
				} else {
					if (!x && this.isChanged()) {
						v.removeStyle("margin-top");
						v.removeStyle("margin-bottom");
					}
				}
				if (!w && u == a) {
					v.removeAttribute("vspace");
				}
			} else {
				if (u == d) {
					v.removeAttribute("vspace");
					v.removeStyle("margin-top");
					v.removeStyle("margin-bottom");
				}
			}
		}}, {id:"cmbAlign", type:"select", labelLayout:"horizontal", widths:["35%", "65%"], style:"width:90px", label:q.lang.image.align, "default":"", items:[[q.lang.common.notSet, ""], [q.lang.image.alignLeft, "left"], [q.lang.image.alignRight, "right"]], onChange:function () {
			i(this.getDialog());
			l.call(this, "advanced:txtdlgGenStyle");
		}, setup:function (u, v) {
			if (u == a) {
				var w = v.getStyle("float");
				switch (w) {
				  case "inherit":
				  case "none":
					w = "";
				}
				!w && (w = (v.getAttribute("align") || "").toLowerCase());
				this.setValue(w);
			}
		}, commit:function (u, v, w) {
			var x = this.getValue();
			if (u == a || u == c) {
				if (x) {
					v.setStyle("float", x);
				} else {
					if (!x && this.isChanged()) {
						v.removeStyle("float");
					}
				}
				if (!w && u == a) {
					x = (v.getAttribute("align") || "").toLowerCase();
					switch (x) {
					  case "left":
					  case "right":
						v.removeAttribute("align");
					}
				}
			} else {
				if (u == d) {
					v.removeStyle("float");
				}
			}
		}}]}]}, {type:"vbox", height:"250px", children:[{type:"html", style:"width:95%;", html:"<div>" + CKEDITOR.tools.htmlEncode(q.lang.image.preview) + "<br>" + "<div id=\"ImagePreviewLoader\" style=\"display:none\"><div class=\"loading\">&nbsp;</div></div>" + "<div id=\"ImagePreviewBox\">" + "<a href=\"javascript:void(0)\" target=\"_blank\" onclick=\"return false;\" id=\"previewLink\">" + "<img id=\"previewImage\" src=\"\" alt=\"\" /></a>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. " + "Maecenas feugiat consequat diam. Maecenas metus. Vivamus diam purus, cursus a, commodo non, facilisis vitae, " + "nulla. Aenean dictum lacinia tortor. Nunc iaculis, nibh non iaculis aliquam, orci felis euismod neque, sed ornare massa mauris sed velit. Nulla pretium mi et risus. Fusce mi pede, tempor id, cursus ac, ullamcorper nec, enim. Sed tortor. Curabitur molestie. Duis velit augue, condimentum at, ultrices a, luctus ut, orci. Donec pellentesque egestas eros. Integer cursus, augue in cursus faucibus, eros pede bibendum sem, in tempus tellus justo quis ligula. Etiam eget tortor. Vestibulum rutrum, est ut placerat elementum, lectus nisl aliquam velit, tempor aliquam eros nunc nonummy metus. In eros metus, gravida a, gravida sed, lobortis id, turpis. Ut ultrices, ipsum at venenatis fringilla, sem nulla lacinia tellus, eget aliquet turpis mauris non enim. Nam turpis. Suspendisse lacinia. Curabitur ac tortor ut ipsum egestas elementum. Nunc imperdiet gravida mauris." + "</div>" + "</div>"}]}]}]}, {id:"Link", label:q.lang.link.title, padding:0, elements:[{id:"txtUrl", type:"text", label:q.lang.image.url, style:"width: 100%", "default":"", setup:function (u, v) {
			if (u == b) {
				var w = v.getAttribute("_cke_saved_href");
				if (!w) {
					w = v.getAttribute("href");
				}
				this.setValue(w);
			}
		}, commit:function (u, v) {
			var w = this;
			if (u == b) {
				if (w.getValue() || w.isChanged()) {
					v.setAttribute("_cke_saved_href", decodeURI(w.getValue()));
					v.setAttribute("href", "javascript:void(0)/*" + CKEDITOR.tools.getNextNumber() + "*/");
					if (w.getValue() || !q.config.image_removeLinkByEmptyURL) {
						w.getDialog().addLink = true;
					}
				}
			}
		}}, {type:"button", id:"browse", filebrowser:"Link:txtUrl", style:"float:right", hidden:true, label:q.lang.common.browseServer}, {id:"cmbTarget", type:"select", label:q.lang.link.target, "default":"", items:[[q.lang.link.targetNotSet, ""], [q.lang.link.targetNew, "_blank"], [q.lang.link.targetTop, "_top"], [q.lang.link.targetSelf, "_self"], [q.lang.link.targetParent, "_parent"]], setup:function (u, v) {
			if (u == b) {
				this.setValue(v.getAttribute("target"));
			}
		}, commit:function (u, v) {
			if (u == b) {
				if (this.getValue() || this.isChanged()) {
					v.setAttribute("target", this.getValue());
				}
			}
		}}]}, {id:"Upload", hidden:true, filebrowser:"uploadButton", label:q.lang.image.upload, elements:[{type:"file", id:"upload", label:q.lang.image.btnUpload, style:"height:40px", size:38}, {type:"fileButton", id:"uploadButton", filebrowser:"info:txtUrl", label:q.lang.image.btnUpload, "for":["Upload", "upload"]}]}, {id:"advanced", label:q.lang.common.advancedTab, elements:[{type:"hbox", widths:["50%", "25%", "25%"], children:[{type:"text", id:"linkId", label:q.lang.common.id, setup:function (u, v) {
			if (u == a) {
				this.setValue(v.getAttribute("id"));
			}
		}, commit:function (u, v) {
			if (u == a) {
				if (this.getValue() || this.isChanged()) {
					v.setAttribute("id", this.getValue());
				}
			}
		}}, {id:"cmbLangDir", type:"select", style:"width : 100px;", label:q.lang.common.langDir, "default":"", items:[[q.lang.common.notSet, ""], [q.lang.common.langDirLtr, "ltr"], [q.lang.common.langDirRtl, "rtl"]], setup:function (u, v) {
			if (u == a) {
				this.setValue(v.getAttribute("dir"));
			}
		}, commit:function (u, v) {
			if (u == a) {
				if (this.getValue() || this.isChanged()) {
					v.setAttribute("dir", this.getValue());
				}
			}
		}}, {type:"text", id:"txtLangCode", label:q.lang.common.langCode, "default":"", setup:function (u, v) {
			if (u == a) {
				this.setValue(v.getAttribute("lang"));
			}
		}, commit:function (u, v) {
			if (u == a) {
				if (this.getValue() || this.isChanged()) {
					v.setAttribute("lang", this.getValue());
				}
			}
		}}]}, {type:"text", id:"txtGenLongDescr", label:q.lang.common.longDescr, setup:function (u, v) {
			if (u == a) {
				this.setValue(v.getAttribute("longDesc"));
			}
		}, commit:function (u, v) {
			if (u == a) {
				if (this.getValue() || this.isChanged()) {
					v.setAttribute("longDesc", this.getValue());
				}
			}
		}}, {type:"hbox", widths:["50%", "50%"], children:[{type:"text", id:"txtGenClass", label:q.lang.common.cssClass, "default":"", setup:function (u, v) {
			if (u == a) {
				this.setValue(v.getAttribute("class"));
			}
		}, commit:function (u, v) {
			if (u == a) {
				if (this.getValue() || this.isChanged()) {
					v.setAttribute("class", this.getValue());
				}
			}
		}}, {type:"text", id:"txtGenTitle", label:q.lang.common.advisoryTitle, "default":"", onChange:function () {
			i(this.getDialog());
		}, setup:function (u, v) {
			if (u == a) {
				this.setValue(v.getAttribute("title"));
			}
		}, commit:function (u, v) {
			var w = this;
			if (u == a) {
				if (w.getValue() || w.isChanged()) {
					v.setAttribute("title", w.getValue());
				}
			} else {
				if (u == c) {
					v.setAttribute("title", w.getValue());
				} else {
					if (u == d) {
						v.removeAttribute("title");
					}
				}
			}
		}}]}, {type:"text", id:"txtdlgGenStyle", label:q.lang.common.cssStyle, "default":"", setup:function (u, v) {
			if (u == a) {
				var w = v.getAttribute("style");
				if (!w && v.$.style.cssText) {
					w = v.$.style.cssText;
				}
				this.setValue(w);
				var x = v.$.style.height, y = v.$.style.width, z = (x ? x : "").match(e), A = (y ? y : "").match(e);
				this.attributesInStyle = {height:!!z, width:!!A};
			}
		}, onChange:function () {
			l.call(this, ["info:cmbFloat", "info:cmbAlign", "info:txtVSpace", "info:txtHSpace", "info:txtBorder", "info:txtWidth", "info:txtHeight"]);
			i(this);
		}, commit:function (u, v) {
			if (u == a && (this.getValue() || this.isChanged())) {
				v.setAttribute("style", this.getValue());
			}
		}}]}]};
	};
	CKEDITOR.dialog.add("image", function (q) {
		return p(q, "image");
	});
	CKEDITOR.dialog.add("imagebutton", function (q) {
		return p(q, "imagebutton");
	});
})();

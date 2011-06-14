KISSY.add("ua", function(a) {
	var o = navigator.userAgent, m = "", e = "", d, f = {}, i = function(b) {
		var n = 0;
		return parseFloat(b.replace(/\./g, function() {
			return n++ === 0 ? "." : ""
		}))
	};
	if ((d = o.match(/AppleWebKit\/([\d.]*)/)) && d[1]) {
		f[m = "webkit"] = i(d[1]);
		if ((d = o.match(/Chrome\/([\d.]*)/)) && d[1])
			f[e = "chrome"] = i(d[1]);
		else if ((d = o.match(/\/([\d.]*) Safari/)) && d[1])
			f[e = "safari"] = i(d[1]);
		if (/ Mobile\//.test(o))
			f.mobile = "apple";
		else if (d = o.match(/NokiaN[^\/]*|Android \d\.\d|webOS\/\d\.\d/))
			f.mobile = d[0].toLowerCase()
	} else if ((d = o.match(/Presto\/([\d.]*)/)) && d[1]) {
		f[m = "presto"] = i(d[1]);
		if ((d = o.match(/Opera\/([\d.]*)/)) && d[1]) {
			f[e = "opera"] = i(d[1]);
			if ((d = o.match(/Opera\/.* Version\/([\d.]*)/)) && d[1])
				f[e] = i(d[1]);
			if ((d = o.match(/Opera Mini[^;]*/)) && d)
				f.mobile = d[0].toLowerCase();
			else if ((d = o.match(/Opera Mobi[^;]*/)) && d)
				f.mobile = d[0]
		}
	} else if ((d = o.match(/MSIE\s([^;]*)/)) && d[1]) {
		f[m = "trident"] = 0.1;
		f[e = "ie"] = i(d[1]);
		if ((d = o.match(/Trident\/([\d.]*)/)) && d[1])
			f[m] = i(d[1])
	} else if (d = o.match(/Gecko/)) {
		f[m = "gecko"] = 0.1;
		if ((d = o.match(/rv:([\d.]*)/)) && d[1])
			f[m] = i(d[1]);
		if ((d = o.match(/Firefox\/([\d.]*)/)) && d[1])
			f[e = "firefox"] = i(d[1])
	}
	f.core = m;
	f.shell = e;
	f._numberify = i;
	a.UA = f
});
KISSY.add("ua-extra", function(a) {
	var o = a.UA, m = navigator.userAgent, e, d, f = {}, i = o._numberify;
	if (m.match(/360SE/))
		f[d = "se360"] = 3;
	else if (m.match(/Maxthon/) && (e = window.external)) {
		d = "maxthon";
		try {
			f[d] = i(e.max_version)
		} catch (b) {
			f[d] = 0.1
		}
	} else if (e = m.match(/TencentTraveler\s([\d.]*)/))
		f[d = "tt"] = e[1] ? i(e[1]) : 0.1;
	else if (m.match(/TheWorld/))
		f[d = "theworld"] = 3;
	else if (e = m.match(/SE\s([\d.]*)/))
		f[d = "sougou"] = e[1] ? i(e[1]) : 0.1;
	d && (f.shell = d);
	a.mix(o, f)
});
KISSY.add("dom", function(a, o) {
	function m(e, d) {
		return e && e.nodeType === d
	}
	a.DOM = {
		_isElementNode : function(e) {
			return m(e, 1)
		},
		_isKSNode : function(e) {
			return a.Node && m(e, a.Node.TYPE)
		},
		_getWin : function(e) {
			return e && "scrollTo" in e && e.document ? e
					: m(e, 9) ? e.defaultView || e.parentWindow
							: e === o ? window : false
		},
		_nodeTypeIs : m
	}
});
KISSY
		.add(
				"selector",
				function(a, o) {
					function m(g, c) {
						var j, h, k = [], s;
						c = e(c);
						if (a.isString(g)) {
							g = a.trim(g);
							if (r.test(g)) {
								if (h = d(g.slice(1), c))
									k = [ h ]
							} else if (j = p.exec(g)) {
								h = j[1];
								s = j[2];
								j = j[3];
								if (c = h ? d(h, c) : c)
									if (j)
										if (!h || g.indexOf(q) !== -1)
											k = i(j, s, c);
										else {
											if ((h = d(h, c))
													&& v.hasClass(h, j))
												k = [ h ]
										}
									else if (s)
										k = f(s, c)
							} else if (a.ExternalSelector)
								return a.ExternalSelector(g, c);
							else
								b(g)
						} else if (g && (g[y] || g[t]))
							k = g[y] ? [ g[y]() ] : g[t]();
						else if (g
								&& (a.isArray(g) || g && !g.nodeType && g.item
										&& g != window))
							k = g;
						else if (g)
							k = [ g ];
						if (k && !k.nodeType && k.item && k != window)
							k = a.makeArray(k);
						k.each = function(z, l) {
							return a.each(k, z, l)
						};
						return k
					}
					function e(g) {
						if (g === o)
							g = n;
						else if (a.isString(g) && r.test(g))
							g = d(g.slice(1), n);
						else if (g && g.nodeType !== 1 && g.nodeType !== 9)
							g = null;
						return g
					}
					function d(g, c) {
						if (c.nodeType !== 9)
							c = c.ownerDocument;
						return c.getElementById(g)
					}
					function f(g, c) {
						return c.getElementsByTagName(g)
					}
					function i(g, c, j) {
						j = g = j.getElementsByClassName(g);
						var h = 0, k = 0, s = g.length, z;
						if (c && c !== u) {
							j = [];
							for (c = c.toUpperCase(); h < s; ++h) {
								z = g[h];
								if (z.tagName === c)
									j[k++] = z
							}
						}
						return j
					}
					function b(g) {
						a.error("Unsupported selector: " + g)
					}
					var n = document, v = a.DOM, q = " ", u = "*", y = "getDOMNode", t = y
							+ "s", r = /^#[\w-]+$/, p = /^(?:#([\w-]+))?\s*([\w-]+|\*)?\.?([\w-]+)?$/;
					(function() {
						var g = n.createElement("div");
						g.appendChild(n.createComment(""));
						if (g.getElementsByTagName(u).length > 0)
							f = function(c, j) {
								var h = j.getElementsByTagName(c);
								if (c === u) {
									for ( var k = [], s = 0, z = 0, l; l = h[s++];)
										if (l.nodeType === 1)
											k[z++] = l;
									h = k
								}
								return h
							}
					})();
					n.getElementsByClassName
							|| (i = n.querySelectorAll ? function(g, c, j) {
								return j.querySelectorAll((c ? c : "") + "."
										+ g)
							} : function(g, c, j) {
								c = j.getElementsByTagName(c || u);
								j = [];
								var h = 0, k = 0, s = c.length, z, l;
								for (g = q + g + q; h < s; ++h) {
									z = c[h];
									if ((l = z.className)
											&& (q + l + q).indexOf(g) > -1)
										j[k++] = z
								}
								return j
							});
					a.query = m;
					a.get = function(g, c) {
						return m(g, c)[0] || null
					};
					a
							.mix(
									v,
									{
										query : m,
										get : a.get,
										filter : function(g, c) {
											var j = m(g), h, k, s, z = [];
											if (a.isString(c)
													&& (h = p.exec(c)) && !h[1]) {
												k = h[2];
												s = h[3];
												c = function(l) {
													return !(k
															&& l.tagName !== k
																	.toUpperCase() || s
															&& !v
																	.hasClass(
																			l,
																			s))
												}
											}
											if (a.isFunction(c))
												z = a.filter(j, c);
											else if (c && a.ExternalSelector)
												z = a.ExternalSelector._filter(
														g, c + "");
											else
												b(c);
											return z
										},
										test : function(g, c) {
											var j = m(g);
											return j.length
													&& v.filter(j, c).length === j.length
										}
									})
				});
KISSY.add("dom-data", function(a, o) {
	var m = window, e = a.DOM, d = "_ks_data_" + a.now(), f = {}, i = {}, b = {
		EMBED : 1,
		OBJECT : 1,
		APPLET : 1
	};
	a.mix(e, {
		data : function(n, v, q) {
			if (a.isPlainObject(v))
				for ( var u in v)
					e.data(n, u, v[u]);
			else if (q === o) {
				n = a.get(n);
				var y;
				if (!(!n || b[n.nodeName])) {
					if (n == m)
						n = i;
					y = (u = n && n.nodeType) ? f : n;
					n = y[u ? n[d] : d];
					if (a.isString(v) && n)
						return n[v];
					return n
				}
			} else
				a.query(n).each(function(t) {
					if (!(!t || b[t.nodeName])) {
						if (t == m)
							t = i;
						var r = f, p;
						if (t && t.nodeType) {
							if (!(p = t[d]))
								p = t[d] = a.guid()
						} else {
							p = d;
							r = t
						}
						if (v && q !== o) {
							r[p] || (r[p] = {});
							r[p][v] = q
						}
					}
				})
		},
		removeData : function(n, v) {
			a.query(n).each(function(q) {
				if (q) {
					if (q == m)
						q = i;
					var u, y = f, t, r = q && q.nodeType;
					if (r)
						u = q[d];
					else {
						y = q;
						u = d
					}
					if (u) {
						t = y[u];
						if (v) {
							if (t) {
								delete t[v];
								a.isEmptyObject(t) && e.removeData(q)
							}
						} else {
							if (r)
								q.removeAttribute && q.removeAttribute(d);
							else
								try {
									delete q[d]
								} catch (p) {
								}
							r && delete y[u]
						}
					}
				}
			})
		}
	})
});
KISSY.add("dom-class", function(a, o) {
	function m(i, b, n, v) {
		if (!(b = a.trim(b)))
			return v ? false : o;
		i = a.query(i);
		var q = 0, u = i.length;
		b = b.split(d);
		for ( var y; q < u; q++) {
			y = i[q];
			if (e._isElementNode(y)) {
				y = n(y, b, b.length);
				if (y !== o)
					return y
			}
		}
		if (v)
			return false
	}
	var e = a.DOM, d = /[\.\s]\s*\.?/, f = /[\n\t]/g;
	a.mix(e, {
		hasClass : function(i, b) {
			return m(i, b, function(n, v, q) {
				if (n = n.className) {
					n = " " + n + " ";
					for ( var u = 0, y = true; u < q; u++)
						if (n.indexOf(" " + v[u] + " ") < 0) {
							y = false;
							break
						}
					if (y)
						return true
				}
			}, true)
		},
		addClass : function(i, b) {
			m(i, b, function(n, v, q) {
				var u = n.className;
				if (u) {
					var y = " " + u + " ";
					u = u;
					for ( var t = 0; t < q; t++)
						if (y.indexOf(" " + v[t] + " ") < 0)
							u += " " + v[t];
					n.className = a.trim(u)
				} else
					n.className = b
			})
		},
		removeClass : function(i, b) {
			m(i, b, function(n, v, q) {
				var u = n.className;
				if (u)
					if (q) {
						u = (" " + u + " ").replace(f, " ");
						for ( var y = 0, t; y < q; y++)
							for (t = " " + v[y] + " "; u.indexOf(t) >= 0;)
								u = u.replace(t, " ");
						n.className = a.trim(u)
					} else
						n.className = ""
			})
		},
		replaceClass : function(i, b, n) {
			e.removeClass(i, b);
			e.addClass(i, n)
		},
		toggleClass : function(i, b, n) {
			var v = a.isBoolean(n), q;
			m(i, b, function(u, y, t) {
				for ( var r = 0, p; r < t; r++) {
					p = y[r];
					q = v ? !n : e.hasClass(u, p);
					e[q ? "removeClass" : "addClass"](u, p)
				}
			})
		}
	})
});
KISSY
		.add(
				"dom-attr",
				function(a, o) {
					var m = a.UA, e = document.documentElement, d = !e.hasAttribute, f = e.textContent !== o ? "textContent"
							: "innerText", i = a.DOM, b = i._isElementNode, n = /^(?:href|src|style)/, v = /^(?:href|src|colspan|rowspan)/, q = /\r/g, u = /^(?:radio|checkbox)/, y = {
						readonly : "readOnly"
					}, t = {
						val : 1,
						css : 1,
						html : 1,
						text : 1,
						data : 1,
						width : 1,
						height : 1,
						offset : 1
					};
					d && a.mix(y, {
						"for" : "htmlFor",
						"class" : "className"
					});
					a
							.mix(
									i,
									{
										attr : function(r, p, g, c) {
											if (a.isPlainObject(p)) {
												c = g;
												for ( var j in p)
													i.attr(r, j, p[j], c)
											} else if (p = a.trim(p)) {
												p = p.toLowerCase();
												if (c && t[p])
													return i[p](r, g);
												p = y[p] || p;
												if (g === o) {
													r = a.get(r);
													if (!b(r))
														return o;
													var h;
													n.test(p) || (h = r[p]);
													if (h === o)
														h = r.getAttribute(p);
													if (d)
														if (v.test(p))
															h = r.getAttribute(
																	p, 2);
														else if (p === "style")
															h = r.style.cssText;
													return h === null ? o : h
												}
												a
														.each(
																a.query(r),
																function(k) {
																	if (b(k))
																		if (p === "style")
																			k.style.cssText = g;
																		else {
																			if (p === "checked")
																				k[p] = !!g;
																			k
																					.setAttribute(
																							p,
																							""
																									+ g)
																		}
																})
											}
										},
										removeAttr : function(r, p) {
											a.each(a.query(r), function(g) {
												if (b(g)) {
													i.attr(g, p, "");
													g.removeAttribute(p)
												}
											})
										},
										val : function(r, p) {
											if (p === o) {
												var g = a.get(r);
												if (!b(g))
													return o;
												if (g
														&& g.nodeName
																.toUpperCase() === "option"
																.toUpperCase())
													return (g.attributes.value || {}).specified ? g.value
															: g.text;
												if (g
														&& g.nodeName
																.toUpperCase() === "select"
																.toUpperCase()) {
													var c = g.selectedIndex, j = g.options;
													if (c < 0)
														return null;
													else if (g.type === "select-one")
														return i.val(j[c]);
													g = [];
													for ( var h = 0, k = j.length; h < k; ++h)
														j[h].selected
																&& g
																		.push(i
																				.val(j[h]));
													return g
												}
												if (m.webkit && u.test(g.type))
													return g
															.getAttribute("value") === null ? "on"
															: g.value;
												return (g.value || "").replace(
														q, "")
											}
											a
													.each(
															a.query(r),
															function(s) {
																if (s
																		&& s.nodeName
																				.toUpperCase() === "select"
																				.toUpperCase()) {
																	if (a
																			.isNumber(p))
																		p += "";
																	var z = a
																			.makeArray(p), l = s.options, w;
																	h = 0;
																	for (k = l.length; h < k; ++h) {
																		w = l[h];
																		w.selected = a
																				.inArray(
																						i
																								.val(w),
																						z)
																	}
																	if (!z.length)
																		s.selectedIndex = -1
																} else if (b(s))
																	s.value = p
															})
										},
										text : function(r, p) {
											if (p === o) {
												var g = a.get(r);
												if (b(g))
													return g[f] || "";
												else if (i._nodeTypeIs(g, 3))
													return g.nodeValue
											} else
												a.each(a.query(r), function(c) {
													if (b(c))
														c[f] = p;
													else if (i
															._nodeTypeIs(c, 3))
														c.nodeValue = p
												})
										}
									})
				});
KISSY
		.add(
				"dom-style",
				function(a, o) {
					function m(c, j) {
						var h = a.get(c), k = j === n ? h.offsetWidth
								: h.offsetHeight;
						a.each(j === n ? [ "Left", "Right" ] : [ "Top",
								"Bottom" ], function(s) {
							k -= parseFloat(d._getComputedStyle(h, "padding"
									+ s)) || 0;
							k -= parseFloat(d._getComputedStyle(h, "border" + s
									+ "Width")) || 0
						});
						return k
					}
					function e(c, j, h) {
						var k = h;
						if (h === v && u.test(j)) {
							k = 0;
							if (d.css(c, "position") === "absolute") {
								h = c[j === "left" ? "offsetLeft" : "offsetTop"];
								if (f.ie === 8 || f.opera)
									h -= q(d.css(c.offsetParent, "border-" + j
											+ "-width")) || 0;
								k = h - (q(d.css(c, "margin-" + j)) || 0)
							}
						}
						return k
					}
					var d = a.DOM, f = a.UA, i = document, b = i.documentElement, n = "width", v = "auto", q = parseInt, u = /^(?:left|top)/, y = /^(?:width|height|top|left|right|bottom|margin|padding)/i, t = /-([a-z])/ig, r = function(
							c, j) {
						return j.toUpperCase()
					}, p = {}, g = {};
					a
							.mix(
									d,
									{
										_CUSTOM_STYLES : p,
										_getComputedStyle : function(c, j) {
											var h = "", k = c.ownerDocument;
											if (c.style)
												h = k.defaultView
														.getComputedStyle(c,
																null)[j];
											return h
										},
										css : function(c, j, h) {
											if (a.isPlainObject(j))
												for ( var k in j)
													d.css(c, k, j[k]);
											else {
												if (j.indexOf("-") > 0)
													j = j.replace(t, r);
												j = p[j] || j;
												if (h === o) {
													c = a.get(c);
													k = "";
													if (c && c.style) {
														k = j.get ? j.get(c)
																: c.style[j];
														if (k === "" && !j.get)
															k = e(
																	c,
																	j,
																	d
																			._getComputedStyle(
																					c,
																					j))
													}
													return k === o ? "" : k
												} else {
													if (h === null || h === "")
														h = "";
													else if (!isNaN(new Number(
															h))
															&& y.test(j))
														h += "px";
													(j === n || j === "height")
															&& parseFloat(h) < 0
															|| a
																	.each(
																			a
																					.query(c),
																			function(
																					s) {
																				if (s
																						&& s.style) {
																					j.set ? j
																							.set(
																									s,
																									h)
																							: s.style[j] = h;
																					if (h === "")
																						s.style.cssText
																								|| s
																										.removeAttribute("style")
																				}
																			})
												}
											}
										},
										width : function(c, j) {
											if (j === o)
												return m(c, n);
											else
												d.css(c, n, j)
										},
										height : function(c, j) {
											if (j === o)
												return m(c, "height");
											else
												d.css(c, "height", j)
										},
										show : function(c) {
											a
													.query(c)
													.each(
															function(j) {
																if (j) {
																	j.style.display = d
																			.data(
																					j,
																					"display")
																			|| "";
																	if (d
																			.css(
																					j,
																					"display") === "none") {
																		var h = j.tagName, k = g[h], s;
																		if (!k) {
																			s = i
																					.createElement(h);
																			i.body
																					.appendChild(s);
																			k = d
																					.css(
																							s,
																							"display");
																			d
																					.remove(s);
																			g[h] = k
																		}
																		d
																				.data(
																						j,
																						"display",
																						k);
																		j.style.display = k
																	}
																}
															})
										},
										hide : function(c) {
											a
													.query(c)
													.each(
															function(j) {
																if (j) {
																	var h = j.style, k = h.display;
																	if (k !== "none") {
																		k
																				&& d
																						.data(
																								j,
																								"display",
																								k);
																		h.display = "none"
																	}
																}
															})
										},
										toggle : function(c) {
											a
													.query(c)
													.each(
															function(j) {
																if (j)
																	j.style.display === "none" ? d
																			.show(j)
																			: d
																					.hide(j)
															})
										},
										addStyleSheet : function(c, j) {
											var h;
											if (j && (j = j.replace("#", "")))
												h = a.get("#" + j);
											if (!h) {
												h = d.create("<style>", {
													id : j
												});
												a.get("head").appendChild(h);
												if (h.styleSheet)
													h.styleSheet.cssText = c;
												else
													h.appendChild(i
															.createTextNode(c))
											}
										}
									});
					if (b.style.cssFloat !== o)
						p["float"] = "cssFloat";
					else if (b.style.styleFloat !== o)
						p["float"] = "styleFloat"
				});
KISSY
		.add(
				"dom-style-ie",
				function(a, o) {
					if (a.UA.ie) {
						var m = a.DOM, e = document, d = e.documentElement, f = m._CUSTOM_STYLES, i = /^-?\d+(?:px)?$/i, b = /^-?\d/, n = /^(?:width|height)$/;
						try {
							if (d.style.opacity === o && d.filters)
								f.opacity = {
									get : function(q) {
										var u = 100;
										try {
											u = q.filters["DXImageTransform.Microsoft.Alpha"].opacity
										} catch (y) {
											try {
												u = q.filters("alpha").opacity
											} catch (t) {
											}
										}
										return u / 100 + ""
									},
									set : function(q, u) {
										var y = q.style, t = (q.currentStyle || 0).filter
												|| "";
										y.zoom = 1;
										if (t)
											if (t = t
													.replace(
															/alpha\(opacity=.+\)/ig,
															""))
												t += ", ";
										y.filter = t + "alpha(opacity=" + u
												* 100 + ")"
									}
								}
						} catch (v) {
							a.log("IE filters ActiveX is disabled. ex = " + v)
						}
						if (!(e.defaultView || {}).getComputedStyle
								&& d.currentStyle)
							m._getComputedStyle = function(q, u) {
								var y = q.style, t = q.currentStyle[u];
								if (n.test(u))
									t = m[u](q) + "px";
								else if (!i.test(t) && b.test(t)) {
									var r = y.left, p = q.runtimeStyle.left;
									q.runtimeStyle.left = q.currentStyle.left;
									y.left = u === "fontSize" ? "1em" : t || 0;
									t = y.pixelLeft + "px";
									y.left = r;
									q.runtimeStyle.left = p
								}
								return t
							}
					}
				});
KISSY
		.add(
				"dom-offset",
				function(a, o) {
					function m(h) {
						var k = 0, s = 0, z = v(h[t]);
						if (h[j]) {
							h = h[j]();
							k = h[r];
							s = h[p];
							if (d.mobile !== "apple") {
								k += e[g](z);
								s += e[c](z)
							}
						}
						return {
							left : k,
							top : s
						}
					}
					var e = a.DOM, d = a.UA, f = window, i = document, b = e._isElementNode, n = e._nodeTypeIs, v = e._getWin, q = i.compatMode === "CSS1Compat", u = Math.max, y = parseInt, t = "ownerDocument", r = "left", p = "top", g = "scrollLeft", c = "scrollTop", j = "getBoundingClientRect";
					a
							.mix(
									e,
									{
										offset : function(h, k) {
											if (!(h = a.get(h)) || !h[t])
												return null;
											if (k === o)
												return m(h);
											var s = h;
											if (e.css(s, "position") === "static")
												s.style.position = "relative";
											var z = m(s), l = {}, w, x;
											for (x in k) {
												w = y(e.css(s, x), 10) || 0;
												l[x] = w + k[x] - z[x]
											}
											e.css(s, l)
										},
										scrollIntoView : function(h, k, s, z) {
											if ((h = a.get(h)) && h[t]) {
												z = z === o ? true : !!z;
												s = s === o ? true : !!s;
												if (!k || k === f)
													return h.scrollIntoView(s);
												k = a.get(k);
												if (n(k, 9))
													k = v(k);
												var l = k && "scrollTo" in k
														&& k.document, w = e
														.offset(h), x = l ? {
													left : e.scrollLeft(k),
													top : e.scrollTop(k)
												} : e.offset(k), A = {
													left : w[r] - x[r],
													top : w[p] - x[p]
												};
												w = l ? e.viewportHeight(k)
														: k.clientHeight;
												x = l ? e.viewportWidth(k)
														: k.clientWidth;
												var C = e[g](k), E = e[c](k), D = C
														+ x, B = E + w, K = h.offsetHeight;
												h = h.offsetWidth;
												var J = A.left
														+ C
														- (y(e
																.css(k,
																		"borderLeftWidth")) || 0);
												A = A.top
														+ E
														- (y(e
																.css(k,
																		"borderTopWidth")) || 0);
												var G = J + h, F = A + K, H, I;
												if (K > w || A < E || s)
													H = A;
												else if (F > B)
													H = F - w;
												if (z)
													if (h > x || J < C || s)
														I = J;
													else if (G > D)
														I = G - x;
												if (l) {
													if (H !== o || I !== o)
														k.scrollTo(I, H)
												} else {
													if (H !== o)
														k[c] = H;
													if (I !== o)
														k[g] = I
												}
											}
										}
									});
					a.each([ "Left", "Top" ], function(h, k) {
						var s = "scroll" + h;
						e[s] = function(z) {
							var l = 0, w = v(z), x;
							if (w && (x = w.document))
								l = w[k ? "pageYOffset" : "pageXOffset"]
										|| x.documentElement[s] || x.body[s];
							else if (b(z = a.get(z)))
								l = z[s];
							return l
						}
					});
					a.each([ "Width", "Height" ], function(h) {
						e["doc" + h] = function(k) {
							k = k || i;
							return u(q ? k.documentElement["scroll" + h]
									: k.body["scroll" + h], e["viewport" + h]
									(k))
						};
						e["viewport" + h] = function(k) {
							var s = "inner" + h;
							k = v(k);
							var z = k.document;
							return s in k ? k[s]
									: q ? z.documentElement["client" + h]
											: z.body["client" + h]
						}
					})
				});
KISSY.add("dom-traversal", function(a, o) {
	function m(i, b, n, v) {
		if (!(i = a.get(i)))
			return null;
		if (b === o)
			b = 1;
		var q = null, u, y;
		if (a.isNumber(b) && b >= 0) {
			if (b === 0)
				return i;
			u = 0;
			y = b;
			b = function() {
				return ++u === y
			}
		}
		for (; i = i[n];)
			if (f(i) && (!b || d.test(i, b)) && (!v || v(i))) {
				q = i;
				break
			}
		return q
	}
	function e(i, b, n) {
		var v = [];
		var q = i = a.get(i);
		if (i && n)
			q = i.parentNode;
		if (q) {
			n = 0;
			for (q = q.firstChild; q; q = q.nextSibling)
				if (f(q) && q !== i && (!b || d.test(q, b)))
					v[n++] = q
		}
		return v
	}
	var d = a.DOM, f = d._isElementNode;
	a.mix(d, {
		parent : function(i, b) {
			return m(i, b, "parentNode", function(n) {
				return n.nodeType != 11
			})
		},
		next : function(i, b) {
			return m(i, b, "nextSibling")
		},
		prev : function(i, b) {
			return m(i, b, "previousSibling")
		},
		siblings : function(i, b) {
			return e(i, b, true)
		},
		children : function(i, b) {
			return e(i, b)
		},
		contains : function(i, b) {
			var n = false;
			if ((i = a.get(i)) && (b = a.get(b)))
				if (i.contains) {
					if (b.nodeType === 3) {
						b = b.parentNode;
						if (b === i)
							return true
					}
					if (b)
						return i.contains(b)
				} else if (i.compareDocumentPosition)
					return !!(i.compareDocumentPosition(b) & 16);
				else
					for (; !n && (b = b.parentNode);)
						n = b == i;
			return n
		}
	})
});
KISSY
		.add(
				"dom-create",
				function(a, o) {
					function m(l) {
						var w = l.cloneNode(true);
						if (b.ie < 8)
							w.innerHTML = l.innerHTML;
						return w
					}
					function e(l, w, x, A) {
						if (x) {
							var C = a.guid("ks-tmp-"), E = RegExp(r);
							w += '<span id="' + C + '"></span>';
							a.available(C, function() {
								var D = a.get("head"), B, K, J, G, F, H;
								for (E.lastIndex = 0; B = E.exec(w);)
									if ((J = (K = B[1]) ? K.match(g) : false)
											&& J[2]) {
										B = f.createElement("script");
										B.src = J[2];
										if ((G = K.match(c)) && G[2])
											B.charset = G[2];
										B.async = true;
										D.appendChild(B)
									} else if ((H = B[2]) && H.length > 0)
										a.globalEval(H);
								(F = f.getElementById(C)) && i.remove(F);
								a.isFunction(A) && A()
							});
							d(l, w)
						} else {
							d(l, w);
							a.isFunction(A) && A()
						}
					}
					function d(l, w) {
						w = (w + "").replace(r, "");
						try {
							l.innerHTML = w
						} catch (x) {
							for (; l.firstChild;)
								l.removeChild(l.firstChild);
							w && l.appendChild(i.create(w))
						}
					}
					var f = document, i = a.DOM, b = a.UA, n = b.ie, v = i._nodeTypeIs, q = i._isElementNode, u = i._isKSNode, y = f
							.createElement("div"), t = /<(\w+)/, r = /<script([^>]*)>([^<]*(?:(?!<\/script>)<[^<]*)*)<\/script>/ig, p = /^<(\w+)\s*\/?>(?:<\/\1>)?$/, g = /\ssrc=(['"])(.*?)\1/i, c = /\scharset=(['"])(.*?)\1/i;
					a
							.mix(
									i,
									{
										create : function(l, w, x) {
											if (v(l, 1) || v(l, 3))
												return m(l);
											if (u(l))
												return m(l[0]);
											if (!(l = a.trim(l)))
												return null;
											var A = null;
											A = i._creators;
											var C, E = "div", D;
											if (C = p.exec(l))
												A = (x || f)
														.createElement(C[1]);
											else {
												if ((C = t.exec(l))
														&& (D = C[1])
														&& a
																.isFunction(A[D = D
																		.toLowerCase()]))
													E = D;
												l = A[E](l, x).childNodes;
												if (l.length === 1)
													x = l[0].parentNode
															.removeChild(l[0]);
												else {
													l = l;
													D = x || f;
													x = null;
													if (l && (l.push || l.item)
															&& l[0]) {
														D = D
																|| l[0].ownerDocument;
														x = D
																.createDocumentFragment();
														if (l.item)
															l = a.makeArray(l);
														D = 0;
														for (A = l.length; D < A; D++)
															x.appendChild(l[D])
													} else
														a
																.log("Unable to convert "
																		+ l
																		+ " to fragment.");
													x = x
												}
												A = x
											}
											x = A;
											q(x) && a.isPlainObject(w)
													&& i.attr(x, w, true);
											return x
										},
										_creators : {
											div : function(l, w) {
												var x = w ? w
														.createElement("div")
														: y;
												x.innerHTML = l;
												return x
											}
										},
										html : function(l, w, x, A) {
											if (w === o) {
												l = a.get(l);
												if (q(l))
													return l.innerHTML
											} else
												a.each(a.query(l), function(C) {
													q(C) && e(C, w, x, A)
												})
										},
										remove : function(l) {
											a.each(a.query(l), function(w) {
												q(w)
														&& w.parentNode
														&& w.parentNode
																.removeChild(w)
											})
										}
									});
					if (n || b.gecko || b.webkit) {
						var j = i._creators, h = i.create, k = /(?:\/(?:thead|tfoot|caption|col|colgroup)>)+\s*<tbody/, s = {
							option : "select",
							td : "tr",
							tr : "tbody",
							tbody : "table",
							col : "colgroup",
							legend : "fieldset"
						}, z;
						for (z in s)
							(function(l) {
								j[z] = function(w, x) {
									return h(
											"<" + l + ">" + w + "</" + l + ">",
											null, x)
								}
							})(s[z]);
						if (n) {
							j.script = function(l, w) {
								var x = w ? w.createElement("div") : y;
								x.innerHTML = "-" + l;
								x.removeChild(x.firstChild);
								return x
							};
							if (n < 8)
								j.tbody = function(l, w) {
									var x = h("<table>" + l + "</table>", null,
											w), A = x.children.tags("tbody")[0];
									x.children.length > 1 && A && !k.test(l)
											&& A.parentNode.removeChild(A);
									return x
								}
						}
						a.mix(j, {
							optgroup : j.option,
							th : j.td,
							thead : j.tbody,
							tfoot : j.tbody,
							caption : j.tbody,
							colgroup : j.tbody
						})
					}
				});
KISSY.add("dom-insertion", function(a) {
	var o = a.DOM;
	a.mix(o, {
		insertBefore : function(m, e) {
			if ((m = a.get(m)) && (e = a.get(e)) && e.parentNode)
				e.parentNode.insertBefore(m, e);
			return m
		},
		insertAfter : function(m, e) {
			if ((m = a.get(m)) && (e = a.get(e)) && e.parentNode)
				e.nextSibling ? e.parentNode.insertBefore(m, e.nextSibling)
						: e.parentNode.appendChild(m);
			return m
		},
		append : function(m, e) {
			if ((m = a.get(m)) && (e = a.get(e)))
				e.appendChild && e.appendChild(m)
		},
		prepend : function(m, e) {
			if ((m = a.get(m)) && (e = a.get(e)))
				e.firstChild ? o.insertBefore(m, e.firstChild) : e
						.appendChild(m)
		}
	})
});
KISSY.add("dom-diandian", function(a) {
	var o = a.DOM;
	o.createFragment = function(m, e) {
		var d = document.createDocumentFragment(), f = o.create("<div>");
		e = e || false;
		for (o.html(f, m, e); f.firstChild;)
			d.appendChild(f.firstChild);
		return d
	};
	o.appendHtml = function(m, e, d) {
		d = d || false;
		e = o.createFragment(e, d);
		m.appendChild(e)
	}
});
KISSY
		.add(
				"event",
				function(a, o) {
					function m(r, p, g, c, j) {
						if (a.isString(p))
							p = a.query(p);
						if (a.isArray(p)) {
							a.each(p, function(h) {
								t[r](h, g, c, j)
							});
							return true
						}
						if ((g = a.trim(g)) && g.indexOf(q) > 0) {
							a.each(g.split(q), function(h) {
								t[r](p, h, c, j)
							});
							return true
						}
					}
					function e(r, p) {
						d(r) && i.data(r, v, p)
					}
					function d(r) {
						return r && r.nodeType !== 3 && r.nodeType !== 8
					}
					var f = document, i = a.DOM, b = f.addEventListener ? function(
							r, p, g, c) {
						r.addEventListener && r.addEventListener(p, g, !!c)
					}
							: function(r, p, g) {
								r.attachEvent && r.attachEvent("on" + p, g)
							}, n = f.removeEventListener ? function(r, p, g, c) {
						r.removeEventListener
								&& r.removeEventListener(p, g, !!c)
					}
							: function(r, p, g) {
								r.detachEvent && r.detachEvent("on" + p, g)
							}, v = "ksEventTargetId", q = " ", u = a.now(), y = {}, t = {
						EVENT_GUID : v,
						special : {},
						add : function(r, p, g, c) {
							if (!m("add", r, p, g, c)) {
								var j = d(r) ? i.data(r, v) : -1, h, k, s, z, l;
								if (!(j === -1 || !p || !a.isFunction(g))) {
									if (!j) {
										e(r, j = u++);
										y[j] = {
											target : r,
											events : {}
										}
									}
									k = y[j].events;
									if (!k[p]) {
										h = ((j = !r.isCustomEventTarget) || r._supportSpecialEvent)
												&& t.special[p] || {};
										s = function(w, x) {
											if (!w || !w.fixed) {
												w = new a.EventObject(r, w, p,
														this);
												a.isPlainObject(x)
														&& a.mix(w, x)
											}
											h.setup && h.setup(w);
											return (h.handle || t._handle)
													.call(this, r, w,
															k[p].listeners)
										};
										k[p] = {
											handle : s,
											listeners : []
										};
										z = h.fix || p;
										l = h.capture;
										if (j)
											b(r, z, s, l);
										else
											r._addEvent && r._addEvent(z, s, l)
									}
									k[p].listeners.push({
										fn : g,
										scope : c || r
									})
								}
							}
						},
						remove : function(r, p, g, c) {
							if (!m("remove", r, p, g, c)) {
								var j = d(r) ? i.data(r, v) : -1, h, k, s, z, l, w, x;
								if (j !== -1)
									if (j && (h = y[j]))
										if (h.target === r) {
											c = c || r;
											h = h.events || {};
											if (k = h[p]) {
												s = k.listeners;
												w = s.length;
												if (a.isFunction(g) && w) {
													l = z = 0;
													for (x = []; z < w; ++z)
														if (g !== s[z].fn
																|| c !== s[z].scope)
															x[l++] = s[z];
													k.listeners = x;
													w = x.length
												}
												if (g === o || w === 0) {
													if (r.isCustomEventTarget)
														r._removeEvent
																&& r
																		._removeEvent(
																				p,
																				k.handle);
													else {
														g = t.special[p] || {};
														n(r, g.fix || p,
																k.handle)
													}
													delete h[p]
												}
											}
											if (p === o || a.isEmptyObject(h)) {
												for (p in h)
													t.remove(r, p);
												delete y[j];
												i.removeData(r, v)
											}
										}
							}
						},
						_handle : function(r, p, g) {
							g = g.slice(0);
							var c, j = 0, h = g.length, k;
							if (r.isCustomEventTarget && r.item)
								k = r.item(this);
							for (; j < h; ++j) {
								c = g[j];
								c = c.fn.call(k || c.scope, p);
								if (c === false && r.isCustomEventTarget
										|| p.isImmediatePropagationStopped)
									break
							}
							return c
						},
						_getCache : function(r) {
							return y[r]
						},
						_simpleAdd : b,
						_simpleRemove : n
					};
					t.on = t.add;
					a.Event = t
				});
KISSY
		.add(
				"event-object",
				function(a, o) {
					function m(f, i, b, n) {
						this.currentTarget = f;
						this.originalEvent = i || {};
						if (i) {
							this.type = i.type;
							this._fix()
						} else {
							this.type = b;
							this.target = f
						}
						this.currentTarget = f;
						this.fixed = true;
						if (f.isCustomEventTarget) {
							if (f.item)
								f = f.item(n);
							if (a.DOM._isKSNode(f))
								this.target = new a.Node(this.target)
						}
					}
					var e = document, d = "altKey attrChange attrName bubbles button cancelable charCode clientX clientY ctrlKey currentTarget data detail eventPhase fromElement handler keyCode layerX layerY metaKey newValue offsetX offsetY originalTarget pageX pageY prevValue relatedNode relatedTarget screenX screenY shiftKey srcElement target toElement view wheelDelta which"
							.split(" ");
					a
							.augment(
									m,
									{
										_fix : function() {
											var f = this.originalEvent, i = d.length, b, n = this.currentTarget;
											for (n = n.nodeType === 9 ? n
													: n.ownerDocument || e; i;) {
												b = d[--i];
												this[b] = f[b]
											}
											if (!this.target)
												this.target = this.srcElement
														|| e;
											if (this.target.nodeType === 3)
												this.target = this.target.parentNode;
											if (!this.relatedTarget
													&& this.fromElement)
												this.relatedTarget = this.fromElement === this.target ? this.toElement
														: this.fromElement;
											if (this.pageX === o
													&& this.clientX !== o) {
												f = n.documentElement;
												i = n.body;
												this.pageX = this.clientX
														+ (f && f.scrollLeft
																|| i
																&& i.scrollLeft || 0)
														- (f && f.clientLeft
																|| i
																&& i.clientLeft || 0);
												this.pageY = this.clientY
														+ (f && f.scrollTop
																|| i
																&& i.scrollTop || 0)
														- (f && f.clientTop
																|| i
																&& i.clientTop || 0)
											}
											if (this.which === o)
												this.which = this.charCode !== o ? this.charCode
														: this.keyCode;
											if (this.metaKey === o)
												this.metaKey = this.ctrlKey;
											if (!this.which
													&& this.button !== o)
												this.which = this.button & 1 ? 1
														: this.button & 2 ? 3
																: this.button & 4 ? 2
																		: 0
										},
										preventDefault : function() {
											var f = this.originalEvent;
											if (f.preventDefault)
												f.preventDefault();
											else
												f.returnValue = false;
											this.isDefaultPrevented = true
										},
										stopPropagation : function() {
											var f = this.originalEvent;
											if (f.stopPropagation)
												f.stopPropagation();
											else
												f.cancelBubble = true;
											this.isPropagationStopped = true
										},
										stopImmediatePropagation : function() {
											var f = this.originalEvent;
											f.stopImmediatePropagation ? f
													.stopImmediatePropagation()
													: this.stopPropagation();
											this.isImmediatePropagationStopped = true
										},
										halt : function(f) {
											f ? this.stopImmediatePropagation()
													: this.stopPropagation();
											this.preventDefault()
										}
									});
					a.EventObject = m
				});
KISSY.add("event-target", function(a, o) {
	var m = a.Event;
	a.EventTarget = {
		isCustomEventTarget : true,
		fire : function(e, d) {
			var f = a.DOM.data(this, m.EVENT_GUID) || -1;
			if ((f = ((m._getCache(f) || {}).events || {})[e])
					&& a.isFunction(f.handle))
				return f.handle(o, d);
			return this
		},
		on : function(e, d, f) {
			m.add(this, e, d, f);
			return this
		},
		detach : function(e, d, f) {
			m.remove(this, e, d, f);
			return this
		}
	}
});
KISSY.add("event-mouseenter", function(a) {
	var o = a.Event;
	a.UA.ie || a.each([ {
		name : "mouseenter",
		fix : "mouseover"
	}, {
		name : "mouseleave",
		fix : "mouseout"
	} ], function(m) {
		o.special[m.name] = {
			fix : m.fix,
			setup : function(e) {
				e.type = m.name
			},
			handle : function(e, d, f) {
				if (a.DOM._isKSNode(e))
					e = e[0];
				else if (e.item)
					e = d.currentTarget[0];
				var i = d.relatedTarget;
				try {
					for (; i && i !== e;)
						i = i.parentNode;
					i !== e && o._handle(e, d, f)
				} catch (b) {
					a.log(b)
				}
			}
		}
	})
});
KISSY.add("event-focusin", function(a) {
	var o = a.Event;
	document.addEventListener && a.each([ {
		name : "focusin",
		fix : "focus"
	}, {
		name : "focusout",
		fix : "blur"
	} ], function(m) {
		o.special[m.name] = {
			fix : m.fix,
			capture : true,
			setup : function(e) {
				e.type = m.name
			}
		}
	})
});
KISSY.add("node", function(a) {
	function o(e, d, f) {
		if (!(this instanceof o))
			return new o(e, d, f);
		if (e) {
			if (a.isString(e)) {
				e = m.create(e, d, f);
				if (e.nodeType === 11)
					return new a.NodeList(e.childNodes)
			} else if (e instanceof o)
				return e;
			else
				e = e;
			this[0] = e
		} else
			this.length = 0
	}
	var m = a.DOM;
	o.TYPE = "-ks-Node";
	a.augment(o, {
		length : 1,
		getDOMNode : function() {
			return this[0]
		},
		nodeType : o.TYPE
	});
	a.one = function(e, d) {
		var f = a.get(e, d);
		return f ? new o(f) : null
	};
	a.Node = o
});
KISSY.add("nodelist", function(a) {
	function o(d) {
		if (!(this instanceof o))
			return new o(d);
		m.push.apply(this, a.makeArray(d) || [])
	}
	var m = Array.prototype, e = a.DOM._isElementNode;
	a.mix(o.prototype, {
		length : 0,
		item : function(d) {
			var f = null, i, b;
			if (e(d)) {
				i = 0;
				for (b = this.length; i < b; i++)
					if (d === this[i]) {
						d = i;
						break
					}
			}
			if (e(this[d]))
				f = new a.Node(this[d]);
			return f
		},
		getDOMNodes : function() {
			return m.slice.call(this)
		},
		each : function(d, f) {
			var i = this.length, b = 0, n;
			for (n = new a.Node(this[0]); b < i
					&& d.call(f || n, n, b, this) !== false; n = new a.Node(
					this[++b]))
				;
			return this
		}
	});
	a.all = function(d, f) {
		return new o(a.query(d, f, true))
	};
	a.NodeList = o
});
KISSY
		.add(
				"node-attach",
				function(a, o) {
					function m(c, j, h, k) {
						c = [ this[c ? t : y]() ].concat(a.makeArray(j));
						if (j[h] === o)
							return k.apply(i, c);
						else {
							k.apply(i, c);
							return this
						}
					}
					function e(c, j) {
						a
								.each(
										c,
										function(h) {
											a
													.each(
															[ q, u ],
															function(k, s) {
																k[h] = function(
																		z) {
																	switch (j) {
																	case r:
																		return function() {
																			return m
																					.call(
																							this,
																							s,
																							arguments,
																							1,
																							z)
																		};
																	case p:
																		return function() {
																			return m
																					.call(
																							this,
																							s,
																							arguments,
																							0,
																							z)
																		};
																	case g:
																		return function() {
																			var l = this[s ? t
																					: y]
																					();
																			return (l = z
																					.apply(
																							i,
																							[ l ]
																									.concat(a
																											.makeArray(arguments)))) ? new (a[a
																					.isArray(l) ? "NodeList"
																					: "Node"])(
																					l)
																					: null
																		};
																	default:
																		return function() {
																			var l = this[s ? t
																					: y]
																					();
																			l = z
																					.apply(
																							i,
																							[ l ]
																									.concat(a
																											.makeArray(arguments)));
																			return l === o ? this
																					: l
																		}
																	}
																}(i[h])
															})
										})
					}
					function d(c, j, h) {
						c && a.each(this, function(k) {
							var s;
							if (j || a.isString(c))
								s = i.create(c);
							else {
								if (n(c, 1) || n(c, 3))
									s = c;
								if (v(c))
									s = c[0]
							}
							i[h](s, k)
						});
						return this
					}
					function f(c, j) {
						if ((c = a.get(c)) && c.appendChild)
							a.each(this, function(h) {
								i[j](h, c)
							});
						return this
					}
					var i = a.DOM, b = a.Event, n = i._nodeTypeIs, v = i._isKSNode, q = a.Node.prototype, u = a.NodeList.prototype, y = "getDOMNode", t = y
							+ "s", r = 1, p = 2, g = 4;
					a.mix(q, {
						one : function(c) {
							return a.one(c, this[0])
						},
						all : function(c) {
							return a.all(c, this[0])
						}
					});
					e([ "data", "removeData" ], r);
					e([ "hasClass", "addClass", "removeClass", "replaceClass",
							"toggleClass" ]);
					e([ "attr", "removeAttr" ], r);
					e([ "val", "text" ], p);
					e([ "css" ], r);
					e([ "width", "height" ], p);
					e([ "offset" ], p);
					e([ "scrollIntoView" ]);
					e([ "parent", "next", "prev", "siblings", "children" ], g);
					e([ "contains" ]);
					e([ "html" ], p);
					e([ "remove" ]);
					a.each([ "insertBefore", "insertAfter" ], function(c) {
						q[c] = function(j) {
							i[c].call(i, this[0], j);
							return this
						}
					});
					a.each([ q, u ], function(c, j) {
						a.each([ "append", "prepend" ], function(h) {
							c[h] = function(k) {
								return d.call(this, k, j, h)
							};
							c[h + "To"] = function(k) {
								return f.call(this, k, h)
							}
						})
					});
					a.each([ q, u ], function(c) {
						a.mix(c, a.EventTarget);
						c._supportSpecialEvent = true;
						c._addEvent = function(j, h, k) {
							for ( var s = 0, z = this.length; s < z; s++)
								b._simpleAdd(this[s], j, h, k)
						};
						c._removeEvent = function(j, h, k) {
							for ( var s = 0, z = this.length; s < z; s++)
								b._simpleRemove(this[s], j, h, k)
						};
						delete c.fire
					})
				});
if (!this.JSON)
	this.JSON = {};
(function() {
	function a(v) {
		return v < 10 ? "0" + v : v
	}
	function o(v) {
		d.lastIndex = 0;
		return d.test(v) ? '"'
				+ v.replace(d, function(q) {
					var u = b[q];
					return typeof u === "string" ? u : "\\u"
							+ ("0000" + q.charCodeAt(0).toString(16)).slice(-4)
				}) + '"' : '"' + v + '"'
	}
	function m(v, q) {
		var u, y, t, r, p = f, g, c = q[v];
		if (c && typeof c === "object" && typeof c.toJSON === "function")
			c = c.toJSON(v);
		if (typeof n === "function")
			c = n.call(q, v, c);
		switch (typeof c) {
		case "string":
			return o(c);
		case "number":
			return isFinite(c) ? String(c) : "null";
		case "boolean":
		case "null":
			return String(c);
		case "object":
			if (!c)
				return "null";
			f += i;
			g = [];
			if (Object.prototype.toString.apply(c) === "[object Array]") {
				r = c.length;
				for (u = 0; u < r; u += 1)
					g[u] = m(u, c) || "null";
				t = g.length === 0 ? "[]" : f ? "[\n" + f + g.join(",\n" + f)
						+ "\n" + p + "]" : "[" + g.join(",") + "]";
				f = p;
				return t
			}
			if (n && typeof n === "object") {
				r = n.length;
				for (u = 0; u < r; u += 1) {
					y = n[u];
					if (typeof y === "string")
						if (t = m(y, c))
							g.push(o(y) + (f ? ": " : ":") + t)
				}
			} else
				for (y in c)
					if (Object.hasOwnProperty.call(c, y))
						if (t = m(y, c))
							g.push(o(y) + (f ? ": " : ":") + t);
			t = g.length === 0 ? "{}" : f ? "{\n" + f + g.join(",\n" + f)
					+ "\n" + p + "}" : "{" + g.join(",") + "}";
			f = p;
			return t
		}
	}
	if (typeof Date.prototype.toJSON !== "function") {
		Date.prototype.toJSON = function() {
			return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-"
					+ a(this.getUTCMonth() + 1) + "-" + a(this.getUTCDate())
					+ "T" + a(this.getUTCHours()) + ":"
					+ a(this.getUTCMinutes()) + ":" + a(this.getUTCSeconds())
					+ "Z" : null
		};
		String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function() {
			return this.valueOf()
		}
	}
	var e = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, d = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, f, i, b = {
		"" : "\\b",
		"\t" : "\\t",
		"\n" : "\\n",
		"" : "\\f",
		"\r" : "\\r",
		'"' : '\\"',
		"\\" : "\\\\"
	}, n;
	if (typeof JSON.stringify !== "function")
		JSON.stringify = function(v, q, u) {
			var y;
			i = f = "";
			if (typeof u === "number")
				for (y = 0; y < u; y += 1)
					i += " ";
			else if (typeof u === "string")
				i = u;
			if ((n = q) && typeof q !== "function"
					&& (typeof q !== "object" || typeof q.length !== "number"))
				throw Error("JSON.stringify");
			return m("", {
				"" : v
			})
		};
	if (typeof JSON.parse !== "function")
		JSON.parse = function(v, q) {
			function u(t, r) {
				var p, g, c = t[r];
				if (c && typeof c === "object")
					for (p in c)
						if (Object.hasOwnProperty.call(c, p)) {
							g = u(c, p);
							if (g !== undefined)
								c[p] = g;
							else
								delete c[p]
						}
				return q.call(t, r, c)
			}
			var y;
			v = String(v);
			e.lastIndex = 0;
			if (e.test(v))
				v = v.replace(e, function(t) {
					return "\\u"
							+ ("0000" + t.charCodeAt(0).toString(16)).slice(-4)
				});
			if (/^[\],:{}\s]*$/
					.test(v
							.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@")
							.replace(
									/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,
									"]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) {
				y = eval("(" + v + ")");
				return typeof q === "function" ? u({
					"" : y
				}, "") : y
			}
			throw new SyntaxError("JSON.parse");
		}
})();
KISSY.add("json", function(a) {
	var o = window.JSON;
	a.JSON = {
		parse : function(m) {
			if (m == null || m === "")
				return null;
			return o.parse(m)
		},
		stringify : o.stringify
	}
});
KISSY
		.add(
				"ajax",
				function(a, o) {
					function m(l) {
						l = a.merge(z, l);
						if (l.url) {
							if (l.data && !a.isString(l.data))
								l.data = a.param(l.data);
							l.context = l.context || l;
							var w, x = c, A, C = l.type.toUpperCase();
							if (l.dataType === u) {
								w = l.jsonpCallback || u + a.now();
								l.url = l.url
										+ (l.url.indexOf("?") === -1 ? "?"
												: "&") + (l.jsonp + "=" + w);
								l.dataType = y;
								var E = f[w];
								f[w] = function(G) {
									if (a.isFunction(E))
										E(G);
									else {
										f[w] = o;
										try {
											delete f[w]
										} catch (F) {
										}
									}
									e([ c, j ], G, x, B, l)
								}
							}
							if (l.data && C === b)
								l.url = l.url
										+ (l.url.indexOf("?") === -1 ? "?"
												: "&") + l.data;
							if (l.dataType === y) {
								d(r, l);
								C = a.getScript(l.url, w ? null : function() {
									e([ c, j ], t, x, B, l)
								});
								d(p, l);
								return C
							}
							var D = false, B = l.xhr();
							d(r, l);
							B.open(C, l.url, l.async);
							try {
								if (l.data || l.contentType)
									B.setRequestHeader(v, l.contentType);
								B
										.setRequestHeader(
												"Accept",
												l.dataType
														&& l.accepts[l.dataType] ? l.accepts[l.dataType]
														+ ", */*; q=0.01"
														: l.accepts._default)
							} catch (K) {
							}
							B.onreadystatechange = function(G) {
								if (!B || B.readyState === 0 || G === "abort") {
									D || e(j, null, h, B, l);
									D = true;
									if (B)
										B.onreadystatechange = i
								} else if (!D && B
										&& (B.readyState === 4 || G === k)) {
									D = true;
									B.onreadystatechange = i;
									var F;
									if (G === k)
										F = k;
									else {
										a: {
											try {
												F = B.status >= 200
														&& B.status < 300
														|| B.status === 304
														|| B.status === 1223;
												break a
											} catch (H) {
											}
											F = false
										}
										F = F ? c : h
									}
									x = F;
									try {
										F = B;
										var I = l.dataType, M = t, N, L = F;
										if (!a.isString(L)) {
											M = F.getResponseHeader(v) || t;
											L = (N = I === "xml" || !I
													&& M.indexOf("xml") >= 0) ? F.responseXML
													: F.responseText;
											if (N
													&& L.documentElement.nodeName === s)
												throw s;
										}
										if (a.isString(L))
											if (I === q || !I
													&& M.indexOf(q) >= 0)
												L = a.JSON.parse(L);
										A = L
									} catch (O) {
										x = s
									}
									e([ x === c ? c : h, j ], A, x, B, l);
									if (G === k) {
										B.abort();
										d(g, l)
									}
									if (l.async)
										B = null
								}
							};
							d(p, l);
							try {
								B.send(C === n ? l.data : null)
							} catch (J) {
								e([ h, j ], A, h, B, l)
							}
							l.async || d(j, l);
							return B
						}
					}
					function e(l, w, x, A, C) {
						if (a.isArray(l))
							a.each(l, function(E) {
								e(E, w, x, A, C)
							});
						else {
							x === l && C[l] && C[l].call(C.context, w, x, A);
							d(l, C)
						}
					}
					function d(l, w) {
						m.fire(l, {
							ajaxConfig : w
						})
					}
					var f = window, i = function() {
					}, b = "GET", n = "POST", v = "Content-Type", q = "json", u = q
							+ "p", y = "script", t = "", r = "start", p = "send", g = "stop", c = "success", j = "complete", h = "error", k = "timeout", s = "parsererror", z = {
						type : b,
						url : t,
						contentType : "application/x-www-form-urlencoded",
						async : true,
						data : null,
						xhr : f.XMLHttpRequest ? function() {
							return new f.XMLHttpRequest
						} : function() {
							try {
								return new f.ActiveXObject(
										a.UA.ie == 6 ? "Msxml2.XMLHTTP.5.0"
												: "Microsoft.XMLHTTP")
							} catch (l) {
							}
						},
						accepts : {
							xml : "application/xml, text/xml",
							html : "text/html",
							script : "text/javascript, application/javascript",
							json : "application/json, text/javascript",
							text : "text/plain",
							_default : "*/*"
						},
						jsonp : "callback"
					};
					a.mix(m, a.EventTarget);
					a.mix(m, {
						get : function(l, w, x, A, C) {
							if (a.isFunction(w)) {
								A = x;
								x = w
							}
							return m({
								type : C || b,
								url : l,
								data : w,
								success : function(E, D, B) {
									x && x.call(this, E, D, B)
								},
								dataType : A
							})
						},
						post : function(l, w, x, A) {
							if (a.isFunction(w)) {
								A = x;
								x = w;
								w = o
							}
							return m.get(l, w, x, A, n)
						},
						jsonp : function(l, w, x) {
							if (a.isFunction(w)) {
								x = w;
								w = null
							}
							return m.get(l, w, x, u)
						}
					});
					m.getScript = a.getScript;
					a.io = a.ajax = m.ajax = m;
					a.jsonp = m.jsonp;
					a.IO = m
				});
KISSY.add("anim-easing", function(a) {
	var o = Math, m = o.PI, e = o.pow, d = o.sin, f = 1.70158, i = {
		easeNone : function(b) {
			return b
		},
		easeIn : function(b) {
			return b * b
		},
		easeOut : function(b) {
			return (2 - b) * b
		},
		easeBoth : function(b) {
			return (b *= 2) < 1 ? 0.5 * b * b : 0.5 * (1 - --b * (b - 2))
		},
		easeInStrong : function(b) {
			return b * b * b * b
		},
		easeOutStrong : function(b) {
			return 1 - --b * b * b * b
		},
		easeBothStrong : function(b) {
			return (b *= 2) < 1 ? 0.5 * b * b * b * b : 0.5 * (2 - (b -= 2) * b
					* b * b)
		},
		elasticIn : function(b) {
			if (b === 0 || b === 1)
				return b;
			return -(e(2, 10 * (b -= 1)) * d((b - 0.075) * 2 * m / 0.3))
		},
		elasticOut : function(b) {
			if (b === 0 || b === 1)
				return b;
			return e(2, -10 * b) * d((b - 0.075) * 2 * m / 0.3) + 1
		},
		elasticBoth : function(b) {
			if (b === 0 || (b *= 2) === 2)
				return b;
			if (b < 1)
				return -0.5 * e(2, 10 * (b -= 1))
						* d((b - 0.1125) * 2 * m / 0.45);
			return e(2, -10 * (b -= 1)) * d((b - 0.1125) * 2 * m / 0.45) * 0.5
					+ 1
		},
		backIn : function(b) {
			if (b === 1)
				b -= 0.0010;
			return b * b * ((f + 1) * b - f)
		},
		backOut : function(b) {
			return (b -= 1) * b * ((f + 1) * b + f) + 1
		},
		backBoth : function(b) {
			if ((b *= 2) < 1)
				return 0.5 * b * b * (((f *= 1.525) + 1) * b - f);
			return 0.5 * ((b -= 2) * b * (((f *= 1.525) + 1) * b + f) + 2)
		},
		bounceIn : function(b) {
			return 1 - i.bounceOut(1 - b)
		},
		bounceOut : function(b) {
			return b < 1 / 2.75 ? 7.5625 * b * b : b < 2 / 2.75 ? 7.5625
					* (b -= 1.5 / 2.75) * b + 0.75 : b < 2.5 / 2.75 ? 7.5625
					* (b -= 2.25 / 2.75) * b + 0.9375 : 7.5625
					* (b -= 2.625 / 2.75) * b + 0.984375
		},
		bounceBoth : function(b) {
			if (b < 0.5)
				return i.bounceIn(b * 2) * 0.5;
			return i.bounceOut(b * 2 - 1) * 0.5 + 0.5
		}
	};
	i.NativeTimeFunction = {
		easeNone : "linear",
		ease : "ease",
		easeIn : "ease-in",
		easeOut : "ease-out",
		easeBoth : "ease-in-out",
		easeInStrong : "cubic-bezier(0.9, 0.0, 0.9, 0.5)",
		easeOutStrong : "cubic-bezier(0.1, 0.5, 0.1, 1.0)",
		easeBothStrong : "cubic-bezier(0.9, 0.0, 0.1, 1.0)"
	};
	a.Easing = i
});
KISSY
		.add(
				"anim",
				function(a, o) {
					function m(g, c, j, h, k, s) {
						if (g = a.get(g)) {
							if (!(this instanceof m))
								return new m(g, c, j, h, k, s);
							var z = a.isPlainObject(j);
							c = c;
							this.domEl = g;
							if (a.isPlainObject(c))
								c = a.param(c, ";").replace(/=/g, ":").replace(
										/%23/g, "#").replace(/([a-z])([A-Z])/g,
										"$1-$2").toLowerCase();
							var l = {}, w = y.length, x;
							u.innerHTML = '<div style="' + c + '"></div>';
							for (g = u.firstChild.style; w--;)
								if (x = g[y[w]])
									l[y[w]] = f(x);
							this.props = l;
							this.targetStyle = c;
							if (z)
								z = a.merge(p, j);
							else {
								z = a.clone(p);
								if (j)
									z.duration = q(j) || 1;
								if (a.isString(h) || a.isFunction(h))
									z.easing = h;
								if (a.isFunction(k))
									z.complete = k;
								if (s !== o)
									z.nativeSupport = s
							}
							this.config = z;
							if (z.nativeSupport && e()
									&& a.isString(h = z.easing))
								if (/cubic-bezier\([\s\d.,]+\)/.test(h)
										|| (h = v.NativeTimeFunction[h])) {
									z.easing = h;
									this.transitionName = e()
								}
							a.isFunction(k) && this.on(r, k)
						}
					}
					function e() {
						var g = "transition", c;
						if (u.style[g] !== o)
							c = g;
						else
							a.each([ "Webkit", "Moz", "O" ], function(j) {
								if (u.style[g = j + "Transition"] !== o) {
									c = g;
									return false
								}
							});
						e = function() {
							return c
						};
						return c
					}
					function d(g, c, j) {
						a.UA.ie && j.indexOf(t) > -1 && n.css(g, t, c[t].v);
						g.style.cssText += ";" + j
					}
					function f(g) {
						var c = q(g);
						g = (g + "").replace(/^[-\d.]+/, "");
						return isNaN(c) ? {
							v : g,
							u : "",
							f : b
						} : {
							v : c,
							u : g,
							f : i
						}
					}
					function i(g, c, j) {
						return (g + (c - g) * j).toFixed(3)
					}
					function b(g, c, j) {
						for ( var h = 2, k, s, z = [], l = []; k = 3,
								s = arguments[h - 1], h--;)
							if (s.substr(0, 4) === "rgb(")
								for (s = s.match(/\d+/g); k--;)
									z.push(~~s[k]);
							else if (s.substr(0, 1) === "#") {
								if (s.length === 4)
									s = "#" + s.substr(1, 1) + s.substr(1, 1)
											+ s.substr(2, 1) + s.substr(2, 1)
											+ s.substr(3, 1) + s.substr(3, 1);
								for (; k--;)
									z
											.push(parseInt(s.substr(1 + k * 2,
													2), 16))
							} else
								return c;
						for (; k--;) {
							h = ~~(z[k + 3] + (z[k] - z[k + 3]) * j);
							l.push(h < 0 ? 0 : h > 255 ? 255 : h)
						}
						return "rgb(" + l.join(",") + ")"
					}
					var n = a.DOM, v = a.Easing, q = parseFloat, u = n
							.create("<div>"), y = "backgroundColor borderBottomColor borderBottomWidth borderBottomStyle borderLeftColor borderLeftWidth borderLeftStyle borderRightColor borderRightWidth borderRightStyle borderSpacing borderTopColor borderTopWidth borderTopStyle bottom color font fontFamily fontSize fontWeight height left letterSpacing lineHeight marginBottom marginLeft marginRight marginTop maxHeight maxWidth minHeight minWidth opacity outlineColor outlineOffset outlineWidth paddingBottom paddingLeft paddingRight paddingTop right textIndent top width wordSpacing zIndex"
							.split(" "), t = "opacity", r = "complete", p = {
						duration : 1,
						easing : "easeNone",
						nativeSupport : true
					};
					a
							.augment(
									m,
									a.EventTarget,
									{
										run : function() {
											var g = this, c = g.config, j = g.domEl, h, k, s, z, l = g.props, w = {}, x;
											for (x in l)
												w[x] = f(n.css(j, x));
											if (g.fire("start") !== false) {
												g.stop();
												if (g.transitionName)
													g._nativeRun();
												else {
													h = c.duration * 1E3;
													s = a.now();
													z = s + h;
													k = c.easing;
													if (a.isString(k))
														k = v[k] || v.easeNone;
													g.timer = a
															.later(
																	c = function() {
																		var A = a
																				.now(), C = A > z ? 1
																				: (A - s)
																						/ h, E, D, B;
																		for (x in l) {
																			E = w[x];
																			D = l[x];
																			if (D.v == 0)
																				D.u = E.u;
																			if (E.u !== D.u)
																				E.v = 0;
																			n
																					.css(
																							j,
																							x,
																							D
																									.f(
																											E.v,
																											D.v,
																											k(C))
																									+ D.u)
																		}
																		if (g
																				.fire("step") === false
																				|| (B = A > z)) {
																			g
																					.stop();
																			B
																					&& g
																							.fire(r)
																		}
																	}, 13, true);
													c()
												}
												return g
											}
										},
										_nativeRun : function() {
											var g = this, c = g.config, j = g.domEl, h = g.props, k = c.duration * 1E3;
											c = c.easing;
											var s = g.transitionName, z = {};
											a
													.log("Amin uses native transition.");
											z[s + "Property"] = "all";
											z[s + "Duration"] = k + "ms";
											z[s + "TimingFunction"] = c;
											n.css(j, z);
											a.later(function() {
												d(j, h, g.targetStyle)
											}, 0);
											a.later(function() {
												g.stop(true)
											}, k)
										},
										stop : function(g) {
											if (this.transitionName)
												this._nativeStop(g);
											else {
												if (this.timer) {
													this.timer.cancel();
													this.timer = o
												}
												if (g) {
													d(this.domEl, this.props,
															this.targetStyle);
													this.fire(r)
												}
											}
											return this
										},
										_nativeStop : function(g) {
											var c = this.domEl, j = this.transitionName, h = this.props, k;
											if (g) {
												n
														.css(c, j + "Property",
																"none");
												this.fire(r)
											} else {
												for (k in h)
													n.css(c, k, n
															._getComputedStyle(
																	c, k));
												n
														.css(c, j + "Property",
																"none")
											}
										}
									});
					m.supportTransition = function() {
						return !!e()
					};
					a.Anim = m
				});
KISSY
		.add(
				"anim-node-plugin",
				function(a, o) {
					function m(t, r, p, g, c) {
						if (r === "toggle") {
							c = e.css(t, f) === i ? 1 : 0;
							r = "show"
						}
						if (c)
							e.css(t, f, e.data(t, f) || "");
						var j = {}, h = {};
						a.each(y[r],
								function(k) {
									if (k === b) {
										j[b] = e.css(t, b);
										e.css(t, b, n)
									} else if (k === v) {
										j[v] = e.css(t, v);
										h.opacity = c ? 1 : 0;
										c && e.css(t, v, 0)
									} else if (k === q) {
										j[q] = e.css(t, q);
										h.height = c ? e.css(t, q)
												|| t.naturalHeight : 0;
										c && e.css(t, q, 0)
									} else if (k === u) {
										j[u] = e.css(t, u);
										h.width = c ? e.css(t, u)
												|| t.naturalWidth : 0;
										c && e.css(t, u, 0)
									}
								});
						(new a.Anim(t, h, p, "easeOut", function() {
							if (!c) {
								var k = t.style, s = k[f];
								if (s !== i) {
									s && e.data(t, f, s);
									k[f] = i
								}
								j[q] && e.css(t, {
									height : j[q]
								});
								j[u] && e.css(t, {
									height : j[u]
								});
								j[v] && e.css(t, {
									height : j[v]
								});
								j[b] && e.css(t, {
									height : j[b]
								})
							}
							g && a.isFunction(g) && g()
						})).run()
					}
					var e = a.DOM, d = a.Anim, f = "display", i = "none", b = "overflow", n = "hidden", v = "opacity", q = "height", u = "width", y = {
						show : [ b, v, q, u ],
						fade : [ v ],
						slide : [ b, q ]
					};
					a.each([ a.Node.prototype, a.NodeList.prototype ],
							function(t) {
								t.animate = function() {
									var r = a.makeArray(arguments);
									a.each(this, function(p) {
										d.apply(o, [ p ].concat(r)).run()
									});
									return this
								};
								a.each({
									show : [ "show", 1 ],
									hide : [ "show", 0 ],
									toggle : [ "toggle" ],
									fadeIn : [ "fade", 1 ],
									fadeOut : [ "fade", 0 ],
									slideDown : [ "slide", 1 ],
									slideUp : [ "slide", 0 ]
								}, function(r, p) {
									t[p] = function(g, c) {
										e[p] && arguments.length === 0 ? e[p]
												(this) : a.each(this, function(
												j) {
											m(j, r[0], g, c, r[1])
										});
										return this
									}
								})
							})
				});
KISSY.add("cookie", function(a) {
	var o = document, m = encodeURIComponent, e = decodeURIComponent;
	a.Cookie = {
		get : function(d) {
			var f;
			if (a.isString(d) && d !== "")
				if (d = o.cookie.match("(?:^| )" + d + "(?:(?:=([^;]*))|;|$)"))
					f = d[1] ? e(d[1]) : "";
			return f
		},
		set : function(d, f, i, b, n, v) {
			f = m(f);
			var q = i;
			if (typeof q === "number") {
				q = new Date;
				q.setTime(q.getTime() + i * 864E5)
			}
			if (q instanceof Date)
				f += "; expires=" + q.toUTCString();
			if (a.isString(b) && b !== "")
				f += "; domain=" + b;
			if (a.isString(n) && n !== "")
				f += "; path=" + n;
			if (v)
				f += "; secure";
			o.cookie = d + "=" + f
		},
		remove : function(d, f, i, b) {
			this.set(d, "", 0, f, i, b)
		}
	}
});
KISSY.add("attribute", function(a, o) {
	function m() {
		this.__attrs = {};
		this.__attrVals = {}
	}
	function e(d) {
		d += "";
		return d.charAt(0).toUpperCase() + d.substring(1)
	}
	a.augment(m, {
		__getDefAttrs : function() {
			return a.clone(this.__attrs)
		},
		addAttr : function(d, f) {
			this.__attrs[d] = a.clone(f || {});
			return this
		},
		addAttrs : function(d, f) {
			var i = this;
			a.each(d, function(b, n) {
				if (n in f)
					b.value = f[n];
				i.addAttr(n, b)
			});
			return i
		},
		hasAttr : function(d) {
			return d && d in (this.__attrs || {})
		},
		removeAttr : function(d) {
			if (this.hasAttr(d)) {
				delete this.__attrs[d];
				delete this.__attrVals[d]
			}
			return this
		},
		set : function(d, f) {
			var i = this.get(d);
			if (i !== f)
				if (false !== this.__fireAttrChange("before", d, i, f)) {
					this.__set(d, f);
					this.__fireAttrChange("after", d, i, this.__attrVals[d]);
					return this
				}
		},
		__fireAttrChange : function(d, f, i, b) {
			return this.fire(d + e(f) + "Change", {
				attrName : f,
				prevVal : i,
				newVal : b
			})
		},
		__set : function(d, f) {
			var i, b = this.__attrs[d];
			if (b = b && b.setter)
				i = b.call(this, f);
			if (i !== o)
				f = i;
			this.__attrVals[d] = f
		},
		get : function(d) {
			var f;
			f = (f = this.__attrs[d]) && f.getter;
			d = d in this.__attrVals ? this.__attrVals[d] : this
					.__getDefAttrVal(d);
			if (f)
				d = f.call(this, d);
			return d
		},
		__getDefAttrVal : function(d) {
			d = this.__attrs[d];
			var f;
			if (d) {
				if (f = d.valueFn) {
					f = f.call(this);
					if (f !== o)
						d.value = f;
					delete d.valueFn
				}
				return d.value
			}
		},
		reset : function(d) {
			if (this.hasAttr(d))
				return this.set(d, this.__getDefAttrVal(d));
			for (d in this.__attrs)
				this.hasAttr(d) && this.reset(d);
			return this
		}
	});
	a.Attribute = m;
	m.__capitalFirst = e
});
KISSY.add("base", function(a) {
	function o(m) {
		a.Attribute.call(this);
		for ( var e = this.constructor; e;) {
			var d = e.ATTRS;
			if (d) {
				var f = void 0;
				for (f in d)
					d.hasOwnProperty(f) && !this.hasAttr(f)
							&& this.addAttr(f, d[f])
			}
			e = e.superclass ? e.superclass.constructor : null
		}
		if (m)
			for ( var i in m)
				m.hasOwnProperty(i) && this.__set(i, m[i])
	}
	a.augment(o, a.EventTarget, a.Attribute);
	a.Base = o
});
KISSY.add("net", function(a, o) {
	function m(b) {
		this._init(b)
	}
	var e = function() {
	}, d = {
		proxy : {}
	}, f = a.DOM, i = a.Event.on;
	a.augment(m, {
		url : "",
		async : true,
		data : "",
		onStart : e,
		onSuccess : null,
		onFailure : null,
		onComplete : e,
		method : "post",
		xhr : null,
		header : null,
		startEventObj : {
			notice : "\u6b63\u5728\u52a0\u8f7d..."
		},
		completeEventObj : {},
		_init : function(b) {
			a.mix(this, b);
			b = this.data;
			if (window.DDformKey)
				b.formKey = window.DDformKey;
			else
				a.log("\u8bf7\u6dfb\u52a0formKey");
			if (a.isPlainObject(b))
				this.data = a.param(b)
		},
		getXhr : function() {
			var b = this.url, n, v = this, q;
			n = f.create("<a>", {
				href : b
			}).hostname;
			if (n != location.hostname && /^http/.test(b))
				if (d.proxy[n]) {
					v.xhr = d.proxy[n].contentWindow.getXhr();
					v._send()
				} else {
					q = f.create("<iframe>", {
						src : "http://" + n + "/proxy.html",
						width : 0,
						height : 0,
						frameborder : 0
					});
					document.body.appendChild(q);
					i(q, "load", function() {
						d.proxy[n] = q;
						v.xhr = q.contentWindow.getXhr();
						v._send()
					})
				}
			else {
				if (a.UA.ie)
					try {
						v.xhr = new ActiveXObject("Msxml2.XMLHTTP")
					} catch (u) {
						v.xhr = new ActiveXObject("Microsoft.XMLHTTP")
					}
				else
					v.xhr = new XMLHttpRequest;
				v._send()
			}
		},
		abort : function() {
			this.xhr.abort()
		},
		send : function() {
			this.getXhr()
		},
		_send : function() {
			var b = this.url, n = this.method, v = null, q = this;
			if (n == "get" && this.data)
				b = this.url + (/\?/.test(this.url) ? "&" : "?") + this.data;
			else
				v = this.data;
			this.xhr.onreadystatechange = function() {
				q._onStateChange()
			};
			this.xhr.open(n, b, this.async);
			this.xhr.setRequestHeader("Content-Type",
					"application/x-www-form-urlencoded");
			if (a.isPlainObject(this.header))
				for ( var u in this.header)
					this.xhr.setRequestHeader(u, this.header[u]);
			this.xhr.send(v)
		},
		_onStateChange : function() {
			var b = this.xhr;
			if (b.readyState == 1 && !this._hasStart) {
				this.onStart();
				d.fire("start", {
					startEventObj : this.startEventObj
				});
				this._hasStart = true
			} else if (b.readyState == 4) {
				b.status == o || b.status == 0 || b.status >= 200
						&& b.status < 300 || b.status == 1223
						|| b.status == 304 ? this._onSuccess() : (this.onError
						|| this.onFailure || e).call(this, b);
				d.fire("complete", {
					completeEventObj : this.comleteEventObj
				});
				this._onComplete()
			}
		},
		_onSuccess : function() {
			this.onSuccess(this.xhr)
		},
		_onComplete : function() {
			this.onComplete(this.xhr)
		}
	});
	a.mix(d, {
		XmlHttpRequest : m,
		post : function(b) {
			b.method = "post";
			b = new m(b);
			b.send();
			return b
		},
		get : function(b) {
			b.method = "get";
			b = new m(b);
			b.send();
			return b
		}
	});
	a.mix(d, a.EventTarget);
	a.net = d
});
KISSY.add("ks-core");

package com.smit.util;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SmitPageTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private SmitPage pager;

	public int doStartTag() {

		try {
			//pageContext.setAttribute(SmitPage.CUR_PAGE, pager.getPageIndex()); // set current page
			pageContext.setAttribute(SmitPage.CUR_PAGE, pager.getPageIndex(), 3);
			JspWriter out = pageContext.getOut();

			out.print("第 " + pager.getPageIndex() + " 页/共 "
					+ (pager.getPageCount()) + " 页");

			if (pager.getPageIndex() == 1) {
				out.print("[最首页]");
				out.print("[上一页]");
			}

			if (pager.getPageIndex() != 1) {
				out.print("[<a href='" + pager.getUrl() + "&cpage=1'>最首页</a>]");
				out.print("[<a href='" + pager.getUrl() + "&cpage="
						+ (pager.getPageIndex() - 1) + "'>上一页</a>]");
			}

			for (int i = pager.getPageIndex() - 3; i <= pager.getPageIndex() + 3; i++) {
				if (i <= 0 || i > pager.getPageCount()) {
					continue;
				}
				if (i == pager.getPageIndex()) {
					out.print("[<span style='color:#FF0000; border: 1px solid #cccccc; font-weight:bold; width:15px;text-align: center;'> "
							+ i + " </span>]");
				} else {
					out.print("[<a href='" + pager.getUrl() + "&cpage=" + i
							+ "'> " + i + " </a>]");
				}
			}
			if (pager.getPageIndex() == pager.getPageCount()
					|| pager.getPageCount() == 0) {
				out.print("[下一页]");
				out.print("[最末页]");

			}
			if (pager.getPageIndex() != pager.getPageCount()
					&& pager.getPageCount() != 0) {
				out.print("[<a href='" + pager.getUrl() + "&cpage="
						+ (pager.getPageIndex() + 1) + "'>下一页</a>]");
				out.print("[<a href='" + pager.getUrl() + "&cpage="
						+ (pager.getPageCount()) + "'>最末页</a>]");
			}
			out.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return super.SKIP_BODY;
	}

	public int doEndTag() {
		return super.EVAL_PAGE;
	}

	public SmitPage getPager() {
		return pager;
	}

	public void setPager(SmitPage page) {
		this.pager = page;
	}

}

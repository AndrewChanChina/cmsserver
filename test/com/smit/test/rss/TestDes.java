package com.smit.test.rss;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TestDes {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		String str = "<a href='http://www.youku.com/v_show/id_XMjc3NjcwNzAw_rss.html'>" +
//				"<img src='http://g1.ykimg.com/0100641F464DFE6728DF0B007BF2DA3A5304DB-6249-2D02-88AE-55103D736E5A' border='0' width='120' height='90' vspace='4' hspace='4' title='醉后决定爱上你10' target='_blank' /></a>" +
//						" <a href='http://www.youku.com/v_show/id_XMjc3NjcwNzAw_rss.html'><img src='http://g1.ykimg.com/0100641F464DFE67286722007BF2DAC46C1CD9-C384-C6AA-FEF0-6A140F201547' border='0' width='120' height='90' vspace='4' hspace='4' title='醉后决定爱上你10' target='_blank' /></a> " +
//								"<a href='http://www.youku.com/v_show/id_XMjc3NjcwNzAw_rss.html'><img src='http://g1.ykimg.com/0100641F464DFE67285B68007BF2DA9CD80E73-0DDB-4BC0-C622-BE3523395165' border='0' width='120' height='90' vspace='4' hspace='4' title='醉后决定爱上你10' target='_blank' /></a> <p>醉后决定爱上你10</p>";
//		System.out.println(str.length());
//		Document doc = DocumentHelper.parseText("<root>"+str+"</root>");
//		System.out.println(doc.getRootElement().getName());
//		System.out.println(doc.getRootElement().elements("a").size());
//		List<Element> list = doc.getRootElement().elements("a");
//		String img = list.get(0).element("img").attributeValue("src");
//		System.out.println(img);
		String atom = "<feed xmlns='http://www.w3.org/2005/Atom'>" +
				"<title>Example Feed</title><link href='http://example.org/'/>" +
				"<updated>2003-12-13T18:30:02Z</updated>" +
				"<author> <name>John Doe</name></author>" +
				"<id>urn:uuid:60a76c80-d399-11d9-b93C-0003939e0af6</id>" +
				"<entry><title>Atom-Powered Robots Run Amok</title>" +
				"<link href='http://example.org/2003/12/13/atom03'/>" +
				"<id>urn:uuid:1225c695-cfb8-4ebb-aaaa-80da344efa6a</id>" +
				"<updated>2003-12-13T18:30:02Z</updated>" +
				"<summary>Some text.</summary></entry>" +
				"</feed>";
		Document doc = DocumentHelper.parseText(atom);
		Element root = doc.getRootElement();
		System.out.println(root.getName());

	}

}

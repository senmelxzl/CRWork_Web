package com.crwork.web.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupCityService {
	private static Map<Integer, String> cssMap = new HashMap<Integer, String>();
	private static BufferedWriter bufferedWriter = null;

	static {
		cssMap.put(1, "provincetr");// ʡ
		cssMap.put(2, "citytr");// ��
		cssMap.put(3, "countytr");// ��
		cssMap.put(4, "towntr");// ��
		cssMap.put(5, "villagetr");// ��
	}

	public static void main(String[] args) throws IOException {
		int level = 1;

		initFile();

		// ��ȡȫ������ʡ����Ϣ
		Document connect = connect("http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/");
		Elements rowProvince = connect.select("tr." + cssMap.get(level));
		for (Element provinceElement : rowProvince)// ����ÿһ�е�ʡ�ݳ���
		{
			Elements select = provinceElement.select("a");
			for (Element province : select)// ÿһ��ʡ��(�Ĵ�ʡ)
			{
				parseNextLevel(province, level + 1);
			}
		}

		closeStream();
	}

	private static void initFile() {
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(new File("d:\\CityInfo.txt"), true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void closeStream() {
		if (bufferedWriter != null) {
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bufferedWriter = null;
		}
	}

	private static void parseNextLevel(Element parentElement, int level) throws IOException {
		try {
			Thread.sleep(500);// ˯��һ�£�������ܳ��ָ��ִ���״̬��
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Document doc = connect(parentElement.attr("abs:href"));
		if (doc != null) {
			Elements newsHeadlines = doc.select("tr." + cssMap.get(level));//
			// ��ȡ����һ������
			for (Element element : newsHeadlines) {
				printInfo(element, level + 1);
				Elements select = element.select("a");// �ڵݹ���õ�ʱ���������ж��Ƿ��Ǵ�һ�������ݣ���һ��������û��a��ǩ
				if (select.size() != 0) {
					parseNextLevel(select.last(), level + 1);
				}
			}
		}
	}

	/**
	 * дһ�����ݵ������ļ���ȥ
	 * 
	 * @param element
	 *            ��ȡ��������Ԫ��
	 * @param level
	 *            ���м���
	 */
	private static void printInfo(Element element, int level) {
		try {
			bufferedWriter.write(element.select("td").last().text() + "{" + level + "}["
					+ element.select("td").first().text() + "]");
			bufferedWriter.newLine();
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Document connect(String url) {
		if (url == null || url.isEmpty()) {
			throw new IllegalArgumentException("The input url('" + url + "') is invalid!");
		}
		try {
			return Jsoup.connect(url).timeout(100 * 1000).get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

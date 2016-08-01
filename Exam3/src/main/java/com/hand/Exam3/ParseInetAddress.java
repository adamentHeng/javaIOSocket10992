package com.hand.Exam3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ParseInetAddress {

	static void parseXML(String s) throws ParserConfigurationException, TransformerException{
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element root = document.createElement("stock");
		Element element1 = document.createElement("name");
		Element element2 = document.createElement("open");
		Element element3 = document.createElement("close");
		Element element4 = document.createElement("current");
		Element element5 = document.createElement("high");
		Element element6 = document.createElement("low");
		String[] arr = s.split(",");
		String name = arr[0].split("\"")[1];
		element1.setTextContent(name);
		element2.setTextContent(arr[1]);
		element3.setTextContent(arr[2]);
		element4.setTextContent(arr[3]);
		element5.setTextContent(arr[4]);
		element6.setTextContent(arr[5]);
		root.appendChild(element1);
		root.appendChild(element2);
		root.appendChild(element3);
		root.appendChild(element4);
		root.appendChild(element5);
		root.appendChild(element6);
		document.appendChild(root);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty("encoding", "UTF-8");
		
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(document), new StreamResult(writer));
		System.out.println(writer.toString());
		
		transformer.transform(new DOMSource(document), new StreamResult(new File("myxml.xml")));
	}
	
	static void parseJSON(String s) throws IOException{
		String[] arr = s.split(",");
		String name = arr[0].split("\"")[1];
		JSONObject jsonObject = new JSONObject();
		jsonObject.add("name", name);
		jsonObject.add("open", arr[1]);
		jsonObject.add("close", arr[2]);
		jsonObject.add("current", arr[3]);
		jsonObject.add("high", arr[4]);
		jsonObject.add("low", arr[5]);
		String res = jsonObject.toString();
		System.out.println(res);	
		File file = new File("myjson.json");
		if( !file.exists() )
			file.createNewFile();
		OutputStream outputStream = new FileOutputStream(file);
		outputStream.write(res.getBytes());
		outputStream.close();
	}
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		URL url = new URL("http://hq.sinajs.cn/list=sz300170");
		URLConnection connection = url.openConnection();
		InputStream inputStream = connection.getInputStream();
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		String s = "";
		byte[] buff = new byte[1024];
		int len = 0;
		while( (len = bufferedInputStream.read(buff)) > 0 ) {
			
			s += new String(buff,0,len,"GBK");
		}
		bufferedInputStream.close();
		inputStream.close();
		parseXML(s);
		parseJSON(s);
	}

}

package com.zxy.baiduspider;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.Header;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.entity.mime.Header;

public class BaiduSpider {
		public static HttpClient httpClient = new DefaultHttpClient();
		
		public static void main(String[] args){
				try {
					BaiduSpider.simulateLogin("http://59.77.226.32/logincheck.asp");
					BaiduSpider.logIn("http://59.77.226.35/top.aspx?id=2016711620246312");
					//BaiduSpider.logIn("http://59.77.226.35//student/xyzk/cjyl/score_sheet.aspx?id=20167116151160618");
					//BaiduSpider.logIn("http://jwch.fzu.edu.cn/html/");
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		public static boolean simulateLogin(String path)throws Exception{
				HttpPost post = new HttpPost(path);
				ArrayList<NameValuePair> data = new ArrayList<>();
				data.add(new BasicNameValuePair("muser", "031402231"));
				data.add(new BasicNameValuePair("passwd", "060031"));

				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(data,"GBK");
				post.setEntity(entity);
				
				HttpResponse response = httpClient.execute(post);
				HttpEntity entity2 = response.getEntity();
				org.apache.http.Header header = entity2.getContentType();
				//header.getElements();
				System.out.println(response.getStatusLine().toString());
				if(entity2 != null){
					OutputStream output = new FileOutputStream("E:\\2.txt");
					entity2.writeTo(output);
					System.out.println("大概，也许，成功了？");
					return true;
				}
				System.out.println("失败。。");
				return false;
				
		}
		
		public static void logIn(String path)throws Exception{
				HttpGet get = new HttpGet(path);
				
				HttpResponse response = httpClient.execute(get);
				HttpEntity entity = response.getEntity();
				if(entity!= null){
						System.out.println("再次获取成功");
						OutputStream output = new FileOutputStream("E:\\3.txt");
						entity.writeTo(output);
				}
		}
		
		
		public static boolean downloadPage(String path)throws Exception{
					HttpGet get  = new HttpGet(path);
					HttpResponse  response = httpClient.execute(get);
					HttpEntity entity = response.getEntity();
					if(entity!=null){
							InputStream input = entity.getContent();	  //返回一个输入流，或者直接输出到指定的输出流中
							//OutputStream output =  new FileOutputStream("E:\\1.txt");
							//entity.writeTo(output);
							int n;
							System.out.println("成功啦啦啦啦啦");
							byte[] b = new byte[2048];
							while((n = input.read(b))!=-1){
									String str = new String(b);
									System.out.println(str+"\n");
							}
							return true;
					}
					return false;
				
			
		}
}

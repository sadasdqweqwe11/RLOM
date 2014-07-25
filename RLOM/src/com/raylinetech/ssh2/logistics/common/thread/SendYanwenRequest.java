package com.raylinetech.ssh2.logistics.common.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.util.XmlUtil;

public class SendYanwenRequest implements Runnable{

	private RLOrder rlOrder;
	
	public RLOrder getRlOrder() {
		return rlOrder;
	}

	public void setRlOrder(RLOrder rlOrder) {
		this.rlOrder = rlOrder;
	}

	public SendYanwenRequest(RLOrder rlOrder) {
		this.rlOrder = rlOrder;
	}

	@Override
	public void run() {
		String url="http://online.yw56.com.cn/service_sandbox/Users/100000/Expresses";  
        try {
            URL server=new URL(url);  
            HttpURLConnection connection = (HttpURLConnection) server.openConnection();  
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            connection.setRequestProperty("Authorization", "basic MTAwMDAwOjEwMDAwMQ==");
            //connection.connect();  
            connection.setDoOutput(true);
            connection.setDoInput(true);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(),"utf-8");
            PrintWriter writer = new PrintWriter(out);
            writer.print(XmlUtil.test());
            writer.flush();
            writer.close();
            
            
            StringBuilder content = new StringBuilder();  
            InputStream is = connection.getInputStream();  
            String curLine = "";  
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));  
            while ((curLine = reader.readLine()) != null) {  
                content.append(curLine);  
            }  
            is.close();  
            System.out.println(content.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  		
	}

	public static void main(String[] args) {
		long millis = System.currentTimeMillis();
		System.out.println(millis);
		for (int i = 0; i < 300; i++) {
			Thread th = new Thread(new SendYanwenRequest(null));
			th.start();
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long millis2 = System.currentTimeMillis();
		System.out.println(millis2);
		System.out.println(millis2-millis);
		
	}
}

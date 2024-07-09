package jp.aka.sample;

import java.io.IOException;

import com.jutil.Http.HttpWrapper;
import com.jutil.Logger.Logger;

import jp.vstone.RobotLib.CRobotUtil;

public class HelloWorld {
	private static String TAG = "HELLO_WORLD";
	public static void main(String[] args) {
		CRobotUtil.Log(TAG, "!");
		CRobotUtil.Err(TAG, "?");
		
		Logger.info(TAG, "!!!");
		
		String url = "http://192.168.1.41:8000";
		try {
			String response = HttpWrapper.createGetReq(url);
			Logger.info(TAG, "response", response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

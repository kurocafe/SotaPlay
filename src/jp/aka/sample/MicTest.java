package jp.aka.sample;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.jutil.Http.HttpWrapper;
import com.jutil.Logger.Logger;

import jp.aka.sample.JSON.JSONMapper;
import jp.aka.sample.values.GenerateReq;
import jp.aka.sample.values.SpReqRes;
import jp.vstone.RobotLib.*;
import jp.vstone.camera.CameraCapture;
/**
 * VSMDを使用し、モーション再生・音声再生するサンプル
 * @author Vstone
 *
 */
public class MicTest {
	static final String TAG = "MotionSample";
	static final String RECPATH = "./test_rec.wav";
	static final String API_HOME = "http://192.168.1.41:8000";
	public static void main(String args[]){
		CRobotUtil.Log(TAG, "Start " + TAG);

		//音声ファイル録音
		CRobotUtil.Log(TAG, "Mic Recording Test");
		CPlayWave.PlayWave_wait("sound/start_rec_test.wav");
		CRecordMic mic = new CRecordMic();
		mic.startRecording(RECPATH,5000);
		CRobotUtil.Log(TAG, "wait end");
		mic.waitend();
		
		String url = API_HOME + "/sp_rec";
		SpReqRes response = null;
		try {
			response = JSONMapper.mapper.readValue(HttpWrapper.uploadFile(RECPATH, url), SpReqRes.class);

			Logger.info(TAG, response.getResponse());
			
			GenerateReq request_gen = new GenerateReq();
			request_gen.setUser_message(response.getResponse());
			
			String url2 = API_HOME + "/generate";
			String response_gen = null;
			response_gen = HttpWrapper.sendJSON(JSONMapper.mapper.writeValueAsString(request_gen), url2);
			

			Logger.info(TAG, response_gen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		CRobotUtil.Log(TAG, "Spk Play Test");
		//音声ファイル再生
		CPlayWave.PlayWave_wait("./test_rec.wav");
	    
		
		
		//音声ファイル再生
		//raw　Waveファイルのみ対応
		CPlayWave.PlayWave("sound/end_test.wav");
		CRobotUtil.wait(2000);
		
	}
}

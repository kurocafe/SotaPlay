package jp.vstone.sotatest;

import java.awt.image.BufferedImage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.jutil.Http.HttpWrapper;

import jp.aka.sample.JSON.JSONMapper;
import jp.aka.sample.values.SpReqRes;
import jp.vstone.RobotLib.CRobotUtil;
import jp.vstone.camera.CameraCapture;

public class QRCodeTest {

	public static void main(String[] args) {
		CameraCapture cap = null;
		cap = new CameraCapture(CameraCapture.CAP_IMAGE_SIZE_VGA, CameraCapture.CAP_FORMAT_3BYTE_BGR);
		try {
			cap.openDevice("/dev/video0");
			cap.snapGetFile("./test3.jpg");
			cap.close();
			
			String url = API_HOME + "/sp_rec";
			SpReqRes response = null;
			response = JSONMapper.mapper.readValue(HttpWrapper.uploadFile(RECPATH, url), SpReqRes.class);
//	        	cap.snap();
//	        	CRobotUtil.wait(1000);
//	        	cap.snap();
//
//		    //. 画像読み込み
//	        	BufferedImage image = cap.RawtoBufferedImage();
//	        	LuminanceSource source = new BufferedImageLuminanceSource( image );
//	        	BinaryBitmap bitmap = new BinaryBitmap( new HybridBinarizer( source ) );
//
//	        	//. デコード
//	        	Reader reader = new MultiFormatReader();
//	        	Result result = reader.decode( bitmap );
//
//	        	//. バーコードフォーマット
//	        	BarcodeFormat format = result.getBarcodeFormat();
//
//	        	//. バーコードコンテンツ（読み取り結果）
//	        	String text = result.getText();
//	        	System.out.println("result2 " + text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

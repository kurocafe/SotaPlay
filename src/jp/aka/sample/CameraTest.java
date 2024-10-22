package jp.aka.sample;

import com.jutil.Http.HttpWrapper;

import jp.aka.sample.JSON.JSONMapper;
import jp.aka.sample.values.QRReadRes;
import jp.aka.sample.values.SpReqRes;
import jp.vstone.RobotLib.CRobotUtil;
import jp.vstone.camera.CameraCapture;

public class CameraTest {
	static String API_HOME = "http://150.59.20.116:8800";
	static String QR_PATH = "./qr.jpg";
	
	public static void main(String args[]) {
		CameraCapture cap = null;
		cap = new CameraCapture(CameraCapture.CAP_IMAGE_SIZE_HD_1080, CameraCapture.CAP_FORMAT_3BYTE_BGR);
		try {
			cap.openDevice("/dev/video0");
			int count = 0;
			while (count < 60) {
				cap.snapGetFile(QR_PATH);
				String url = API_HOME + "/qr_read";
				QRReadRes response = null;
				response = JSONMapper.mapper.readValue(HttpWrapper.uploadFile(QR_PATH, url), QRReadRes.class);
				CRobotUtil.Log(response.getResponse(), "wait end");
				
				if (response.getResponse() != null) {
					break;
				}
		       CRobotUtil.wait(1000);
		       count++; 
			}
					

//	        	cap.snap();
				cap.close();
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

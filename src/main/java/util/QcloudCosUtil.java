package util;

import java.io.File;
import java.io.InputStream;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;

public class QcloudCosUtil {
	private static COSClient cosClient;
	private static String bucketName = "bucket-1253319037";

	static {
		COSCredentials cosCredentials = new BasicCOSCredentials("AKIDPn393lzFcnmcGnnV7WknofR1QcdSmjld",
				"vfDk5EMKzZ1GKB73xtki1NjwwjQwZ6Sc");
		ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
		cosClient = new COSClient(cosCredentials, clientConfig);
	}

	/**
	 * 保存File到腾讯云OOS
	 * 
	 * @param file
	 * @param key
	 * @return
	 */
	public static String saveToQcloud(File file, String key) {
		cosClient.putObject(bucketName, key, file);
		System.out.println(TimeUtil.getTime() + " qcloud-cos-save-path: " + key);
		return "https://" + bucketName + ".cosbj.myqcloud.com/" + key;
	}

	/**
	 * 保存InputStream到腾讯云OOS
	 * 
	 * @param inputStream
	 * @param key
	 * @param contentLength
	 * @return
	 */
	public static String saveToQcloud(InputStream inputStream, String key, long contentLength) {
		String bucketName = "weather-1253319037";
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(contentLength);
		cosClient.putObject(bucketName, key, inputStream, objectMetadata);
		System.out.println(TimeUtil.getTime() + " qcloud-cos-save-path: " + key);
		return "https://" + bucketName + ".cosbj.myqcloud.com/" + key;
	}

}

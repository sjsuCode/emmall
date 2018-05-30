package com.ssj.fastdfstest;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;


public class FastdfsTest {

	@Test
	public void TestUpLoad() throws Exception {
		ClientGlobal.init("I:/eclipse-workspace/emall-manager-web/src/test/resources/conf/fastdfs.conf");
		TrackerClient trackerClient = new TrackerClient();
		TrackerServer trackerServer = trackerClient.getConnection();
		StorageServer storageServer = null;
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		String[] ss=storageClient.upload_file(
				"C:\\Users\\sushijun\\Pictures\\sj\\timg.jpg", "jpg", null);
		
		for(String s:ss)
		System.out.println(s);
		
	}
}

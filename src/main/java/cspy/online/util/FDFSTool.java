package cspy.online.util;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * @author CSpy
 * @date 2019/4/24 11:32
 */
public class FDFSTool {

    static {
        ClassPathResource cpr = new ClassPathResource("client.conf");
        try {
            ClientGlobal.init(cpr.getClassLoader().getResource("client.conf").getPath());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static String uploadFile(byte[] fileBytes, String fileExtName, NameValuePair[] metaList) throws IOException, MyException {
//        String fileExtName = tempFileName.getName().substring(tempFileName.getName().indexOf("."));

        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);

        return client1.upload_file1(fileBytes, fileExtName, metaList);
    }

    public static int deleteFile(String fileId) throws IOException, MyException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);

        return client1.delete_file1(fileId);

    }

}

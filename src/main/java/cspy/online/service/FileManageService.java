package cspy.online.service;

import cspy.online.bean.ResponseMessage;
import cspy.online.bean.SCFile;
import cspy.online.util.FDFSTool;
import org.csource.common.MyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CSpy
 * @date 2019/5/6 23:13
 */
@Service
public interface FileManageService {

    public ResponseMessage removeFile(String path, String fileNameListStr);
    public ResponseMessage createDirectory(String rootPath, String dirName);
    public ResponseMessage updateFile(String path, MultipartFile file);
    public ResponseMessage removeDirectory(String path);
    public ResponseMessage moveFile(String requestBody);
}

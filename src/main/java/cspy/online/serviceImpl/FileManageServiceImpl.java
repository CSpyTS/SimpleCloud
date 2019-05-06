package cspy.online.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import cspy.online.bean.FileMoveWrapper;
import cspy.online.bean.ResponseMessage;
import cspy.online.bean.SCFile;
import cspy.online.bean.SimpleFile;
import cspy.online.dao.FileMapper;
import cspy.online.service.FileManageService;
import cspy.online.service.FileSearchService;
import cspy.online.util.FDFSTool;
import cspy.online.util.FileTool;
import org.apache.tika.Tika;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CSpy
 * @date 2019/5/6 23:21
 */
public class FileManageServiceImpl implements FileManageService {

    @Autowired
    FileMapper fileMapper;


    @Override
    public ResponseMessage removeFile(String path, String fileNameListStr)  {
        ResponseMessage message = new ResponseMessage();
        System.out.println(fileNameListStr);

        String[] split = fileNameListStr.split(",");
        System.out.println("split = " + split.length);

        List<SCFile> unRemovedFiles = new ArrayList<>();
        int count = 0;
        for (String sp : split) {


            // 如果当前子文件是目录的话
            SCFile file = fileMapper.selectFile(path, sp);

            if (file != null) {
                if (file.getType().equals("文件夹")) {
                    // 如果是文件夹，则将该文件夹及其子文件夹全部删除
                    String rootPath = file.getPath() + "/" + file.getFilename();

                    unRemovedFiles.addAll(fileMapper.selectChildren(rootPath));
                    count += fileMapper.deletePath(rootPath);
                    count += fileMapper.deleteDirectory(path, sp);

                } else {
                    // 不是文件夹，则只删除此文件
                    unRemovedFiles.add(file);
                    count += fileMapper.deleteOneFile(path, sp);
                }


            }
        }
        try {
            FDFSTool.deleteFiles(unRemovedFiles);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        message.setState(true);
        message.setMsg("删除成功，共删除" + count + "条数据! FDFS删除了：" + unRemovedFiles.size() + "个文件。");

        return message;
    }

    @Override
    public ResponseMessage createDirectory(String rootPath, String dirName) {
        System.out.println("rootPath: " + rootPath + " dirName: " + dirName);

        ResponseMessage message = new ResponseMessage();
        if (dirName != null && dirName.length() != 0) {
            int sameDirCount = fileMapper.selectDirectory(rootPath, dirName);
            if (sameDirCount == 0) {
                SCFile scFile = new SCFile();
                scFile.setType("文件夹");
                scFile.setPath(rootPath);
                scFile.setFilename(dirName);
                scFile.setGmtCreate(new Timestamp(System.currentTimeMillis()));
                scFile.setGmtModify(new Timestamp(System.currentTimeMillis()));
                int i = fileMapper.insertFile(scFile);
                if (i != -1) {
                    message.setState(true);
                    message.setMsg("文件夹创建成功。");
                    return message;
                } else {
                    message.setState(false);
                    message.setMsg("文件夹创建失败，未知错误！");
                }
            } else {
                message.setState(false);
                message.setMsg("文件夹创建失败，文件夹已存在！");
            }
        } else {
            message.setState(false);
            message.setMsg("文件夹创建失败，文件名不能为空！");
        }
        return message;
    }

    @Override
    public ResponseMessage updateFile(String path, MultipartFile file) {
        ResponseMessage message = new ResponseMessage();

        if (file.isEmpty()) {
            message.setState(false);
            message.setMsg("文件为空！");
        } else {
            try {
                File normalFile = FileTool.byteToFile(file.getBytes());

//                判断文件类型，如果是视频或者图片的话，则制作出缩略图
                Tika tika = new Tika();
                String type = tika.detect(normalFile);

                File smallImage = null;

                if (type.startsWith("video")) {
                    BufferedImage frame = FileTool.getFrame(normalFile);
                    smallImage = FileTool.getSmallImage(frame);
                } else if (type.startsWith("image")) {
                    smallImage = FileTool.getSmallImage(ImageIO.read(normalFile));
                }

                String tempFileName = file.getOriginalFilename();

                byte[] fileBytes = file.getBytes();
                String fileExtName = tempFileName.substring(tempFileName.indexOf("."));


                NameValuePair[] metaList = new NameValuePair[3];
                metaList[0] = new NameValuePair("fileName", tempFileName);
                metaList[1] = new NameValuePair("fileExtName", fileExtName);
                metaList[2] = new NameValuePair("size", String.valueOf(tempFileName.length()));
                String fileId = FDFSTool.uploadFile(fileBytes, fileExtName, metaList);


                SCFile scFile = new SCFile();
                scFile.setGmtModify(new Timestamp(System.currentTimeMillis()));
                scFile.setGmtCreate(new Timestamp(System.currentTimeMillis()));
                scFile.setType(tika.detect(normalFile));
                scFile.setFilename(file.getOriginalFilename());
                scFile.setSize(normalFile.length());
                scFile.setFdfsId(fileId);
                if (smallImage != null) {
                    metaList[1] = new NameValuePair("fileExtName", "jpg");
                    String smallFileId = FDFSTool.uploadFile(FileTool.fileToByte(smallImage), "jpg", metaList);
                    scFile.setIcon(smallFileId);
                }
                scFile.setPath(path);
                fileMapper.insertFile(scFile);


                System.out.println(fileId);
                message.setMsg("文件上传成功！");
                message.setState(true);
                message.add(scFile);

            } catch (Exception e) {
                e.printStackTrace();
                message.setState(false);
                message.setMsg("文件上传失败！");
            }
        }

        return message;
    }

    @Override
    public ResponseMessage removeDirectory(String path) {
        ResponseMessage message = new ResponseMessage();
        try {
            Path pathObj = Paths.get(path);
            int count = 0;
            if (fileMapper.getFileList(path) != null) {
                List<SCFile> unRemovedFiles = fileMapper.selectChildren(path);
                int children = fileMapper.deletePath(path);
                int parent = fileMapper.deleteOneFile(pathObj.getParent().toString(), pathObj.getFileName().toString());

                count = FDFSTool.deleteFiles(unRemovedFiles);

                System.out.println("最后删除:" + path);
                System.out.println("c = " + children + " p = " + parent);
                message.setMsg("删除成功。FDFS共删除了" + count + "条数据，共" + unRemovedFiles.size() + "条数据。");
                message.setState(true);
            } else {
                message.setState(false);
                message.setMsg("当前所选文件夹不存在。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message.setState(false);
            message.setMsg("删除失败！");
        }

        return message;
    }

    @Override
    public ResponseMessage moveFile(String requestBody) {
        ResponseMessage message = new ResponseMessage();

        System.out.println(requestBody);
        List<SCFile> unmovedFileList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileMoveWrapper wrapper = mapper.readValue(requestBody, FileMoveWrapper.class);

            List<SimpleFile> simpleFiles = wrapper.getFileNameList();
            String path = wrapper.getPath();
            String destPath = wrapper.getDestPath();

            for (SimpleFile sf : simpleFiles) {
                // 获取待移动的文件实例
                SCFile scFile = fileMapper.selectByFileName(path, sf.getFileName(), sf.isDir());
                System.out.println(scFile);
                if (sf.isDir()) {
                    // 获取子目录
                    String tempPath = path + "/" + sf.getFileName();
                    System.out.println("tempPath = " + tempPath);
                    List<SCFile> directoryList = fileMapper.selectChildren(tempPath);
                    for (SCFile cDirectory : directoryList) {
                        System.out.println();
                        System.out.print("old:" + cDirectory);
                        cDirectory.setPath(cDirectory.getPath().replace(path, destPath));
                        System.out.println(" new:" + cDirectory);
                    }
                    unmovedFileList.addAll(directoryList);
                }
                System.out.print("old:" + scFile);
                scFile.setPath(destPath);
                System.out.println(" new:" + scFile);
                unmovedFileList.add(scFile);
            }
            int duplicate = fileMapper.duplicateFile(unmovedFileList);
            System.out.println("重复文件：" + duplicate);
            if (duplicate != 0) {
                message.setMsg("有" + duplicate +"个重名文件，请先处理相关文件再进行移动！");
                message.setState(false);
                return message;
            } else {
                int updated = fileMapper.updateSCFiles(unmovedFileList);
                System.out.println("当前更新：" + updated + "个文件，总共:" + unmovedFileList.size() + "个");
                message.setState(true);
                message.setMsg("文件移动成功，共：" + unmovedFileList.size() + "个文件。");
                return message;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        message.setState(true);
        message.setMsg("文件移动成功。");
        return message;
    }


}

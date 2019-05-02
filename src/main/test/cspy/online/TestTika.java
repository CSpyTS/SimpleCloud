package cspy.online;

import cspy.online.bean.SCFile;
import cspy.online.dao.FileMapper;
import cspy.online.dao.StorageMapper;
import org.apache.tika.Tika;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CSpy
 * @date 2019/4/2 15:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class TestTika {

    public void getDirectory(Path root, List<Path> dirs) {
        File rootFile = root.toFile();
        if (rootFile.isDirectory()) {
            for (File childFileName : rootFile.listFiles()) {
                getDirectory(childFileName.toPath(), dirs);
            }
        }
        dirs.add(root);
    }

    @Autowired
    Tika tika;

    @Test
    public void testSaveDirectory() {

        Path rootPath = Paths.get("D:/SimpleCloud/users/CSpy");
        List<Path> dirs = new ArrayList<>();
        getDirectory(rootPath, dirs);


        for (Path path : dirs) {
            try {
                System.out.println("type: " + tika.detect(path) + " " + path);
                System.err.println("path: " + path.getParent());
                System.out.println("relative: " + rootPath.relativize(path).getParent());

            } catch (IOException e) {
                System.out.println("type: 文件夹 " + path);
                System.out.println("relative: " + rootPath.relativize(path));
            }
        }


        insertFileToDB(dirs);
    }

    public void insertFileToDB(List<Path> paths) {
        Path rootPath = Paths.get("D:/SimpleCloud/users/CSpy");

        List<SCFile> needSaveSCFile = new ArrayList<>();
        for (Path path : paths) {
            SCFile scFile = new SCFile();
            try {
                scFile.setType(tika.detect(path));
            } catch (IOException e) {
                scFile.setType("文件夹");
            }
            scFile.setGmtCreate(new Timestamp(System.currentTimeMillis()));
            scFile.setGmtModify(new Timestamp(System.currentTimeMillis()));
            scFile.setSize(path.toFile().length());
            scFile.setPath(path.getParent().toString());
            scFile.setFilename(path.getFileName().toString());
            needSaveSCFile.add(scFile);

        }
        saveToDB(needSaveSCFile);
    }


    @Autowired
    FileMapper mapper;

    public void saveToDB(List<SCFile> scFiles) {
        mapper.insertFiles(scFiles);
    }


    @Test
    public void testTika() {
        File file = new File("D:\\备份\\安装包\\android\\IDM+.apk");
        Tika tika = new Tika();
        try {
            System.out.println("fileType:" + tika.detect(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void insertFile() {
        SCFile scFile = new SCFile();
        scFile.setPath("D:\\SimpleCloud\\users\\CSpy");
        scFile.setSize(20);
        scFile.setFilename("testDate");
        scFile.setGmtCreate(new Timestamp(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1024));
        scFile.setGmtModify(new Timestamp(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1012));
        scFile.setType("文件夹");

        mapper.insertFile(scFile);
    }


    @Test
    public void testGetTotalSize() {
        long totalSize = mapper.getTotalSize("D:\\SimpleCloud\\users\\CSpy".replace("\\", "\\\\"));
        System.out.println(totalSize);

    }

    @Test
    public void testGetSize() {
        List<Long> sizes = mapper.selectSize("D:\\SimpleCloud\\users\\CSpy".replace("\\", "\\\\"));
        System.out.println(sizes.size());
    }

    @Autowired
    StorageMapper storageMapper;

    @Test
    public void getStorageSizeByUid() {
        Long storageSize = storageMapper.getStorageByUid(1);
        System.out.println("StorageSize = " + storageSize);
    }


}

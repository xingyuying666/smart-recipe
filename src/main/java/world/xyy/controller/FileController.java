package world.xyy.controller;

import cn.hutool.core.util.IdUtil;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import world.xyy.dto.RespResult;
import world.xyy.entity.User;
import world.xyy.utils.Assert;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;

/**
 * File controller
 *
 * @author xyy
 */
@RestController
@RequestMapping("/file")
public class FileController extends BaseController<User> {

    /**
     * Upload files
     */
    @PostMapping("/upload")
    public RespResult upload(@RequestParam("file") MultipartFile file) throws IOException {
//        String url = ossClient.upload(file, String.valueOf(loginUser.getId()));
        String fpath = "";

        String path = "D:/img";
        System.out.println("Folder name" + file.getName());
        if (StringUtils.isEmpty(file)) {
            throw new IOException("The uploaded file cannot be empty!");
        }
        if (StringUtils.isEmpty("images")) {
            throw new IOException("The folder cannot be empty!");
        }
        path = path + "/images/";
        File file1 = new File(path);
        //If the folder does not exist
        if (!file1.exists()) {
            //Create a folder
            file1.mkdir();
        }
        String fileName = null;
        //Get the file with the suffix
        String myFileName = file.getOriginalFilename();
        //Get the suffix
        String suffix = getExtensionName(myFileName);
        System.out.println("Get the file without a suffix" + myFileName + "-Get the suffix" + suffix);
        // If the name is not "", it indicates that the file exists; otherwise, it indicates that the file does not exist
        if (!myFileName.trim().equals("")) {
            // Rename the file name after uploading
            fileName = IdUtil.getSnowflake(0, 0).nextId() + "." + suffix;
            // Define the upload path
            fpath = path + fileName;
            File localFile = new File(fpath);
            file.transferTo(localFile);
            System.out.println("Uploaded successfully----Name" + fileName);
        }

        // Image recognition
        File imageFile = new File(fpath);
        Tesseract tesseract = new Tesseract();

        // Set the data path of Tesseract (including the language pack)
        tesseract.setDatapath("\"E:\\tessdata\\chi_sim.traineddata\"");

        try {
            String result = tesseract.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }

        if (Assert.isEmpty(fpath)) {
            return RespResult.fail("error", fpath);
        }
        return RespResult.success("success", fpath);
    }


    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

}

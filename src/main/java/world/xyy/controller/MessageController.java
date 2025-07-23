package world.xyy.controller;

import cn.hutool.core.util.IdUtil;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import world.xyy.dto.RespResult;
import world.xyy.entity.User;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Message controller
 *
 * @author xyy
 */
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController<User> {

    /**
     * Send a message
     */
    @PostMapping("/query")
    public RespResult query(@RequestParam("file") MultipartFile file, String content) throws IOException {

        String fpath = "";
        String count = "";
        if (!file.isEmpty()) {
            fpath = "";

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


            String imgPath = fpath;

            List<String> strings = extractKeywords(fpath);


            String result = apiService.query("帮我想个关于" + strings.get(strings.size() - 1) + "的食谱");
            return RespResult.success(result);

        } else {
            String result = apiService.query(content);
            return RespResult.success(result);
        }




    }

    public static List<String> extractKeywords(String imagePath) {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("chi_sim");

        try {
            String extractedText = tesseract.doOCR(new File(imagePath));
            // Extract key words. Here, simple Spaces are used for separation and filtering
            return Arrays.stream(extractedText.split("\\s+"))
                    .filter(word -> word.length() > 1) // Filter out words with a length less than 2
                    .distinct() // duplicate removal
                    .collect(Collectors.toList());
        } catch (TesseractException e) {
            System.err.println("OCR识别错误: " + e.getMessage());
            return null;
        }
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

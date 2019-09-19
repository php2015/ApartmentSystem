package com.aoke.apartmentsystem.system.controller;

import com.aoke.apartmentsystem.common.controller.BaseController;
import com.aoke.apartmentsystem.common.entity.FebsResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件图片上传
 * @author xiaoxinglin
 */

@Slf4j
@Validated
@RestController
@RequestMapping("file")
public class FileController extends BaseController {

    Logger log = LoggerFactory.getLogger(BuildingController.class);

    @GetMapping("/")
    public String uploladPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public FebsResponse fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest req, Model model) {
        try {
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            String destFileName = req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();//创建一下目录
            log.info("destFile:" + destFile);
            file.transferTo(destFile);
            model.addAttribute("fileName", fileName);
            model.addAttribute("path", destFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("Exception:" + e.getMessage());
            return new FebsResponse().message("上传失败"+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception:" + e.getMessage());
            return new FebsResponse().message("上传失败"+e.getMessage());
        }
        return new FebsResponse().success();
    }

}

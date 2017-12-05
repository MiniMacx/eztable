package org.tustcs.eztable.controller;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tustcs.eztable.config.Config;
import org.tustcs.eztable.dao.FileInfoMapper;
import org.tustcs.eztable.entity.FileInfo;
import org.tustcs.eztable.entity.FileKeyword;
import org.tustcs.eztable.enums.ResultEnums;
import org.tustcs.eztable.exception.FileException;
import org.tustcs.eztable.service.FileService;
import org.tustcs.eztable.utils.Res;
import org.tustcs.eztable.utils.ResUtil;
import org.tustcs.eztable.utils.XSSFUtils;
import sun.misc.Request;
import sun.nio.ch.FileKey;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/file",produces = {"application/json;charset=UTF-8"})
public class FileController {
    @Resource
    private FileService fileService;

    @Resource
    private FileInfoMapper fileInfoMapper;

    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    @ResponseBody
    public Res findAll(int pageNum){
        List<FileInfo> fileInfos=fileService.getFileList(pageNum);
        return new Res().setCode(0).setMsg(String.valueOf(fileInfoMapper.selectCount())).setData(fileInfos);
    }

    @RequestMapping(value = "/findByFileName",method = RequestMethod.POST)
    @ResponseBody
    public Res findByFileName(int pageNum,String fileName){
        List<FileInfo> fileInfoList=fileService.getByFileName(pageNum,fileName);
        if(fileInfoList.size()<1){
            throw new FileException(ResultEnums.FILE_NOTFOUND);
        }
        return ResUtil.success(fileInfoList);
    }

    @RequestMapping(value = "/addFile",method = RequestMethod.POST)
    @ResponseBody
    public Res addFile(String fileName,int userId,String fileDescription,int row) throws IOException {
        FileInfo fileInfo=new FileInfo();
        fileInfo.setCreateTime(String.valueOf(new Date().getTime()));
        fileInfo.setFileDescription(fileDescription);
        fileInfo.setFileName(fileName);
        fileInfo.setUserId(userId);
        fileInfo.setTypeId(1);

        if(fileService.addFileInfo(fileInfo)){
            if(fileService.addKeyword(row,fileName)){
                return ResUtil.success();
            }
        }
        throw new FileException(ResultEnums.ERROR);
    }

    @RequestMapping(value = "/getKeyword",method = RequestMethod.POST)
    @ResponseBody
    public Res getKeyword(String fileName){
        fileService.checkFile(fileName);

        List<FileKeyword> keywordList=fileService.getKeyByFileName(fileName);

        List<String> keyNameList=new ArrayList<String>();
        for (FileKeyword f:
             keywordList) {
            keyNameList.add(f.getKeywordName());
        }
        return ResUtil.success(keyNameList);
    }

    @RequestMapping(value = "/writeFile",method = RequestMethod.POST)
    @ResponseBody
    public Res writeFile(String infos,String fileName){
        JSONArray infoArray=new JSONArray(infos);

        List<String> infoList=new ArrayList<String>();

        List<FileKeyword> keywordList=fileService.getKeyByFileName(fileName);

        for(int i=0;i<infoArray.length();i++){
            JSONObject jsonObject=infoArray.optJSONObject(i);
            String keyword=jsonObject.optString("k");
            String value=jsonObject.optString("v");
            if(keyword.equals(keywordList.get(i).getKeywordName())){
                infoList.add(value);
            }
        }

        try {
            XSSFUtils.writeByKeyword(infoList,keywordList,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResUtil.success();
    }

    @RequestMapping(value = "/upload")
    public void upload(@RequestParam(value = "file",required = false)MultipartFile uploadFile,HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        String filename=uploadFile.getOriginalFilename();
        File file=new File(Config.FILE_PATH,filename);
        uploadFile.transferTo(file);
        response.sendRedirect("http://www.airmacx.com/upload.html");
    }

    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(String filename)throws Exception {
        //下载文件路径
        String path = Config.FILE_PATH+filename;
        System.out.println(path);

        File file = new File(path);
        if(file.exists()){
            HttpHeaders headers = new HttpHeaders();
            //下载显示的文件名，解决中文名称乱码问题
            String downloadFileName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
            //通知浏览器以attachment（下载方式）打开图片
            headers.setContentDispositionFormData("attachment", downloadFileName);
            //application/octet-stream ： 二进制流数据（最常见的文件下载）。
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            System.out.println("ok");
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                    headers, HttpStatus.CREATED);
        }else {
            throw new FileException(ResultEnums.FILE_NOTINLOC);
        }

    }
}

package com.nspaces.oss.busi.control;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.News;
import com.nspaces.oss.busi.dto.NewsDTO;
import com.nspaces.oss.busi.dto.NewsQueryDTO;
import com.nspaces.oss.busi.service.NewsService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/news")
public class NewsController {

	public static final Logger logger = LoggerFactory
			.getLogger(NewsController.class);

	@Autowired
	NewsService newsService;

	/**
	 * save新闻
	 * @param request
	 * @return
	 */
	@RequestMapping(value="saveNews",method=RequestMethod.POST)
	@ResponseBody
	public News saveNews(@RequestBody News news)
	{
		logger.info("saveNews");
		try {
			return newsService.insertAndUpdate(news);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error saveNews");
		}	
		return null;
	}
	
	/**
	 * delete新闻
	 * @param request
	 * @return
	 */
	@RequestMapping(value="deleteNews",method=RequestMethod.POST)
	@ResponseBody
	public String deleteNews(News news)
	{
		logger.info("deleteNews id:"+news.getId());
		try {
			 return newsService.deleteNews(news.getId())?"true":"false";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error deleteNews");
		}
		return "false";
	}
	
	
	/**
	 * 查询新闻信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryNews", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<NewsDTO> queryNews(NewsQueryDTO dataQueryDTO) {
		logger.info("queryNews by page");
		try {
			Pagination<NewsDTO> pagination = new Pagination<NewsDTO>();
			List<NewsDTO> list =  newsService.findNewsDTO(dataQueryDTO);
			pagination.setItems(list);
			return pagination;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error queryNews by page");
		}
		return null;
	}
	
	
	/**
	 * 新闻信息    发布状态
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findPublishStatus", method = RequestMethod.GET)
	@ResponseBody
	public List<NewsDTO> findBusiName() {
		logger.info("findPublishStatus");
		try {
			List<NewsDTO> list = newsService.findPublishStatus();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findPublishStatus");
		}

		return null;
	}


    /**
     *  图片上传
     */
    @RequestMapping(value = "upload")
    public String uploadImages(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request)
            throws Exception {

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
        /**构建图片保存的目录**/
        String logoPathDir = "/files/images"+ dateformat.format(new Date());
        /**得到图片保存目录的真实路径**/
        String realPath = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();

        String partRightType = fileName.substring(fileName.lastIndexOf("."));
        String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");

        System.out.println(realPath+fileName);

        File targetFile = new File(logoPathDir, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }



//        // 判断图片的格式
//        if (!ImageFile.checkImageType(partRightType)) {
//            String path = "";
//            String alt_msg = "Sorry! Image format selection is incorrect, please choose GIF, jpeg, PNG format JPG, picture!";
//            pringWriterToPage("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
//                    + CKEditorFuncNum
//                    + ", '"
//                    + path
//                    + "' , '"
//                    + alt_msg
//                    + "');</script>");
//        } else {
//            try {
//                uploadFileName = DateUtils.getDateNoStyle() + "-"
//                        + UUID.randomUUID() + partRightType;
//                String savePath = webRoot + Constants.UPLOAD_IMAGES_PATH;
//                File uploadFilePath = new File(savePath);
//                if (uploadFilePath.exists() == false) {
//                    uploadFilePath.mkdirs();
//                    System.out.println("路径不存在,但是已经成功创建了" + savePath);
//                } else {
//                    System.out.println("路径存在了" + savePath);
//                }
//                fos = new FileOutputStream(new File(savePath + uploadFileName));
//                FileInputStream fis = new FileInputStream(getUpload());
//                byte[] buffer = new byte[1024];
//                int len = 0;
//                while ((len = fis.read(buffer)) > 0) {
//                    fos.write(buffer, 0, len);
//                }
//                fos.close();
//                fis.close();
//            } catch (FileNotFoundException foe) {
//                System.out.println("上传文件为0字节");
//            }
//            // String path = "http://" + request.getServerName() + ":"
//            // + request.getServerPort() + request.getContextPath()
//            // + Constants.UPLOAD_IMAGES_PATH + uploadFileName;
//            String path = request.getContextPath()
//                    + Constants.UPLOAD_IMAGES_PATH + uploadFileName;
//            String alt_msg = "";
//            pringWriterToPage("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
//                    + CKEditorFuncNum
//                    + ", '"
//                    + path
//                    + "' , '"
//                    + alt_msg
//                    + "');</script>");
//        }
        return null;
    }
}

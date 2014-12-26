package com.nspaces.oss.busi.control;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.nspaces.oss.base.dto.Pagination;
import com.nspaces.oss.busi.domain.News;
import com.nspaces.oss.busi.dto.NewsDTO;
import com.nspaces.oss.busi.dto.NewsQueryDTO;
import com.nspaces.oss.busi.service.NewsService;

@Controller
@RequestMapping("/news")
public class NewsController {

	public static final Logger logger = LoggerFactory
			.getLogger(NewsController.class);

	@Autowired
	NewsService newsService;

	/**
	 * save新闻
	 * @param
	 * @return
	 */
	@RequestMapping(value="saveNews",method=RequestMethod.POST)
	@ResponseBody
	public News saveNews(@RequestBody News news)
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		news.setPublishDate(dateString);
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
	 * @param
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
	 * 新闻信息    新闻状态
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "findNewsType", method = RequestMethod.GET)
	@ResponseBody
	public List<NewsDTO> findNewsType() {
		logger.info("findNewsType");
		try {
			List<NewsDTO> list = newsService.findNewsType();
			if (list != null && list.size() > 0) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error findNewsType");
		}

		return null;
	}


    /**
     *  图片上传
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String uploadImages(HttpServletRequest request,HttpServletResponse response)
            throws Exception {

        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

        /** 设置输出流 **/
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        /** CKEditor提交的很重要的一个参数  **/
        String callback = request.getParameter("CKEditorFuncNum");

        /**页面控件的文件流**/
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

        /**得到图片保存目录的真实路径**/
        String realPath = request.getSession().getServletContext().getRealPath("/");
        /**构建图片保存的目录**/
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
        String logoPathDir = "upload/images/"+ dateformat.format(new Date())+"/";

        String newfileName = "";
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            String fileName = mf.getOriginalFilename();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            /** 检查文件类型 */
            String expandedName = "";
            if (fileExt.equals("jpg") || fileExt.equals("pjpeg") || fileExt.equals("jpeg")) {
                // IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
                expandedName = ".jpg";
            } else if (fileExt.equals("png") || fileExt.equals("x-png")) {
                // IE6上传的png图片的headimageContentType是"image/x-png"
                expandedName = ".png";
            } else if (fileExt.equals("gif")) {
                expandedName = ".gif";
            } else if (fileExt.equals("bmp")) {
                expandedName = ".bmp";
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
                        + ",'','文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");
                out.println("</script>");
                return null;
            }

            if (mf.getSize() > 600 * 1024) {
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
                        + ",''," + "'文件大小不得大于600k');");
                out.println("</script>");
                return null;
            }

            /** 生成文件 */
            newfileName = java.util.UUID.randomUUID().toString();
            newfileName += expandedName;
            File targetFile = new File(realPath+logoPathDir, newfileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            //保存
            try {
                mf.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /** 返回“图像”选项卡并显示图片 */
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
                + ",'" + basePath+logoPathDir+newfileName + "','')"); // 相对路径用于显示图片
        out.println("</script>");

        return null;
    }
    
    
    /**
	 * 跨域请求 新闻列表
	 * 
	 * @param
	 * @return
     * @throws Exception 
	 */
	@RequestMapping(value = "findNewsListByJsonp", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String findNewsListByJsonp(HttpServletRequest request,HttpServletResponse response) throws Exception {
	  String newsType = request.getParameter("newsType");
      String callback = request.getParameter("jsoncallback"); 
      List<NewsDTO> list = newsService.findNewsListByJsonp(newsType);
      String result = callback+"("+JSONArray.fromObject(list).toString()+")"; 
      return result; 
	} 
	
}

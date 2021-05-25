package kr.co.esboard.board;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    private static String ARTICLE_IMAGE_REPO = "C:\\board\\article_image";
    BoardService boardService;
    ArticleVO articleVO;

    public void init(ServletConfig config) throws ServletException {
        boardService = new BoardService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        doHandle(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request, response);
    }
    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = "";
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String action = request.getPathInfo();
        System.out.println("action:" + action);
        System.out.println("�̱��� �����");
        try {
            List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
            if (action == null) {
                articlesList = boardService.listArticles();
                request.setAttribute("articlesList", articlesList);
                nextPage = "/article/listArticles.jsp";
                
            } else if (action.equals("/listArticles.do")) {
                articlesList = boardService.listArticles();
                request.setAttribute("articlesList", articlesList);
                nextPage = "/article/listArticles.jsp";
                
            } else if (action.equals("/articleFrom.do")){
            	nextPage = "/article/articleForm.jsp";
            	
            } else if (action.equals("/addArticle.do")){
            	
            	Map<String, String> articleMap = upload(request, response);
            	String title = articleMap.get("title");
            	String content = articleMap.get("content");
            	String imageFileName = articleMap.get("imageFileName");
            	
            	articleVO.setParentNO(0);
            	articleVO.setId("tempID");
            	articleVO.setTitle(title);
            	articleVO.setContent(content);
            	articleVO.setImageFileName(imageFileName);
            	System.out.println(title);
            	nextPage = "/article/listArticles.jsp";
            } else if(action.equals("/viewArticle.do")) {
            	String articleNO = request.getParameter("articleNO");
            	
            } else if(action.equals("/viewArticle.do")) {
            	String articleNO = request.getParameter("articleNO");
            	articleVO=boardService.viewArticle(Integer.parseInt(articleNO));
            	request.setAttribute("article", articleVO);
            	nextPage = "/board03/viewArticle.jsp";
            } else if(action.equals("/modArticle.do")) {
            	Map<String, String> articleMap = upload(request, response);
            	
    
            }else {
                nextPage = "/article/listArticles.jsp";
            }
            
            RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
            dispatch.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List items = upload.parseRequest(request);
			for (int i=0; i<items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					System.out.println("�Ķ���� �̸� : " + fileItem.getFieldName());
					System.out.println("���� �̸� : " + fileItem.getName());
					System.out.println("���� ũ�� : " + fileItem.getSize()+"bytes");
					
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						
						String fileName = fileItem.getName().substring(idx+1);
						articleMap.put(fileItem.getFieldName(), fileName);
						File uploadFile = new File(currentDirPath+ "\\" + fileName);
						fileItem.write(uploadFile);
						
					}
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return articleMap;
	}
}
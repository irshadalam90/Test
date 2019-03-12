/*



package controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import been.EmailPannel;
import email.Email;
import pojos.EmailPojo;
import pojos.LoginPojo;
import service.Service;


@Controller
public class HomeController {

	private Service ser;
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	  @Autowired
	public void setSer(Service ser) {
		this.ser = ser;
	}
	
	private static final String EXCELDIRECTRY ="excelFile";
	private static final String EXCEL_FILE_DIRECTORY ="uploadFile";
	
		
	@RequestMapping(value="/userSendMail", method=RequestMethod.POST)
	public ModelAndView userSendMail(@RequestParam CommonsMultipartFile[] files, @RequestParam CommonsMultipartFile file, HttpServletRequest request, HttpSession session, ModelMap map , EmailPojo addemail) throws IOException {
		ModelAndView view = new ModelAndView("simpleEmail");
		String username1 = request.getParameter("userName");
		session.setAttribute("username", username1);
		String UserMail = request.getParameter("UserMail");
		String userPassWord = request.getParameter("userPassWord");
		String username2 = (String)session.getAttribute("usern");
		Integer userId= ser.getUserId(username2);
		
		String savePath = request.getServletContext().getRealPath("/")+"uploadedFile";
		String appPath = session.getServletContext().getRealPath("");
		String fileAttachment = null;
		String fPath=null;
        String message;
        File f= null;
        File[] fileAttached=null;
        
        if(files != null && files.length > 0){
        	try{
        		for(int i=0; i<files.length; i++){
        			fileAttachment = files[i].getOriginalFilename();
        		    fPath = appPath+File.separator+EXCEL_FILE_DIRECTORY;
        			byte[] bytess = files[i].getBytes();
        			f = new File(fPath);
        			if (!f.exists())
                    System.out.println(f.mkdirs());
                    File sfile = new File(f.getAbsolutePath()
                            + File.separator +fileAttachment );
                    
        			 BufferedOutputStream stream = new BufferedOutputStream(
        	                    new FileOutputStream(sfile));
        	            stream.write(bytess);
        	            stream.close();
        	            fileAttached = f.listFiles();
            			System.out.println("attachments "+fileAttached);
            			        		}
        	}
        	catch(Exception e){
        		
        	}
        }	
		
		String fileName=file.getOriginalFilename();
		
        
        String realPath=appPath+File.separator+EXCELDIRECTRY;
        
        System.out.println("realPath"+ realPath);
		
			byte[] bytes = file.getBytes();
			 
            File dir = new File(realPath);
            if (!dir.exists())
                System.out.println(dir.mkdirs());
            File serverFile = new File(dir.getAbsolutePath()
                    + File.separator +fileName );
          
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            		
        ArrayList  col = new ArrayList();
		FileInputStream inputStream = new FileInputStream(serverFile);
         
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
         
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
             
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(cell.getColumnIndex()==1 && nextRow.getRowNum()>0) {
                              
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) 
                {
                 col.add(cell.getStringCellValue());
                } 
                           }
            }
            System.out.println();
        }
         
        workbook.close();
        inputStream.close();
           
        int count = 0;
        String emailSubject = request.getParameter("subject");
		String emailContent = request.getParameter("content");
		
		ArrayList<String> emailList = new ArrayList<String>();
		 for(Object c : col){
        	String email = (String)c;
        	Calendar cal = Calendar.getInstance();
   	     	String dateTime = sdf.format(cal.getTime());
    		System.out.println(dateTime);
    		 String username = (String)session.getAttribute("usern");
    		System.out.println(username);
        	try {
    			if(Email.sendEmail(email, emailSubject, emailContent,fileAttached, request, session)){
    				
    				emailList.add(email);
    				 ser.addEmail(email,dateTime,username,userId);
    			       
    				count = count + 1;
    				
    			}
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
      
        	map.addAttribute("emailSend", emailList);
        	map.addAttribute("emailCount", count);
        
		return view;
	}
			
}
*/
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
public class EmailPannelController {

	
	private Service ser;
	
	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

   @Autowired
	public void setSer(Service ser) {
		this.ser = ser;
	}
	
	private static final String UPLOAD_DIRECTORY ="excelFile";
	//private static final String FILE_DIRECTORY ="uploadFile";
	
	@RequestMapping("welcome")
	public ModelAndView homePage(){
		ModelAndView view = new ModelAndView("login");
		return view;
	}
	@RequestMapping(value="/secureLogin", method=RequestMethod.POST)
	public ModelAndView loginPage(HttpServletRequest req ,ModelMap map, HttpSession session){
	ModelAndView mav = new ModelAndView();
	String username = req.getParameter("userName");
	String password = req.getParameter("password");

	ArrayList<LoginPojo> loginList = ser.userList();
	System.out.println("loginList");
	for(LoginPojo logindata :  loginList){
		
		   if(!logindata.getUserName().equals(username) ){
			   mav = new ModelAndView("login","msg", "Email Id wrong. Please enter currect Email Id");
		   }
		   
		   else if(!logindata.getPassword().equals(password)){
			   mav = new ModelAndView("login","msg", "password wrong. Please enter currect Password ");
		   }
		   else{
			   session = req.getSession();
				session.setAttribute("usern", username);
				session.setAttribute("upass", password);
				String username2 = (String)session.getAttribute("usern");
				Integer userId= ser.getUserId(username2);
				String  idList = ser.getTotalId(userId);
				map.addAttribute("totalRow", idList);
				System.out.println("mav"+idList);
				mav = new ModelAndView("NewEmailPannel");
				break;
		   }
		   
			}
		

	return mav;	
    }
	
	@RequestMapping(value="logOut")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		session.removeAttribute("userName");
    	 session.invalidate();
		return new ModelAndView("login","msg","Successfully Logout");
	}
		
  @RequestMapping("emailpannel")
	public ModelAndView emailPannelPage(HttpServletRequest req ,ModelMap map, HttpSession session){
		ModelAndView view = new ModelAndView("NewEmailPannel");
		String username2 = (String)session.getAttribute("usern");
		Integer userId= ser.getUserId(username2);
		String  idList = ser.getTotalId(userId);
		map.addAttribute("totalRow", idList);
		return view;
	}
	
	@RequestMapping(value="/sendemail", method=RequestMethod.POST)
	public ModelAndView sendEmailtoAll(@RequestParam CommonsMultipartFile[] files, @RequestParam CommonsMultipartFile file, HttpServletRequest request, HttpSession session, ModelMap map , EmailPojo addemail) throws IOException {
		
		ModelAndView view = new ModelAndView("NewEmailPannel");
		/*String username2 = request.getParameter("userName");
		session.setAttribute("username", username2);*/
		//session.getAttribute("username");
		
		String username1 = request.getParameter("userId");
		session.setAttribute("username", username1);
		String FILE_DIRECTORY = request.getParameter("files");
		String username2 = (String)session.getAttribute("usern");
		Integer userId= ser.getUserId(username2);
		String  idList = ser.getTotalId(userId);
		map.addAttribute("idList", idList);
          // System.out.println(userId+"ffffffffff");
		Integer tot = Integer.parseInt(idList);
	
		//String savePath = request.getServletContext().getRealPath("/")+"uploadedFile";
		String appPath = session.getServletContext().getRealPath("");
		String appPatha = session.getServletContext().getRealPath("");
		String fileAttachment = null;
		String fPath=null;
        String message;
        File f= null;
        File[] fileAttached=null;
        
        
        
        String fileNamea=file.getOriginalFilename();
		String realPatha=appPatha+File.separator+UPLOAD_DIRECTORY;
        System.out.println("realPath"+ realPatha);
			byte[] bytesa = file.getBytes();
		    File dira = new File(realPatha);
            if (!dira.exists())
                System.out.println(dira.mkdirs());
            File serverFilea = new File(dira.getAbsolutePath()
                    + File.separator +fileNamea );
          
            BufferedOutputStream streama = new BufferedOutputStream(
                    new FileOutputStream(serverFilea));
            streama.write(bytesa);
            streama.close();
        
        ArrayList  cola = new ArrayList();
       
		FileInputStream inputStreama = new FileInputStream(serverFilea);
        Workbook workbooka = new XSSFWorkbook(inputStreama);
        Sheet firstSheeta = workbooka.getSheetAt(0);
        Integer totalRow=0;
       
        totalRow= tot+firstSheeta.getLastRowNum();
        System.out.println("Latest no.  : "+totalRow);
        map.addAttribute("totalRow", totalRow);
        
        
        if(files != null && files.length > 0){
        	
        	try{
        		for(int i=0; i<files.length; i++){
        			fileAttachment = files[i].getOriginalFilename();
        		    fPath = appPath+File.separator+FILE_DIRECTORY;
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
            			System.out.println("attachments "+fileAttached[0]);
           		}
        	}
        	catch(Exception e){
        		
        	}
        }	
		
	String fileName=file.getOriginalFilename();
		String realPath=appPath+File.separator+UPLOAD_DIRECTORY;
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
        ArrayList  col1 = new ArrayList();
		FileInputStream inputStream = new FileInputStream(serverFile);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        int count = 0;
    	ArrayList<String> emailList = new ArrayList<String>();
        if(totalRow<31){
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            
           String cell = nextRow.getCell(0).getStringCellValue();
           String email = nextRow.getCell(1).getStringCellValue();
           
           String emailSubject = request.getParameter("subject");
   		String emailContent = request.getParameter("content");
   		
   	
   		
            Calendar cal = Calendar.getInstance();
      	    String dateTime = sdf.format(cal.getTime());
       		System.out.println(dateTime);
       		 String username = request.getParameter("userId");
       		System.out.println(username);
           	try {
       			if(Email.sendEmail(email, emailSubject, emailContent,fileAttached[0], request, session,cell)){
       				
       				emailList.add(email);
       				 ser.addEmail(email,dateTime,username,userId);
       				 System.out.println(userId+"insert data in dava");
       			       
       				count = count + 1;
       				
       			}
       			
       		} catch (Exception e) {
       			e.printStackTrace();
       		}
           
           
          
           	System.out.println(emailList);
           	map.addAttribute("emailCount", count);
           
             
          /*  while (cellIterator.hasNext()) {
               // String name =  
            	Cell cell = cellIterator.next();
            	
                if(cell.getColumnIndex()==1 && nextRow.getRowNum()>0) {
                	
                if (cell.getCellType() == Cell.CELL_TYPE_STRING) 
                {
                col.add(cell.getStringCellValue());
              
                } 
            }
            }*/

           System.out.println();
        }
     	map.addAttribute("emailSend", emailList);
        workbook.close();
        inputStream.close();
   
    }
		return view;
	}
	
	@RequestMapping("viewEmail")
	public ModelAndView viewEmail(){
		ModelAndView view = new ModelAndView("viewEmail");
		ArrayList<EmailPojo> emailList= ser.showEmail();
		view.addObject("viewEmail", emailList);
		view.addObject("totalMail", emailList.size());
		return view;
	}
	
}

package gatherfile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class gatherfile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 File dir = new File("D://downloads//data//DATA//trans//*");                        
	        File out=new File("D://downloads//gathereddata"); 
	        try { 
	            SaveFile(dir,out); 
	        } catch (IOException e) { 
	            // TODO Auto-generated catch block 
	            e.printStackTrace(); 
	        }   
	    } 
	        
	        static void SaveFile(File dir, File dst) throws IOException { 
	       
	        String[] children = dir.list(); 
	        FilenameFilter filter = new FilenameFilter(){ 
	            
	            public boolean accept(File dir, String name) { 
	                return true;    
	                
	            } 
	        }; 

	        
	        File filePath = new File("D://downloads//gathereddata"); 
	        String fileIndex="0"; 
	        File FileBig = new File(filePath + "/" + "Files"+"_"+fileIndex); 
	        BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(FileBig)); 
	    
	        children = dir.list(filter); 
	        
	        for (int i = 0; i < children.length; i++) { 

	        	String inhalt = children[i]; 
	        	FileInputStream in = new FileInputStream(dir + "/" + inhalt); 

	        	byte[] buffer = new byte[1024]; 
	        	int len=0; 
	        	if (FileBig.length() / 1024 / 1024 < 10) { 
	        	while ((len = in.read(buffer)) > 0) { 
	        	out.write(buffer, 0, len); 

	        	} 
	        	out.flush();// out.close(); 
	        	} else { 
	        	int fileIndexInt = Integer.parseInt(fileIndex) + 1; 
	        	FileBig = new File(filePath + "/" + "Files" + "_" 
	        	+ fileIndexInt); 
	        	out = new BufferedOutputStream( 
	        	new FileOutputStream(FileBig)); 

	        	while ((len = in.read(buffer)) > 0) { 
	        	out.write(buffer, 0, len);	
	        	} 
	        	out.flush(); 
	        	//out.close(); 

	        	fileIndex = String.valueOf(fileIndexInt); 
	        	} 
		

	}
	        }
}
	        



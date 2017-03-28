package traverse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;  
  
public class FileOperation {
    private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
    // 通过 sPath.matches(matches) 方法的返回值判断是否正确
    // sPath 为路径字符串
    boolean flag = false;
    File file;
 /** 
  * 创建文件 
  * @param fileName
  * @return
  */  
 public static boolean createFile(String fileName)throws Exception{

        boolean flag=false;
     File f  = new File(fileName);
  try{
   if(!f.exists()){
       f.createNewFile();
    flag=true;
   }
  }catch(Exception e){  
   e.printStackTrace();  
  }  
  return true;  
 }

    // 创建目录
public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {// 判断目录是否存在
            System.out.println("创建目录失败，目标目录已存在！");
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
            destDirName = destDirName + File.separator;
        }
        if (dir.mkdirs()) {// 创建目标目录
            System.out.println("创建目录成功！" + destDirName);
            return true;
        } else {
            System.out.println("创建目录失败！");
            return false;
        }
}

 public boolean DeleteFolder(String deletePath) {// 根据路径删除指定的目录或文件，无论存在与否
      flag = false;
        if (deletePath.matches(matches)) {
            file = new File(deletePath);
            if (!file.exists()) {// 判断目录或文件是否存在
                return flag; // 不存在返回 false
            } else {

                if (file.isFile()) {// 判断是否为文件
                    return deleteFile(deletePath);// 为文件时调用删除文件方法
                } else {
                    return deleteDirectory(deletePath);// 为目录时调用删除目录方法
                }
            }
        } else {
            System.out.println("要传入正确路径！");
            return false;
        }
    }



    public boolean deleteDirectory(String dirPath) {// 删除目录（文件夹）以及目录下的文件
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!dirPath.endsWith(File.separator)) {
            dirPath = dirPath + File.separator;
        }
        File dirFile = new File(dirPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        File[] files = dirFile.listFiles();// 获得传入路径下的所有文件
        for (int i = 0; i < files.length; i++) {// 循环遍历删除文件夹下的所有文件(包括子目录)
            if (files[i].isFile()) {// 删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                System.out.println(files[i].getAbsolutePath() + " 删除成功");
                if (!flag)
                    break;// 如果删除失败，则跳出
            } else {// 运用递归，删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;// 如果删除失败，则跳出
            }
        }
        if (!flag)
            return false;
        if (dirFile.delete()) {// 删除当前目录
            return true;
        } else {
            return false;
        }
    }


 /** 
  * 读TXT文件内容 
  * @param fileName 
  * @return 
  */  
 public static String readTxtFile(File fileName)throws Exception{  
  String result=null;  
  FileReader fileReader=null;  
  BufferedReader bufferedReader=null;  
  try{  
   fileReader=new FileReader(fileName);  
   bufferedReader=new BufferedReader(fileReader);  
   try{  
    String read=null;  
    while((read=bufferedReader.readLine())!=null){  
     result=result+read+"\r\n";  
    }  
   }catch(Exception e){  
    e.printStackTrace();  
   }  
  }catch(Exception e){  
   e.printStackTrace();  
  }finally{  
   if(bufferedReader!=null){  
    bufferedReader.close();  
   }  
   if(fileReader!=null){  
    fileReader.close();  
   }  
  }  
  System.out.println("读取出来的文件内容是："+"\r\n"+result);  
  return result;  
 }  
   
   
 public static boolean writeTxtFile(String content,File  fileName)throws Exception{  
  RandomAccessFile mm=null;  
  boolean flag=false;  
  FileOutputStream o=null;  
  try {  
   o = new FileOutputStream(fileName);  
      o.write(content.getBytes("GBK"));  
      o.close();  
//   mm=new RandomAccessFile(fileName,"rw");  
//   mm.writeBytes(content);  
   flag=true;  
  } catch (Exception e) {  
   // TODO: handle exception  
   e.printStackTrace();  
  }finally{  
   if(mm!=null){  
    mm.close();  
   }  
  }  
  return flag;  
 }  
  
  
  //写入文本内容且显示出来
public static void contentToTxt(String filePath, String content) {  
	  
		System.out.println(content);
        contentToTxt(filePath,content,1);
    }  
//写入文本内容且但不显示出来 看日志
public static void contentToTxt(String filePath, String content,int i) {  
    String str = new String(); //原有txt内容  
    String s1 = new String();//内容更新  
    try {  
        File f = new File(filePath);  
        if (f.exists()) {  
            
        } else {  
            f.createNewFile();// 不存在则创建  
        }  
        BufferedReader input = new BufferedReader(new FileReader(f));  

        while ((str = input.readLine()) != null) {  
            s1 += str + "\n";  
        }  
        input.close();  
        s1 += content;  

        BufferedWriter output = new BufferedWriter(new FileWriter(f));  
        output.write(s1);  
        output.close();  
    } catch (Exception e) {  
        e.printStackTrace();  

    }  
} 


/** 
 * 删除单个文件 
 * @param   sPath    被删除文件的文件名 
 * @return 单个文件删除成功返回true，否则返回false 
 */  
public static boolean deleteFile(String sPath) {  
    boolean flag = false;  
    File file = new File(sPath);  
    // 路径为文件且不为空则进行删除  
    if (file.isFile() && file.exists()) {  
        file.delete();  
        flag = true;  
    }  
    return flag;  
}  

  
}  
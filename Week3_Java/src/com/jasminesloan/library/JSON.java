package com.jasminesloan.library;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;

public class JSON {

		@SuppressWarnings("resource")
		public static Boolean storeStringFile(Context context, String fileName, String content, Boolean external){
			try {
					File file;
					FileOutputStream fos;
					if(external){
						file = new File(context.getExternalFilesDir(null), fileName);
						fos = new FileOutputStream(file);
					}else{
						fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
					}
					fos.write(content.getBytes());
			}catch (IOException e) {
				Log.e("Write Error", e.toString());
			}
			return true;
		}
		
		@SuppressWarnings("resource")
		public static Boolean storeObjectFile(Context context, String fileName, Object content, Boolean external){
			try{
			File file;
			FileOutputStream fos;
			ObjectOutputStream oos;
			if(external){
				file = new File(context.getExternalFilesDir(null),fileName);
				fos = new FileOutputStream(file);
			}else{
				fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			}
			oos = new ObjectOutputStream(fos);
			oos.writeObject(content);
			Log.i("Content written", content.toString());
			oos.close();
			fos.close();
		}catch(IOException e){
			Log.e("Write Error", fileName);
		}
		return true;
		}
		
		@SuppressWarnings("resource")
		public static String readStringFile(Context context, String fileName, Boolean external){
			String content = "";
			try {
				File file;
				FileInputStream fis;
				if(external){
					file = new File(context.getExternalFilesDir(null), fileName);
					fis = new FileInputStream(file);
				} else {
					file = new File(fileName);
					fis = context.openFileInput(fileName);
				}
				
				BufferedInputStream bin = new BufferedInputStream(fis);
				byte[] contentByte = new byte[1024];
				int bytesRead = 0;
				StringBuffer contentBuffer = new StringBuffer();
				
				while ((bytesRead = bin.read(contentByte)) != -1){
					content = new String(contentByte,0,bytesRead);
					contentBuffer.append(content);
				}
				content = contentBuffer.toString();
				fis.close();
			}catch (FileNotFoundException e){
				Log.e("Read Error", "File Not Found");
			}catch(IOException e){
				Log.e("Read Error", "I/O Error");
			}
			return content;
		} 
		
		@SuppressWarnings("resource")
		public static Object readStringObject(Context context, String fileName, Boolean external){
			Object content = new Object();
			try {
				File file;
				FileInputStream fis;
				if(external){
					file = new File(context.getExternalFilesDir(null), fileName);
					fis = new FileInputStream(file);
				}else {
					file = new File(fileName);
					fis = context.openFileInput(fileName);
				}
				ObjectInputStream ois = new ObjectInputStream(fis);
				try {
					content = (Object) ois.readObject();
				}catch (ClassNotFoundException e){
					Log.e("Read Error", "Invalid Object");
				}
				
				fis.close();
				ois.close();
			}catch (FileNotFoundException e){
				Log.e("Read Error", "File Not Found");
				return null;
			}catch (IOException e){
				Log.e("Read Error", "I/O Error");
			}
			return content;
		}
}

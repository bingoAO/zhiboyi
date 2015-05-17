package com.bao.wec.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;

import com.bao.wec.app.Constant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileUtils {

	public FileUtils() {

	}

	/**
	 * 在SD卡内创建文件
	 * 
	 * @param dir
	 *            不包含SDCardRoot和后面的分隔符\的路径
	 * @throws java.io.IOException
	 */
	public static File createFileInSDCard(String fileName, String dir)
			throws IOException {
		File file = new File(Constant.Path.SDCardRoot + dir + File.separator
				+ fileName);
		//System.out.println("创建以下文件---->" + AppConstant.PATH.SDCardRoot + dir
		//		+ File.separator + fileName);
		if (file.createNewFile()) {
			//System.out.println("创建成功---->" + AppConstant.PATH.SDCardRoot + dir
			//		+ File.separator + fileName);
		} else {
			//System.out.println("创建失败---->" + AppConstant.PATH.SDCardRoot + dir
			//		+ File.separator + fileName);
		}

		return file;
	}

	/**
	 * 创建路径
	 * 
	 * @param dir
	 *            不带SDCardRoot和后面的分隔符\的路径
	 */
	public static File creatSDDir(String dir) {
		File dirFile = new File(Constant.Path.SDCardRoot + dir
				+ File.separator);
		LogUtils.i("创建路径结果---->" + dirFile.mkdirs() + "---->"
				+ dirFile.getPath());
		return dirFile;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param path
	 *            不带SDCardRoot和后面的分隔符\的路径
	 */
	public static boolean isFileExist(String fileName, String path) {
		File file = new File(Constant.Path.SDCardRoot + path
				+ File.separator + fileName);
		//if (file.exists())
			//System.out.println("文件存在--->" + path + File.separator + fileName);
		return file.exists();
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param path
	 *            全路径包括文件名
	 */
	public static boolean isFileExist(String path) {
		File file = new File(path);
		// if(file.exists()) //System.out.println("文件存在--->"+path);
		// else //System.out.println("文件不存在--->"+path);
		return file.exists();
	}

    /**
     * 删除文件
     * @param fileName
     * @param path
     * @return
     */
    public static boolean deleteFile(String fileName, String path) {
        File file = new File(Constant.Path.SDCardRoot + path
                + File.separator + fileName);
        if(file.exists()){
            return file.delete();
        }
        return false;
    }

	/**
	 * 将InputStream写入SD卡
	 * 
	 * path不带SDCARDROOT以及最后分隔符
	 */
	public static File write2SDFromInput(String path, String fileName,
			InputStream input) {

		File file = null;
		OutputStream output = null;
		try {
			creatSDDir(path);
			file = createFileInSDCard(fileName, path);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			int temp;
			while ((temp = input.read(buffer)) != -1) {
				output.write(buffer, 0, temp);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 从文件获得字符串
	 * 
	 * @param filePath
	 *            文件的全路径信息
	 * @return
	 */

	@SuppressWarnings("resource")
	public static String getStringFromFile(String filePath) {
		try {
			StringBuffer sBuffer = new StringBuffer();
			FileInputStream fInputStream = new FileInputStream(filePath);
			InputStreamReader inputStreamReader = new InputStreamReader(
					fInputStream, "UTF-8");
			BufferedReader in = new BufferedReader(inputStreamReader);
			if (!new File(filePath).exists()) {
				//System.out.println("文件不存在 ！无法读取--->" + filePath);
				return null;
			}
			while (in.ready()) {
				sBuffer.append(in.readLine() + "\n");
			}
			in.close();
			//System.out.println("文件读取成功--->" + filePath);
			return sBuffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the corresponding path to a file from the given content:// URI
	 * 
	 * @param selectedVideoUri
	 *            The content:// URI to find the file path from
	 * @param contentResolver
	 *            The content resolver to use to perform the query.
	 * @return the file path as a string
	 */
	public static String getFilePathFromContentUri(Uri selectedVideoUri,
			ContentResolver contentResolver) {
		String filePath;
		String[] filePathColumn = { MediaColumns.DATA };

		Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn,
				null, null, null);
		// 也可用下面的方法拿到cursor
		// Cursor cursor = this.context.managedQuery(selectedVideoUri,
		// filePathColumn, null, null, null);

		cursor.moveToFirst();

		int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
		filePath = cursor.getString(columnIndex);
		cursor.close();
		return filePath;
	}

	/**
	 * Gets the content:// URI from the given corresponding path to a file
	 * 
	 * @param context
	 * @param imageFile
	 * @return content Uri
	 */
	public static Uri getImageContentUri(Context context, File imageFile) {
		String filePath = imageFile.getAbsolutePath();
		Cursor cursor = context.getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				new String[] { MediaStore.Images.Media._ID },
				MediaStore.Images.Media.DATA + "=? ",
				new String[] { filePath }, null);
		if (cursor != null && cursor.moveToFirst()) {
			int id = cursor.getInt(cursor
					.getColumnIndex(MediaColumns._ID));
			Uri baseUri = Uri.parse("content://media/external/images/media");
			return Uri.withAppendedPath(baseUri, "" + id);
		} else {
			if (imageFile.exists()) {
				ContentValues values = new ContentValues();
				values.put(MediaStore.Images.Media.DATA, filePath);
				return context.getContentResolver().insert(
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
			} else {
				return null;
			}
		}
	}
	

	

}
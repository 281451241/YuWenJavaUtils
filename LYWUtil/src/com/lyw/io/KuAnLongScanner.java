package com.lyw.io;

import java.io.File;
import java.util.LinkedList;

public class KuAnLongScanner
{
	public void listAllFiles()
	{
		File tempFile = null;
		File mDirectoryFile = new File("D:/");
		LinkedList<File> listDirectory = new LinkedList<File>();
		listDirectory.add(mDirectoryFile);
		File[] listFile;
		while (!listDirectory.isEmpty())
		{
			tempFile = listDirectory.removeFirst();
			listFile = tempFile.listFiles();
			if (listFile == null)
			{
				continue;
			}
			for (File files : listFile)
			{
				if (files.isDirectory())
				{
//					if (files.getPath().indexOf("/.") != -1)
//					{
//						continue;
//					}
					listDirectory.add(files);
				}
				else
				{
					String filePath = files.getAbsolutePath();
				}
			}
		}
	}
}

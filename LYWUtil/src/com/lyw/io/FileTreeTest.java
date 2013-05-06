package com.lyw.io;

import java.io.File;
import java.util.Stack;

public class FileTreeTest
{

//	public static void main(String[] args)
//	{
//		FileTree tree = new FileTree(
//				"D:/");
//		tree.tree();
//	}
	
	public void scan() {
		FileTree tree = new FileTree(
				"D:/");
		tree.tree();
	}
}

class FileTree
{

	private File root;

	public FileTree(File root)
	{
		this.root = root;
	}

	public FileTree(String rootName)
	{
		this(new File(rootName));
	}

	public void tree()
	{
		Stack<Boolean> lastStack = new Stack<Boolean>();
		tree(root, 0, lastStack);
	}

	private void tree(File dir, int level, Stack<Boolean> lastStack)
	{
		if (dir == null || !dir.exists())
		{
			return;
		}
//		printFile(dir, level, lastStack);

		if (dir.isFile())
		{
			return;
		}
		File[] children = dir.listFiles();
		for (int i = 0; i < children.length; i++)
		{
			lastStack.push(i == children.length - 1);
			tree(children[i], level + 1, lastStack);
			lastStack.pop();
		}
	}

	private void printFile(File file, int level, Stack<Boolean> lastStack)
	{
		for (int i = 0, k = lastStack.size() - 1; i < k; i++)
		{
			System.out.print(lastStack.get(i) ? "　 " : "│ ");
		}
		if (level > 0)
		{
			System.out.print(lastStack.get(lastStack.size() - 1) ? "└ " : "├ ");
		}
		System.out.println(level == 0 ? file.getPath() : file.getName());
	}
}

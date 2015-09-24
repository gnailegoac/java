package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test {

	public static void main(String[] args) throws Exception {
		long timeStart = System.currentTimeMillis();

		String word = args[1];
		BufferedReader reader = new BufferedReader(new FileReader(args[0]));
		String line = "";
		StringBuffer strBuffer = new StringBuffer();
		
		int countSum = 0;
		while((line = reader.readLine()) != null) {
			strBuffer.append(line);
			if (strBuffer.capacity()/1024/1024 > 50) {
				String str = strBuffer.toString();
				int origLen = str.length();
				int nowlen = str.replace(word, "").length();
				int count = (origLen - nowlen)/word.length();
				countSum += count;
				strBuffer.setLength(0);
			}
		}
		reader.close();
		String str = strBuffer.toString();	
		int origLen = str.length();
		int nowlen = str.replace(word, "").length();
		int count = (origLen - nowlen)/word.length();
		countSum += count;
		
//		byte word[] = args[1].getBytes();
//		int wordLen = args[1].length();
//		int countSum = 0;
//
//		FileInputStream fileInputStream = new FileInputStream(args[0]);
//		FileChannel fileChannel = fileInputStream.getChannel();
//		ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024*10);
//		fileChannel.read(byteBuffer);
//		byteBuffer.flip();
//		fileInputStream.close();
//
//		byte b;
//		int index = 0;
//		while(byteBuffer.hasRemaining()) {
//			index = 0;
//			while((b = byteBuffer.get()) == word[index])
//			{
//				index++;
//				if (index == wordLen) {
//					countSum++;
//					index = 0;
//					break;
//				}
//			}
//			byteBuffer.position(byteBuffer.position()-index);
//		}

		System.out.println("count: " + countSum);
		System.out.println("time: " + (System.currentTimeMillis() - timeStart));
	}
}

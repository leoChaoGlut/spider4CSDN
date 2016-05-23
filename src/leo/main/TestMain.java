package leo.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月21日 下午3:48:46
 * @Usage:
 */
public class TestMain {
	public static void main(String[] args) throws Exception {

		String filePath = "/home/leo/test.txt";
		FileWriter fw = new FileWriter(filePath);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		bw.flush();
		bw.close();

	}
}

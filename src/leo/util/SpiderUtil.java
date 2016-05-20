package leo.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月20日 上午11:31:30
 * @Usage:
 */
public class SpiderUtil {

	public static void write(String filePath, String data) {
		File file = new File(filePath);
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(data);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (bw != null) {
						try {
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}
			}
		}
	}

	public static Properties getProperties(String propertiesPath) {
		Properties ppt = new Properties();
		try {
			ppt.load(SpiderUtil.class.getResourceAsStream(propertiesPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ppt;
	}

}

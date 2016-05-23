package leo.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月21日 下午3:57:02
 * @Usage:
 */
public class FileReader {
	/**
	 * 
	 * @param is
	 * @param inputStreamNeedToBeClosed
	 *            是否自动关闭入参的inputstream
	 * @return
	 */
	public static String read(InputStream is, boolean inputStreamNeedToBeClosed) {
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String lineData = "";
			StringBuilder sb = new StringBuilder();
			while ((lineData = br.readLine()) != null) {
				sb.append(lineData);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStreamNeedToBeClosed) {
					if (is != null) {
						System.out.println("close");
						is.close();
					}
				}
			} catch (Exception e) {

			} finally {
				if (isr != null) {
					try {
						isr.close();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (br != null) {
							try {
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static String read(String filePath) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is = new FileInputStream(filePath);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String lineData = "";
			StringBuilder sb = new StringBuilder();
			while ((lineData = br.readLine()) != null) {
				sb.append(lineData);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (isr != null) {
						try {
							isr.close();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							if (br != null) {
								try {
									br.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}

		}
		return null;
	}
}

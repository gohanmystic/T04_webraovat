package Utils;

public class CommonUtils {
	public static boolean isNotBlank(String value) {
		boolean result = false;
		
		if(value != null) {
			if(value.trim() != "") {
				return true;
			}
		}
		
		return result;
	}
}

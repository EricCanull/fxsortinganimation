/*
 * Permissions of this free software license are conditioned on making available
 * complete source code of licensed works and modifications under the same 
 * license or the GNU GPLv3. Copyright and license notices must be preserved.
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code 
 * for the larger work.
 */
package sortingalgoritms.util;

/**
 * Performs the counting for the algorithms iterations and provides access 
 * to the count. 
 * @author Eric Canull
 * @version 1.0
 */
public final class Logger {

	private static String infoText = "";
	private static long count;

	/**
	 * Creates an empty UtilLogger
	 */
	public Logger() {
		count = 0;
	}
	
	/**
	 * Resets the iteration count.
	 */
	public static void resetCount() {
		count = 0;
	}
	
	/**
	 * Gets the iteration count.
	 * @return The value for the iteration count
	 */
	public static long getCount() {
		return count;
	}
	
	/**
	 * Increments the count.
	 */
	public static void count() {
		count++;
	}

	/**
	 * Changes the text of this infoText. 
	 * @param text a reference specifying the new text of this infoText
	 */
	public static void setLogText(String text) {
		Logger.infoText = text;
	}

	/**
	 * Returns the text in this infoText.
	 * @return a String reference specifying the text in this infoText
	 */
	public static String getLogText() {
		return Logger.infoText;
	}
}
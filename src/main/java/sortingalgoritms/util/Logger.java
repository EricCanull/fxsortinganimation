/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sortingalgoritms.util;

/**
 * Performs the counting for the algorithms iterations and provides access to
 * the count.
 *
 * @author Eric Canull
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
     *
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
     *
     * @param text a reference specifying the new text of this infoText
     */
    public static void setLogText(String text) {
        Logger.infoText = text;
    }

    /**
     * Returns the text in this infoText.
     *
     * @return a String reference specifying the text in this infoText
     */
    public static String getLogText() {
        return Logger.infoText;
    }
}

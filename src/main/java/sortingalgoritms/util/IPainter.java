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

import javafx.scene.paint.Color;

/**
 * An interface used to apply colors for individual object values
 */
public interface IPainter {

    public void setColor(Color color);

    public Color getColor();
}

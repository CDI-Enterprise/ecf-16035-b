package fr.cdiEnterprise.view;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.plaf.ComponentUI;

/**
 * Idea found here:
 * http://forums.sun.com/thread.jspa?messageID=10887295
 * Coded by hand to suit my needs.
 *
 * This class causes whatever is put into it to get the same size as itself.
 */
public class ViewportPanel extends JPanel implements Scrollable {

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		
		System.out.println("getPreferredScrollableViewportSize");
		// The default implementation of this method.
		return getPreferredSize();
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {

		System.out.println("getScrollableBlockIncrement");
		// Pixels to represent SEVERAL rows.
		// Arbitrary number for kicks. Not actually needed
		// in my implementation.
		return 40;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		System.out.println("getScrollableTracksViewportHeight");
		// Don't enforce height
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
	
		System.out.println("getScrollableTracksViewportWidth");
		Component parent = getParent();
	    ComponentUI ui = getUI();

	    return parent != null ? (ui.getPreferredSize(this).width <= parent
	        .getSize().width) : true;
		
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {

		System.out.println("getScrollableUnitIncrement");
		// Amount to expose a single row
		// Arbitrary number for kicks. Not actually needed
		// in my implementation.
		return 15;
	}

}

package other;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class JRButton extends JButton {

	public JRButton() {
		super();
		decorate();
	}

	public JRButton(Icon icon) {
		super(icon);
		decorate();
	}

	public JRButton(String text) {
		super(text);
		decorate();
	}

	public JRButton(Action a) {
		super(a);
		decorate();
	}

	public JRButton(String text, Icon icon) {
		super(text, icon);
		decorate();
	}
	private void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
	int width=getWidth();
	int height=getHeight();
	
	Graphics2D g2d=(Graphics2D)g;
	
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	if(getModel().isArmed()) {
		g2d.setColor(getBackground().darker());
	}else if(getModel().isRollover()) {
		g2d.setColor(getBackground().brighter());
	}else {
		g2d.setColor(getBackground());
	}
	
	g2d.fillRoundRect(0, 0, width, height, 10, 10);
	
	FontMetrics fm=g2d.getFontMetrics();
	Rectangle sb= fm.getStringBounds(this.getText(), g2d).getBounds();
	
	int tx=(width-sb.width)/2;
	int ty=(height-sb.height)/2+fm.getAscent();
	
	g2d.setColor(getForeground());
	g2d.setFont(getFont());
	g2d.drawString(getText(), tx, ty);
	g2d.dispose();
	
	super.paintComponents(g);
	}
}

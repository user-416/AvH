import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class CustomButton extends JButton {
    private Shape shape;
    private String str, extra;
    private boolean mouseOver = false;
    private int x, y;
    

    public CustomButton(String label, Shape s, String extra) {
        super(label);
        str = label;
        shape = s;
        this.extra = extra;
        
        setContentAreaFilled(false);

        // Add a mouse listener to change the appearance when the mouse is over the button
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOver = true;
                setText(extra);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOver = false;
                setText(str);
                repaint();
            }
        });
        
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (mouseOver) {
            g2.setColor(new Color(62,66,63));
        } else {
            g2.setColor(new Color(46, 51, 48));
        }
        g2.fill(shape);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 50));

        FontMetrics fm = g2.getFontMetrics();
        Rectangle r = getBounds();
        String text = getText();
        int x = (r.width - fm.stringWidth(text)) / 2;
        int y = (r.height - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(text, x, y);
        
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.draw(shape);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }
    
    public void setExtra(String extra) {
    	this.extra = extra;
    }
    
    
}
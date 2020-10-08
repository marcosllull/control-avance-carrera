package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MiBoton extends JButton{
	
	private Color COLOR_FG = Color.WHITE;
	private Color COLOR_BG= new Color(72, 144, 240);
	private Color COLOR_BG_OVER = new Color(49, 120, 214);
	private Color COLOR_BG_PRESIONADO = new Color(72, 144, 240);
	
	
	public MiBoton (String text, Color colorBg, Color colorBgOver, Color colorBgPresionado) {
        super(text);
        super.setContentAreaFilled(false);
        super.setBackground(colorBg);
        super.setForeground(COLOR_FG);
        COLOR_BG = colorBg;
        COLOR_BG_OVER = colorBgOver;
        COLOR_BG_PRESIONADO = colorBgPresionado;
    }
	
	@Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed())
            g.setColor(COLOR_BG_PRESIONADO);
        else if (getModel().isRollover())
            g.setColor(COLOR_BG_OVER);
        else
            g.setColor(COLOR_BG);        
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}

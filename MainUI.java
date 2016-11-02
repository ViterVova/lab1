package stu.cn.ua.lab1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Optional;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JLabel;

public class MainUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int FRAME_HEIGHT=480;
	public static int FRAME_WIDTH=640;

	public static void main(String[] args) {
		new MainUI("Lab1");
	}
	
	public MainUI(String title){
		super(title);
		JPanel simulator=new JPanel();
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.8);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		splitPane.setRightComponent(panel);
		
		JSlider slider = new JSlider(JSlider.HORIZONTAL,0,100,10);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
			}
		});
		JLabel lblNewLabel = new JLabel("Ball  speed");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(slider);
		panel.add(lblNewLabel);
		
	
		JSlider slider_1 = new JSlider(JSlider.HORIZONTAL,10,60,10);
		slider_1.setMajorTickSpacing(10);
		slider_1.setMinorTickSpacing(10);
		slider_1.setPaintTicks(true);
		
		slider_1.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
						
			}
		});
		panel.add(slider_1);
		
		JLabel lblBallSize = new JLabel("Ball size");
		panel.add(lblBallSize);
	
	
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
			}
		});
		splitPane.setLeftComponent(simulator);
		simulator.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color;
				if (SwingUtilities.isLeftMouseButton(e))
					color=Color.RED;
				else
					color=Color.BLUE;
				new Thread(new Ball(e.getX(), e.getY(),slider_1.getValue(),slider.getValue(),simulator,color)).start();
			}
		});
		setVisible(true);
	}
}


class RoundedCornerButton extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Color color;
    protected int size;
    protected Shape shape;
    protected Shape border;
    protected Shape base;
    
    protected RoundedCornerButton(Color color, int size) {
        super();
        this.color=color;
        this.size=size;
    }
    protected RoundedCornerButton(Icon icon) {
        super(icon);
    }
    protected RoundedCornerButton(String text) {
        super(text);
    }
    protected RoundedCornerButton(Action a) {
        super(a);
    }
    protected RoundedCornerButton(String text, Icon icon) {
        super(text, icon);
    }
    @Override public void updateUI() {
        super.updateUI();
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBackground(color);
        initShape();
    }
    protected void initShape() {
        if (!getBounds().equals(base)) {
            base = getBounds();
            shape = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, size, size);
        }
    }
    
    @Override protected void paintComponent(Graphics g) {
        initShape();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (getModel().isArmed()) {
            g2.setPaint(color);
            g2.fill(shape);
        } else {
            g2.setPaint(color);
            g2.fill(shape);
        }
        g2.dispose();
        super.paintComponent(g);
    }
    @Override protected void paintBorder(Graphics g) {
        initShape();
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getForeground());
        g2.draw(shape);
        g2.dispose();
    }
    @Override public boolean contains(int x, int y) {
        initShape();
        return Optional.ofNullable(shape).map(s -> s.contains(x, y)).orElse(false);
    }
}

class RoundButton extends RoundedCornerButton {
	
    /**
	 * 
	 */
	private int size;
	
	private static final long serialVersionUID = 1L;
	protected RoundButton(Color color,int size) {
        super(color,size);
        this.size=size;
    }
    protected RoundButton(Icon icon) {
        super(icon);
    }
    protected RoundButton(String text) {
        super(text);
    }
    protected RoundButton(Action a) {
        super(a);
    }
    protected RoundButton(String text, Icon icon) {
        super(text, icon);
    }
    @Override public Dimension getPreferredSize() {
        return new Dimension(size,size);
        }
    @Override protected void initShape() {
        if (!getBounds().equals(base)) {
            base = getBounds();
            shape = new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}


package pages;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import other.FontList;

public class TimePanel extends JPanel {

	JLabel timeLabel;
	JLabel dateLabel;
	TimeSys st;
	/**
	 * Create the panel.
	 */
	public TimePanel() {
		setBackground(Color.WHITE);
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.LIGHT_GRAY, null));
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel label = new JLabel("");
		add(label);
		
		timeLabel = new JLabel("TIME");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(FontList.default50);
		add(timeLabel);
		
		dateLabel = new JLabel("date");
		dateLabel.setVerticalAlignment(SwingConstants.TOP);
		dateLabel.setFont(FontList.default40);
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(dateLabel);
		
		setVisible(true);
		st=new TimeSys();
		st.start();
	}
	
	class TimeSys extends Thread {

		public void run() {
			while (true) {
				long systime = System.currentTimeMillis();
				SimpleDateFormat fmt = new SimpleDateFormat("hh:mm a", Locale.KOREA);
				SimpleDateFormat fmd = new SimpleDateFormat("yyyy / MM / dd <E요일>", Locale.KOREA);
				timeLabel.setText(fmt.format(systime));
				dateLabel.setText(fmd.format(systime));
				try {
					super.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}

		
	}

}

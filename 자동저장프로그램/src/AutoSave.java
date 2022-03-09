import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

public class AutoSave extends JFrame {

	public AutoSave() {
		super("오늘의 일기");
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setForeground(Color.WHITE);

		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JTextArea contents = new JTextArea();
		contents.setLineWrap(true);

		contents.setFont(new Font("HY신명조", Font.PLAIN, 20));
		contents.setBackground(SystemColor.inactiveCaptionBorder);

		contents.setBounds(14, 57, 443, 345);
		getContentPane().add(contents);

		JLabel title = new JLabel("오늘의 일기 ▼ " + LocalDate.now());
		title.setFont(new Font("HY신명조", Font.PLAIN, 24));
		title.setBounds(14, 12, 443, 33);
		getContentPane().add(title);

		JLabel info = new JLabel();
		info.setFont(new Font("HY신명조", Font.ITALIC, 16));
		info.setBounds(281, 414, 176, 27);
		getContentPane().add(info);

		Timer timer = new Timer(10000, new ActionListener() {
			BufferedWriter bw;

			@Override
			public void actionPerformed(ActionEvent e) {
				File directory = new File("C:\\오늘의 일기");
				directory.mkdir();

				File myDiary = new File("C:\\오늘의 일기\\" + String.valueOf(LocalDate.now()) + ".txt");

				if (!myDiary.exists()) {
					try {
						myDiary.createNewFile();

					} catch (IOException e1) {

						e1.printStackTrace();
					}
				}

				try {
					bw = new BufferedWriter(new FileWriter(myDiary));
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					bw.write(contents.getText());

					info.setText("임시 저장 되었습니다");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					bw.flush();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		timer.start();

		Timer n = new Timer(12000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				info.setText("");

			}
		});
		n.start();
	}

	public static void main(String[] args) {

		new AutoSave().setVisible(true);
	}
}

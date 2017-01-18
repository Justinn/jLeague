package net.jLeague.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import com.google.gson.JsonSyntaxException;

import net.jLeague.Converter;
import net.jLeague.api.stats.AggregatedStats;
import net.jLeague.api.stats.RankedStats;
import net.jLeague.api.summoner.Summoner;
import net.jLeague.net.RiotException;
import javax.swing.JTextArea;

public class ApplicationGUI extends JFrame implements ActionListener {

	private Summoner summoner;
	private JLabel lblSummonerProfileIcon;
	private Converter converter;
	private JPanel rankedPanel;
	private JTextField summonerNameTxtField;
	private JLabel lblSummonerName;
	@SuppressWarnings("rawtypes")
	private JComboBox regionBox;
	private JLabel lblPlayerAnalysis;
	private int allWins;
	private int allLosses;
	private int allChampsKilled;
	private int allDeaths;
	private boolean rankedSolo;

	/**
	 * Create the Frame
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ApplicationGUI() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 11));
		setTitle("jLeague");
		setBounds(100, 100, 806, 630);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		getContentPane().add(tabbedPane);

		JPanel basicPanel = new JPanel();
		tabbedPane.addTab("Basic Information", null, basicPanel, null);

		rankedPanel = new JPanel();
		tabbedPane.addTab("Ranked Stats", null, rankedPanel, null);

		JPanel currentGame = new JPanel();
		tabbedPane.addTab("Current Game", null, currentGame, null);
		tabbedPane.setEnabledAt(2, false);
		converter = new Converter();
		ImageIcon icon = converter.obtainProfileIcon(1);
		basicPanel.setLayout(null);

		lblSummonerProfileIcon = new JLabel("");
		lblSummonerProfileIcon.setBounds(10, 34, 128, 128);
		lblSummonerProfileIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSummonerProfileIcon.setFont(new Font("Arial", Font.PLAIN, 12));
		basicPanel.add(lblSummonerProfileIcon);
		lblSummonerProfileIcon.setIcon(icon);

		JLabel lblPlayerAnalysisTitle = new JLabel("Profile Icon");
		lblPlayerAnalysisTitle.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPlayerAnalysisTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerAnalysisTitle.setLabelFor(lblSummonerProfileIcon);
		lblPlayerAnalysisTitle.setBounds(10, 11, 128, 23);
		basicPanel.add(lblPlayerAnalysisTitle);

		JPanel analysisBorder = new JPanel();
		analysisBorder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		analysisBorder.setBounds(158, 34, 461, 135);
		basicPanel.add(analysisBorder);
		analysisBorder.setLayout(null);

		lblPlayerAnalysis = new JLabel("Welcome to jLeague, the League of Legends all-in-one toolkit!");
		lblPlayerAnalysis.setBounds(0, 0, 461, 133);
		analysisBorder.add(lblPlayerAnalysis);
		lblPlayerAnalysis.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerAnalysis.setFont(new Font("Arial", Font.ITALIC, 16));

		JLabel lblNewLabel_2 = new JLabel("Player Analysis");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(164, 11, 454, 20);
		basicPanel.add(lblNewLabel_2);

		JPanel searchPanel = new JPanel();
		searchPanel.setBorder(UIManager.getBorder("InternalFrame.border"));
		getContentPane().add(searchPanel, BorderLayout.SOUTH);
		FlowLayout fl_searchPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		searchPanel.setLayout(fl_searchPanel);

		JLabel lblSummoner = new JLabel("Summoner Name:");
		lblSummoner.setFont(new Font("Arial", Font.PLAIN, 12));
		searchPanel.add(lblSummoner);

		summonerNameTxtField = new JTextField();
		summonerNameTxtField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					searchSummoner();
			}
		});
		summonerNameTxtField.setHorizontalAlignment(SwingConstants.CENTER);
		summonerNameTxtField.setFont(new Font("Arial", Font.PLAIN, 12));
		searchPanel.add(summonerNameTxtField);
		summonerNameTxtField.setColumns(10);

		Button searchBtn = new Button("Search");
		searchBtn.addActionListener(this);
		searchPanel.add(searchBtn);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		rigidArea.setEnabled(false);
		searchPanel.add(rigidArea);

		JLabel lblRegion = new JLabel("Region:");
		lblRegion.setFont(new Font("Arial", Font.PLAIN, 12));
		searchPanel.add(lblRegion);

		regionBox = new JComboBox();
		regionBox.setModel(new DefaultComboBoxModel(
				new String[] { "NA", "EUW", "LAN", "KR", "BR", "JP", "EUNE", "LAS", "OCE", "RU", "TR" }));
		searchPanel.add(regionBox);

		JPanel currentSummonerPanel = new JPanel();
		getContentPane().add(currentSummonerPanel, BorderLayout.NORTH);

		lblSummonerName = new JLabel("jLeague");
		lblSummonerName.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		currentSummonerPanel.add(lblSummonerName);
		clearSummoner();
	}

	private void searchSummoner() {
		if (summonerNameTxtField.getText().equalsIgnoreCase(""))
			lblSummonerName.setText("jLeague");
		else {
			try {
				summoner = converter.getSummoner(summonerNameTxtField.getText().toLowerCase(),
						regionBox.getSelectedItem().toString());
				if (summoner != null) {
					lblSummonerName.setText(summoner.getName());
					ImageIcon icon = converter.obtainProfileIcon(summoner.getProfileIconId());
					lblSummonerProfileIcon.setIcon(icon);
					updatePlayerAnalysis();
				}
			} catch (RiotException e) {
				lblSummonerName.setText(e.getMessage());
			}
		}
	}

	private void loadBasicStats() {
		try {
			RankedStats rankedStats = converter.getRankedStats(summoner.getId(), "NA");
			for (int i = 1; i < rankedStats.getChampions().size(); i++) {
				if (rankedStats.getChampions().get(i).getId() == 0) {
					AggregatedStats allStats = rankedStats.getChampions().get(i).getStats();
					allWins = allStats.getTotalSessionsWon();
					allLosses = allStats.getTotalSessionsLost();
					allChampsKilled = allStats.getTotalChampionKills();
					allDeaths = allStats.getTotalDeathsPerSession();
					rankedSolo = true;
				}
			}
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (RiotException e) {
			System.out.println(e.getMessage());
			rankedSolo = false;
		}
	}

	private void loadRankedSummary() {

	}

	private void updatePlayerAnalysis() {
		String pA = new String();
		clearSummoner();
		loadBasicStats();
		if (summoner.getSummonerLevel() < 30)
			pA = "<b>" + summoner.getName() + "</b> is level <b>" + summoner.getSummonerLevel() + "</b>.";
		else if (rankedSolo) {
			loadRankedInformation();
			pA = summoner.getName() + " is currently ranked ";
		} else if (!rankedSolo) {
			pA = "<b>" + summoner.getName() + "</b> is level <b>" + summoner.getSummonerLevel() + "</b>. <b>"
					+ summoner.getName() + "</b> is currently <b>unranked</b>.";
		}
		lblPlayerAnalysis.setText("<html><center>" + pA + "</center></html>");
	}
	
	private void loadRankedInformation() {
		
	}

	private void clearSummoner() {
		allWins = 0;
		allLosses = 0;
		allDeaths = 0;
		allChampsKilled = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().equalsIgnoreCase("Search")) {
			searchSummoner();
		}
	}
}

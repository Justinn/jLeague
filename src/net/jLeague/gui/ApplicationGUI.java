package net.jLeague.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

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

import net.jLeague.api.champion.ChampionMastery;
import net.jLeague.api.game.Game;
import net.jLeague.api.game.RecentGames;
import net.jLeague.api.league.League;
import net.jLeague.api.staticdata.Champion;
import net.jLeague.api.stats.AggregatedStats;
import net.jLeague.api.stats.RankedStats;
import net.jLeague.api.summoner.Summoner;
import net.jLeague.net.RiotException;
import net.jLeague.utils.JSONReader;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.Color;

public class ApplicationGUI extends JFrame implements ActionListener {

	private Summoner summoner;
	private JLabel lblSummonerProfileIcon;
	private JSONReader converter;
	private JPanel rankedPanel;
	private JTextField summonerNameTxtField;
	private JLabel lblSummonerName;
	@SuppressWarnings("rawtypes")
	private JComboBox regionBox;
	private JLabel lblPlayerAnalysis;
	private JLabel lblFavChamps[];
	private JLabel lblRecentChampions[];
	private String soloRank;
	private String flexRank;

	/**
	 * Create the Frame and adds all the panels and components to the frame
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
		converter = new JSONReader("NA");
		ImageIcon defaultIcon = null;
		try {
			defaultIcon = converter.obtainProfileIcon(1, "NA");
		} catch (RiotException e1) {
			e1.printStackTrace();
		}
		basicPanel.setLayout(null);

		lblSummonerProfileIcon = new JLabel("");
		lblSummonerProfileIcon.setBounds(10, 34, 128, 128);
		lblSummonerProfileIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblSummonerProfileIcon.setFont(new Font("Arial", Font.PLAIN, 12));
		basicPanel.add(lblSummonerProfileIcon);
		lblSummonerProfileIcon.setIcon(defaultIcon);

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
		lblPlayerAnalysis.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblNewLabel_2 = new JLabel("Player Analysis");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 17));
		lblNewLabel_2.setBounds(164, 11, 454, 20);
		basicPanel.add(lblNewLabel_2);

		JPanel favChampsPanel = new JPanel();
		favChampsPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Favorite Champions",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		favChampsPanel.setBounds(10, 193, 609, 153);
		basicPanel.add(favChampsPanel);

		lblFavChamps = new JLabel[5];

		for (int i = 0; i < lblFavChamps.length; i++) {
			lblFavChamps[i] = new JLabel("");
			favChampsPanel.add(lblFavChamps[i]);
		}

		JPanel recentChampsPanel = new JPanel();
		recentChampsPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Recently Played Champions", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		recentChampsPanel.setBounds(10, 357, 609, 153);
		basicPanel.add(recentChampsPanel);

		lblRecentChampions = new JLabel[5];

		for (int i = 0; i < lblRecentChampions.length; i++) {
			lblRecentChampions[i] = new JLabel("");
			recentChampsPanel.add(lblRecentChampions[i]);
			
		}

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
		soloRank = new String("Unranked");
		flexRank = new String("Unranked");
	}

	/**
	 * searchSummoner() - This method handles updates the gui and searches for
	 * all the input summoner's data
	 * 
	 * @throws RiotException
	 *             if we run into any of those. Will return the error into the
	 *             gui
	 */
	private void searchSummoner() {
		long timeStart = System.currentTimeMillis();
		clearSummoner();
		loadSummoner();
		if (summoner != null) {
			loadLeagues();
			updatePlayerAnalysis();
			loadFavoriteChampions();
			loadRecentChampions();
		}

		long timeEnd = System.currentTimeMillis();
		long time = timeEnd - timeStart;
		System.out.println("Took " + time / 1000 + " seconds to load this page");
	}

	/**
	 * loadSummoner() - Loads the summoner object and displays information that
	 * it can - Summoner Name - Profile Icon from profileIconId
	 */
	private void loadSummoner() {
		if (summonerNameTxtField.getText().equalsIgnoreCase(""))
			lblSummonerName.setText("jLeague");
		else {
			try {
				// Get the summoner object
				summoner = converter.getSummoner(summonerNameTxtField.getText().toLowerCase(),
						regionBox.getSelectedItem().toString());

				// If we found the summoner object
				if (summoner != null) {

					// Set the name in the UI to the summoner's name
					lblSummonerName.setText(summoner.getName());

					// Get the icon using the summoner's profile icon id
					ImageIcon icon = converter.obtainProfileIcon(summoner.getProfileIconId(),
							regionBox.getSelectedItem().toString());

					// Set the icon in the UI
					lblSummonerProfileIcon.setIcon(icon);
				}
			} catch (RiotException e) {
				lblSummonerName.setText(e.getMessage());
			}
		}
	}

	private void loadRecentChampions() {
		RecentGames recent;
		try {
			recent = converter.getRecentGames(summoner.getId(), regionBox.getSelectedItem().toString());
			Set<Game> games = recent.getGames();
			int count = 0;
			for (Game game : games) {
				if (count < lblRecentChampions.length) {
					if (!game.equals(null)) {
						String name = converter
								.getChampionById(game.getChampionId(), regionBox.getSelectedItem().toString()).getKey();

						ImageIcon icon = converter.getChampionIcon(name, regionBox.getSelectedItem().toString());

						lblRecentChampions[count].setIcon(resizeImageIcon(75, 75, icon));
					}
				}
				count++;
			}
		} catch (RiotException e) {
			e.printStackTrace();
		}
	}

	private void loadFavoriteChampions() {
		try {
			List<ChampionMastery> topChamps = converter.getTopChampionMastery(summoner.getId(),
					regionBox.getSelectedItem().toString());
			for (int i = 0; i < lblFavChamps.length; i++) {
				if (!topChamps.get(i).equals(null)) {

					String name = converter.getChampionById((int) topChamps.get(i).getChampionId(),
							regionBox.getSelectedItem().toString()).getKey();

					ImageIcon icon = converter.getChampionIcon(name, regionBox.getSelectedItem().toString());

					lblFavChamps[i].setIcon(resizeImageIcon(75, 75, icon));
				}
			}
		} catch (

		RiotException e) {
			e.printStackTrace();
		}
	}
	
	private ImageIcon resizeImageIcon(int width, int height, ImageIcon icon) {
		Image image = icon.getImage();
		image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(image);
		return icon;
	}

	private void updatePlayerAnalysis() {
		String analysis = new String();
		analysis = "<b>" + summoner.getName() + "</b> is currently level <b>" + summoner.getSummonerLevel()
				+ "</b>. <b>" + soloRank + "</b> in <i>Solo Queue</i> and <b>" + flexRank
				+ "</b> in <i>Flex Queue</i>.";
		lblPlayerAnalysis.setText("<html><center>" + analysis + "</center></html>");
	}

	private void loadLeagues() {
		if (summoner != null) {
			try {
				List<League> leagues = converter.getLeague(summoner.getId(), "NA");
				for (League league : leagues) {
					if (league.getQueue().equalsIgnoreCase("RANKED_SOLO_5x5"))
						soloRank = league.getTier() + " " + league.getEntries().get(0).getDivision();
					else if (league.getQueue().equalsIgnoreCase("RANKED_FLEX_SR"))
						flexRank = league.getTier() + " " + league.getEntries().get(0).getDivision();
				}
			} catch (RiotException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private void clearSummoner() {
		soloRank = "Unranked";
		flexRank = "Unranked";
		for (JLabel favChamp : lblFavChamps)
			favChamp.setIcon(null);
		for (JLabel recentChamp : lblRecentChampions)
			recentChamp.setIcon(null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getActionCommand().toString().equalsIgnoreCase("Search")) {
			if (summonerNameTxtField.toString().equalsIgnoreCase(""))
				searchSummoner();
		}
	}
}

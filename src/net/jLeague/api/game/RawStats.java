package net.jLeague.api.game;

/**
 * This object contains raw stat information
 * 
 * @author Justin Mintzer
 *
 */
public class RawStats {
	
	private int assists;
	private int barracksKilled; //inhibitors
	private int bountyLevel;
	private int championsKilled;
	private int combatPlayerScore;
	private int consumablesPurchased;
	private int damageDealtPlayer;
	private int doubleKills;
	private int firstBlood;
	private int gold;
	private int goldEarned;
	private int goldSpent;
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private int item6;
	private int itemsPurchased;
	private int killingSprees;
	private int largestCriticalStrike;
	private int largestKillingSpree;
	private int largestMultiKill;
	private int legendaryItemsCreated;
	private int level;
	private int magicDamageDealtPlayer;
	private int magicDamageTaken;
	private int minionsDenied;
	private int minionsKilled;
	private int neutralMinionsKilled;
	private int neutralMinionsKilledEnemyJungle;
	private int neutralMinionsKilledYourJungle;
	private boolean nexusKilled;
	private int nodeCapture;
	private int nodeCaptureAssist;
	private int nodeNeutralize;
	private int nodeNeutralizeAssist;
	private int numDeaths;
	private int numItemsBought;
	private int objectivePlayerScore;
	private int pentaKills;
	private int physicalDamageDealtPlayer;
	private int physicalDamageDealtToChampions;
	private int physicalDamageTaken;
	private int playerPosition; //Player position (Legal values: TOP(1), MIDDLE(2), JUNGLE(3), BOT(4))
	private int playerRole; //Player role (Legal values: DUO(1), SUPPORT(2), CARRY(3), SOLO(4))
	private int playerScore0;
	private int playerScore1;
	private int playerScore2;
	private int playerScore3;
	private int playerScore4;
	private int playerScore5;
	private int playerScore6;
	private int playerScore7;
	private int playerScore8;
	private int playerScore9;
	private int quadraKills;
	private int sightWardsBought;
	private int spell1Cast;
	private int spell2Cast;
	private int spell3Cast;
	private int spell4Cast;
	private int summonSpell1Cast;
	private int summonSpell2Cast;
	private int superMonsterKilled;
	private int team;
	private int teamObjective;
	private int timePlayed;
	private int totalDamageDealt;
	private int totalDamageDealtToBuildings;
	private int totalDamageDealtToChampions;
	private int totalDamageTaken;
	private int totalHeal;
	private int totalPlayerScore;
	private int scoreRank;
	private int totalTimeCrowdControlDealt;
	private int totalUnitsHealed;
	private int tripleKills;
	private int trueDamageDealtPlayer;
	private int trueDamageDealtToChampions;
	private int trueDamageTaken;
	private int turretsKilled;
	private int unrealKills;
	private int victoryPointTotal;
	private int visionWardsBought;
	private int wardKilled;
	private int wardPlaced;
	private boolean win;
	
	public int getAssists() {
		return assists;
	}
	public int getBarracksKilled() {
		return barracksKilled;
	}
	public int getBountyLevel() {
		return bountyLevel;
	}
	public int getChampionsKilled() {
		return championsKilled;
	}
	public int getCombatPlayerScore() {
		return combatPlayerScore;
	}
	public int getConsumablesPurchased() {
		return consumablesPurchased;
	}
	public int getDamageDealtPlayer() {
		return damageDealtPlayer;
	}
	public int getDoubleKills() {
		return doubleKills;
	}
	public int getFirstBlood() {
		return firstBlood;
	}
	public int getGold() {
		return gold;
	}
	public int getGoldEarned() {
		return goldEarned;
	}
	public int getGoldSpent() {
		return goldSpent;
	}
	public int getItem0() {
		return item0;
	}
	public int getItem1() {
		return item1;
	}
	public int getItem2() {
		return item2;
	}
	public int getItem3() {
		return item3;
	}
	public int getItem4() {
		return item4;
	}
	public int getItem5() {
		return item5;
	}
	public int getItem6() {
		return item6;
	}
	public int getItemsPurchased() {
		return itemsPurchased;
	}
	public int getKillingSprees() {
		return killingSprees;
	}
	public int getLargestCriticalStrike() {
		return largestCriticalStrike;
	}
	public int getLargestKillingSpree() {
		return largestKillingSpree;
	}
	public int getLargestMultiKill() {
		return largestMultiKill;
	}
	public int getLegendaryItemsCreated() {
		return legendaryItemsCreated;
	}
	public int getLevel() {
		return level;
	}
	public int getMagicDamageDealtPlayer() {
		return magicDamageDealtPlayer;
	}
	public int getMagicDamageTaken() {
		return magicDamageTaken;
	}
	public int getMinionsDenied() {
		return minionsDenied;
	}
	public int getMinionsKilled() {
		return minionsKilled;
	}
	public int getNeutralMinionsKilled() {
		return neutralMinionsKilled;
	}
	public int getNeutralMinionsKilledEnemyJungle() {
		return neutralMinionsKilledEnemyJungle;
	}
	public int getNeutralMinionsKilledYourJungle() {
		return neutralMinionsKilledYourJungle;
	}
	public boolean isNexusKilled() {
		return nexusKilled;
	}
	public int getNodeCapture() {
		return nodeCapture;
	}
	public int getNodeCaptureAssist() {
		return nodeCaptureAssist;
	}
	public int getNodeNeutralize() {
		return nodeNeutralize;
	}
	public int getNodeNeutralizeAssist() {
		return nodeNeutralizeAssist;
	}
	public int getNumDeaths() {
		return numDeaths;
	}
	public int getNumItemsBought() {
		return numItemsBought;
	}
	public int getObjectivePlayerScore() {
		return objectivePlayerScore;
	}
	public int getPentaKills() {
		return pentaKills;
	}
	public int getPhysicalDamageDealtPlayer() {
		return physicalDamageDealtPlayer;
	}
	public int getPhysicalDamageDealtToChampions() {
		return physicalDamageDealtToChampions;
	}
	public int getPhysicalDamageTaken() {
		return physicalDamageTaken;
	}
	public int getPlayerPosition() {
		return playerPosition;
	}
	public int getPlayerRole() {
		return playerRole;
	}
	public int getPlayerScore0() {
		return playerScore0;
	}
	public int getPlayerScore1() {
		return playerScore1;
	}
	public int getPlayerScore2() {
		return playerScore2;
	}
	public int getPlayerScore3() {
		return playerScore3;
	}
	public int getPlayerScore4() {
		return playerScore4;
	}
	public int getPlayerScore5() {
		return playerScore5;
	}
	public int getPlayerScore6() {
		return playerScore6;
	}
	public int getPlayerScore7() {
		return playerScore7;
	}
	public int getPlayerScore8() {
		return playerScore8;
	}
	public int getPlayerScore9() {
		return playerScore9;
	}
	public int getQuadraKills() {
		return quadraKills;
	}
	public int getSightWardsBought() {
		return sightWardsBought;
	}
	public int getSpell1Cast() {
		return spell1Cast;
	}
	public int getSpell2Cast() {
		return spell2Cast;
	}
	public int getSpell3Cast() {
		return spell3Cast;
	}
	public int getSpell4Cast() {
		return spell4Cast;
	}
	public int getSummonSpell1Cast() {
		return summonSpell1Cast;
	}
	public int getSummonSpell2Cast() {
		return summonSpell2Cast;
	}
	public int getSuperMonsterKilled() {
		return superMonsterKilled;
	}
	public int getTeam() {
		return team;
	}
	public int getTeamObjective() {
		return teamObjective;
	}
	public int getTimePlayed() {
		return timePlayed;
	}
	public int getTotalDamageDealt() {
		return totalDamageDealt;
	}
	public int getTotalDamageDealtToBuildings() {
		return totalDamageDealtToBuildings;
	}
	public int getTotalDamageDealtToChampions() {
		return totalDamageDealtToChampions;
	}
	public int getTotalDamageTaken() {
		return totalDamageTaken;
	}
	public int getTotalHeal() {
		return totalHeal;
	}
	public int getTotalPlayerScore() {
		return totalPlayerScore;
	}
	public int getScoreRank() {
		return scoreRank;
	}
	public int getTotalTimeCrowdControlDealt() {
		return totalTimeCrowdControlDealt;
	}
	public int getTotalUnitsHealed() {
		return totalUnitsHealed;
	}
	public int getTripleKills() {
		return tripleKills;
	}
	public int getTrueDamageDealtPlayer() {
		return trueDamageDealtPlayer;
	}
	public int getTrueDamageDealtToChampions() {
		return trueDamageDealtToChampions;
	}
	public int getTrueDamageTaken() {
		return trueDamageTaken;
	}
	public int getTurretsKilled() {
		return turretsKilled;
	}
	public int getUnrealKills() {
		return unrealKills;
	}
	public int getVictoryPointTotal() {
		return victoryPointTotal;
	}
	public int getVisionWardsBought() {
		return visionWardsBought;
	}
	public int getWardKilled() {
		return wardKilled;
	}
	public int getWardPlaced() {
		return wardPlaced;
	}
	public boolean isWin() {
		return win;
	}

}

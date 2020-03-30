package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    private Hashtable<String, SoccerPlayer> myPlayers = new Hashtable<>();
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        String player =(firstName+"##"+lastName);
        if(myPlayers.get(player) == null){
            // unnecessary check. Experimenting with the hashtable interface
            if (myPlayers.put(player, new SoccerPlayer(firstName, lastName, uniformNumber, teamName)) == null) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String player = (firstName + "##" + lastName);
        if (myPlayers.remove(player) == null) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String player = (firstName+"##"+lastName);
        return myPlayers.get(player);
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        SoccerPlayer player = myPlayers.get(firstName + "##" + lastName);
        if (player == null) {
            return false;
        }
        player.bumpGoals();
        return true;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        SoccerPlayer player = myPlayers.get(firstName + "##" + lastName);
        if (player == null) {
            return false;
        }
        player.bumpAssists();
        return true;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        SoccerPlayer player = myPlayers.get(firstName + "##" + lastName);
        if (player == null) {
            return false;
        }
        player.bumpShots();
        return true;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        SoccerPlayer player = myPlayers.get(firstName + "##" + lastName);
        if (player == null) {
            return false;
        }
        player.bumpSaves();
        return true;

    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        SoccerPlayer player = myPlayers.get(firstName + "##" + lastName);
        if (player == null) {
            return false;
        }
        player.bumpFouls();
        return true;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        SoccerPlayer player = myPlayers.get(firstName + "##" + lastName);
        if (player == null) {
            return false;
        }
        player.bumpYellowCards();
        return true;

    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        SoccerPlayer player = myPlayers.get(firstName + "##" + lastName);
        if (player == null) {
            return false;
        }
        player.bumpRedCards();
        return true;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        if (teamName == null) {
            return myPlayers.size();
        }

        int size = 0;
        Set<String> keys = myPlayers.keySet();
        Iterator<String> dbIterator = keys.iterator();
        while (dbIterator.hasNext()){
            String playerName = dbIterator.next();
            SoccerPlayer player = myPlayers.get(playerName);
            String playersTeam = player.getTeamName();
            if (playersTeam.equals(teamName)){
                size++;
            }
        }
        return size;
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        int playerNumber = 0;
        Set<String> keys = myPlayers.keySet();
        Iterator<String> dbIterator = keys.iterator();
        while (dbIterator.hasNext()) {
            String playerName = dbIterator.next();
            if (teamName == null){
                if (idx == playerNumber){
                    return myPlayers.get(playerName);
                }
                else {
                    playerNumber++;
                }
            }
            else {
                SoccerPlayer player = myPlayers.get(playerName);
                String playerTeam = player.getTeamName();
                if (teamName.equals(playerTeam)){
                    if (idx == playerNumber){
                        return myPlayers.get(playerName);
                    }
                    else {
                        playerNumber++;
                    }
                }
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        return false; //extra credit?
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            Set<String> keys = myPlayers.keySet();
            Iterator<String> dbIterator = keys.iterator();
            while (dbIterator.hasNext()){
                String playerName = dbIterator.next();
                SoccerPlayer player = myPlayers.get(playerName);
                writer.println(logString(player.getFirstName()));
                writer.println(logString(player.getLastName()));
                writer.println(logString(player.getTeamName()));
                writer.println(logString(player.getUniform()+""));
                writer.println(logString(player.getGoals()+""));
                writer.println(logString(player.getAssists()+""));
                writer.println(logString(player.getShots()+""));
                writer.println(logString(player.getFouls()+""));
                writer.println(logString(player.getSaves()+""));
                writer.println(logString(player.getYellowCards()+""));
                writer.println(logString(player.getRedCards()+""));
            }
            writer.close();
            return true;
        }
        catch (FileNotFoundException exception) {
            logString(exception.getMessage());
            return false;
        }
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

}

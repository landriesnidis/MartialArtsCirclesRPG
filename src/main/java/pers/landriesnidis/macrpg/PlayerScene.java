package pers.landriesnidis.macrpg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.alibaba.fastjson.JSON;

import pers.landriesnidis.macrpg.manager.PlayerManager;
import pers.landriesnidis.macrpg.manager.TextMenuManager;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.macrpg.scene.village.VillageMap;
import pers.landriesnidis.ptm4j.scene.Scene;
import pers.landriesnidis.ptm4j.scene.io.SceneReader;
import pers.landriesnidis.sqlitehelper.RowMapper;
import pers.landriesnidis.sqlitehelper.SqliteHelper;

public class PlayerScene extends Scene {

	public static SqliteHelper helper;
	
	static{
		try {
			helper = new SqliteHelper("./db/MartialArtsCirclesRPG.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Player player;
	
	public PlayerScene(String sessionId, SceneReader reader) {
		super(reader);
		
		player = readPlayerDataBySessionId(sessionId);
		if(player == null){
			player = new Player();
			player.setSessionId(sessionId);
			setRootMenu(new LoginMenu());
		}else{
			setRootMenu(TextMenuManager.getMenuByClass(VillageMap.class));
		}
		PlayerManager.addPlayerScene(this, player);
	}
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public static Player readPlayerDataBySessionId(String sessionId){
		try {
			List<String> sList = helper.executeQuery(String.format("select json from player where session_id='%s';", sessionId), new RowMapper<String>() {
                public String mapRow(ResultSet rs, int index)
                        throws SQLException {
                    return rs.getString("json");
                }
            });
			// 判断有没有数据
			if(sList.isEmpty()){
				return null;
			}else{
				return JSON.parseObject(sList.get(0), Player.class);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Player readPlayerDataByPlayerName(String palyerName){
		try {
			List<String> sList = helper.executeQuery(String.format("select json from player where name='%s';", palyerName), new RowMapper<String>() {
                public String mapRow(ResultSet rs, int index)
                        throws SQLException {
                    return rs.getString("json");
                }
            });
			// 判断有没有数据
			if(sList.isEmpty()){
				return null;
			}else{
				return JSON.parseObject(sList.get(0), Player.class);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void updatePlayerDataInDB(Player player){
		try {
			helper.executeUpdate(String.format("update player set json='%s',session_id='%s' where name='%s';", JSON.toJSONString(player),player.getSessionId(),player.getName()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean insertPlayerDataToDB(Player player){
		try {
			helper.executeUpdate(String.format("insert into player(session_id,name) values('%s','%s');", player.getSessionId(),player.getName()));
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean checkPlayerNameOnlyOne(String playerName){
		try {
			List<Integer> sList = helper.executeQuery(String.format("select count(*) as num from player where name='%s';", playerName), new RowMapper<Integer>() {
			    public Integer mapRow(ResultSet rs, int index)
			            throws SQLException {
			        return rs.getInt(1);
			    }
			});
			if(sList.isEmpty() || sList.get(0)==0)
				return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
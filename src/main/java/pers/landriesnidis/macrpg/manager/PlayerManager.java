package pers.landriesnidis.macrpg.manager;

import java.util.HashMap;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.ptm4j.scene.Scene;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

public class PlayerManager {
	
	/*************************************************************/
    //						   脑残单例
    /*************************************************************/
    private static PlayerManager instance;
    private static PlayerManager getInstance() {
    	if(instance==null){
    		instance = new PlayerManager();
    	}
    	return instance;
    }
    private PlayerManager() {
    	players = new HashMap<ISceneContext, Player>();
	}
    
    /*************************************************************/
    //						   一堆玩家
    /*************************************************************/
    private HashMap<ISceneContext, Player> players;
    public HashMap<ISceneContext, Player> getPlayers() {
		return players;
	}
    public void setPlayers(HashMap<ISceneContext, Player> players) {
		this.players = players;
	}
	
	/**
	 * 通过ISceneContext获取Player对象
	 * @param sceneContext 通过Scene获取的会话环境
	 * @return
	 */
	public static Player getPlayerBySceneContext(ISceneContext sceneContext){
    	return getInstance().getPlayers().get(sceneContext);
    }
	
	public static void addPlayerScene(Scene scene, Player player){
		getInstance().getPlayers().put(scene, player);
	}
	
	public static void saveAllPlayerDataToDB(){
		for(ISceneContext sceneContext:getInstance().getPlayers().keySet()){
			PlayerScene.updatePlayerDataInDB(getPlayerBySceneContext(sceneContext));
		}
	}
}

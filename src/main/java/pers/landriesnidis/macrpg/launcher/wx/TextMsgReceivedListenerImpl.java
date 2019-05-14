package pers.landriesnidis.macrpg.launcher.wx;

import java.util.HashMap;

import cn.zhouyafeng.itchat4j.api.MessageTools;
import pers.landriesnidis.itchat4j_quickstart.bean.Contact;
import pers.landriesnidis.itchat4j_quickstart.bean.Group;
import pers.landriesnidis.itchat4j_quickstart.bean.GroupMember;
import pers.landriesnidis.itchat4j_quickstart.listener.TextMsgReceivedListener;
import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.manager.PlayerManager;
import pers.landriesnidis.ptm4j.menu.context.IMenuContext;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;
import pers.landriesnidis.ptm4j.scene.io.SceneReader;

public class TextMsgReceivedListenerImpl implements TextMsgReceivedListener{

	private HashMap<Contact, PlayerScene> players = new HashMap<Contact, PlayerScene>();
	
	public void onMyselChatMsgReceived(Contact self, String text) {
		if(text.equals("保存游戏")) {
			PlayerManager.saveAllPlayerDataToDB();
		}
	}

	public void onOtherPlatformsSyncMessageReceived(Contact receiver, String text) {
		
	}

	public void onPrivateChatTextMsgReceived(Contact contact, String text) {
		final String userId = contact.getUserId();
		if(players.containsKey(contact)){
			players.get(contact).input(text, null);
		}else{
			if(text.contains("开始游戏")){
				players.put(contact, new PlayerScene(contact.getUserId(), new SceneReader() {
					public void output(String text, IMenuContext menuContext, ISceneContext sceneContext, Object dataTag) {
						MessageTools.sendMsgById(text, userId);
					}
				}));
			}
		}
	}

	public void onGroupChatTextMsgReceived(Group group, GroupMember groupMember, String text, boolean isAtBot) {
		
	}

	@Override
	public void onMyselfChatMsgReceived(Contact arg0, String arg1) {
		
	}

}

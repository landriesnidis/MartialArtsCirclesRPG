package pers.landriesnidis.macrpg.launcher.wx;

import cn.zhouyafeng.itchat4j.controller.IWechatStateCallBack;
import pers.landriesnidis.itchat4j_quickstart.WechatLauncher;
import pers.landriesnidis.itchat4j_quickstart.handler.DefaultMsgHandler;
import pers.landriesnidis.macrpg.launcher.DataLoader;
import pers.landriesnidis.macrpg.manager.PlayerManager;

public class WechatServiceLauncher {
	public static void main(String[] args) {
		// 加载资源文件
		DataLoader.loadResourceFile();
		// 启动微信
		DefaultMsgHandler handler = new DefaultMsgHandler();
		TextMsgReceivedListenerImpl listener = new TextMsgReceivedListenerImpl();
		handler.setTextListener(listener);
		WechatLauncher.AutoStart(handler, "./", new IWechatStateCallBack() {
			
			public void onLoginTimeout() {
				
			}
			
			public void onLoginSuccessful() {
				
			}
			
			public void onGotWechatUUID() {
				
			}
			
			public void onGotLoginQRCodePicSuccessful(String path) {
				
			}
			
			public void onGotLoginQRCodePicFailed() {
				
			}
			
			public void onExit() {
				// TODO 把数据保存进数据库
				PlayerManager.saveAllPlayerDataToDB();
			}
		});
	}
}

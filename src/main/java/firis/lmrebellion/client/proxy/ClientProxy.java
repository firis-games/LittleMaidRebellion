package firis.lmrebellion.client.proxy;

import firis.lmlib.api.LMLibraryAPI;
import firis.lmlib.api.caps.IGuiTextureSelect;
import firis.lmrebellion.common.proxy.IProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

/**
 * クライアントサイドProxy
 * @author firis-games
 *
 */
public class ClientProxy implements IProxy {

	/**
	 * クライアントプレイヤーを取得する
	 */
	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}
	
	/**
	 * テクスチャ設定用GUIを表示する
	 */
	@Override
	public void openGuiTextureSelect(IGuiTextureSelect selectEntity) {
		
		//テクスチャ選択画面表示
		LMLibraryAPI.instance().openGuiTextureSelect(null, 
				selectEntity, 
				"LittleMaidRebellion Texture Select");
		
	}
}

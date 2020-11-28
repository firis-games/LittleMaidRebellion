package firis.lmrebellion.common.proxy;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Proxy
 * @author firis-games
 *
 */
public interface IProxy {

	default public EntityPlayer getClientPlayer() {
		return null;
	}
	
	default public void openGuiTextureSelect() {
		
	}
}

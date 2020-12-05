package firis.lmrebellion.common.proxy;

import firis.lmlib.api.caps.IGuiTextureSelect;
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
	
	default public void openGuiTextureSelect(IGuiTextureSelect selectEntity) {
		
	}
}

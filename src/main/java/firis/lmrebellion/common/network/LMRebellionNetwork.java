package firis.lmrebellion.common.network;

import java.util.function.BiConsumer;

import firis.lmlib.api.LMLibraryAPI;
import firis.lmrebellion.LittleMaidRebellion;
import firis.lmrebellion.common.entity.EntityLittleMaidRebellion;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class LMRebellionNetwork {

	/** クライアントの情報をサーバーへ送信 */
	public static String SERVER_SYNC_TEXTURE = LittleMaidRebellion.MODID + ":SERVER_SYNC_TEXTURE";
		
	/**
	 * ネットワーク登録
	 */
	public static void init() {
		//クライアントからサーバーへの通信
    	LMLibraryAPI.instance().registerNetwork(SERVER_SYNC_TEXTURE, new BiConsumer<NBTTagCompound, MessageContext>() {
			@Override
			public void accept(NBTTagCompound nbt, MessageContext arg1) {
				//反逆メイドさんの見た目を同期
				int entityId = nbt.getInteger("id");
				EntityLittleMaidRebellion entity = (EntityLittleMaidRebellion) arg1.getServerHandler().player.getEntityWorld().getEntityByID(entityId);
				entity.setPacketLittleMaidTexture(nbt.getString("texture"), nbt.getInteger("color"));
			}
    	});
	}
	
}

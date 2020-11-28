package firis.lmrebellion.client.renderer;

import firis.lmlib.api.caps.IModelCompound;
import firis.lmlib.api.client.renderer.LMRenderMultiModel;
import firis.lmrebellion.client.renderer.layer.LayerHeldItemLittleMaidRebellion;
import firis.lmrebellion.common.entity.EntityLittleMaidRebellion;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * リトルメイドRenderer
 * 
 * メイドさんを描画するRenderer
 */
@SideOnly(Side.CLIENT)
public class RenderLittleMaidRebellion extends LMRenderMultiModel<EntityLittleMaidRebellion> {
	
	/**
	 * コンストラクタ
	 */
	public RenderLittleMaidRebellion(RenderManager manager) {
		
		super(manager, 0.3F);
		
		//描画用Layer登録
		this.addLayer(new LayerHeldItemLittleMaidRebellion(this));
		
	}
	
	/**
	 * メイドさんからModelConfigCompoundを取得する
	 */
	@Override
	protected IModelCompound getModelConfigCompoundFromEntity(EntityLittleMaidRebellion entity) {
		return entity.getModelCompound();
	}

	
}

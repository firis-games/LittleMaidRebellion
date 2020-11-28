package firis.lmrebellion.client.renderer.layer;

import firis.lmlib.api.client.renderer.LMRenderMultiModel;
import firis.lmlib.api.client.renderer.layer.LMLayerHeldItemBase;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;

/**
 * 反逆メイドの手持ちLayer
 * @author firis-games
 *
 */
public class LayerHeldItemLittleMaidRebellion extends LMLayerHeldItemBase {
	
	/**
	 * コンストラクタ
	 * @param rendererIn
	 */
	public LayerHeldItemLittleMaidRebellion(LMRenderMultiModel<? extends EntityLiving> rendererIn) {
		
		super(rendererIn, rendererIn.modelMain);
		
	}

	/**
	 * 右手のアイテムを取得する
	 */
	@Override
	protected ItemStack getRightHandItemStack(EntityLivingBase entitylivingbaseIn) {
		boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
		return flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();
	}

	/**
	 * 左手のアイテムを取得する
	 */
	@Override
	protected ItemStack getLeftHandItemStack(EntityLivingBase entitylivingbaseIn) {
		boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
		return flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
	}
	
	/**
	 * 手持ちアイテムの描画判定
	 */
	@Override
	protected boolean isRenderHeldItem(EntityLivingBase entitylivingbaseIn, ItemStack stackIn, ItemCameraTransforms.TransformType transformType) {
		return true;
    }
}
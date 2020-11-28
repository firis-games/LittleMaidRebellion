package firis.lmrebellion.common.modelcaps;

import firis.lmlib.api.LMLibraryAPI;
import firis.lmlib.api.caps.IModelCapsEntity;
import firis.lmlib.api.caps.ModelCompoundEntityBase;
import firis.lmlib.api.constant.EnumColor;
import firis.lmrebellion.common.entity.EntityLittleMaidRebellion;

/**
 * 反逆メイド用 ModelCompound
 * @author firis-games
 *
 */
public class ModelCompoundEntityLittleMaidRebellion extends ModelCompoundEntityBase<EntityLittleMaidRebellion> {

	/***
	 * コンストラクタ
	 * @param entity
	 * @param caps
	 */
	public ModelCompoundEntityLittleMaidRebellion(EntityLittleMaidRebellion entity, IModelCapsEntity caps) {
		super(entity, caps);
		
		this.color = EnumColor.WHITE.getColor();
		this.contract = true;
		
	}
	
	/**
	 * Textureと色情報を設定する
	 */
	public void setLmmmTextureWithColor(String texture, int color) {
		this.setTextureBoxLittleMaid(LMLibraryAPI.instance().getTextureManager().getLMTextureBox(texture));
		this.setColor(color);
	}

}

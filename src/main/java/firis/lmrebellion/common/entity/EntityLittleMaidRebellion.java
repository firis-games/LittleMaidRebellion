package firis.lmrebellion.common.entity;

import javax.annotation.Nullable;

import firis.lmlib.api.LMLibraryAPI;
import firis.lmlib.api.caps.IModelCompound;
import firis.lmlib.api.constant.EnumColor;
import firis.lmlib.api.constant.EnumSound;
import firis.lmlib.api.manager.LMTextureBoxManager;
import firis.lmlib.api.resource.LMTextureBox;
import firis.lmrebellion.common.modelcaps.ModelCapsEntityLittleMaidRebellion;
import firis.lmrebellion.common.modelcaps.ModelCompoundEntityLittleMaidRebellion;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

/***
 * 反乱メイド
 * @author firis-games
 *
 */
public class EntityLittleMaidRebellion extends EntityMob {
	
	protected final ModelCompoundEntityLittleMaidRebellion modelCompound;
	
	
	private static final DataParameter<String> LMMM_TEXTURE = EntityDataManager.<String>createKey(EntityLittleMaidRebellion.class, DataSerializers.STRING);
	
    private static final DataParameter<Integer> LMMM_COLOR = EntityDataManager.<Integer>createKey(EntityLittleMaidRebellion.class, DataSerializers.VARINT);
    

	/**
	 * コンストラクタ
	 * @param worldIn
	 */
	public EntityLittleMaidRebellion(World worldIn) {
		super(worldIn);
		this.setSize(0.5F, 1.35F);
		
		//モデル情報初期化
		this.modelCompound = new ModelCompoundEntityLittleMaidRebellion(this, new ModelCapsEntityLittleMaidRebellion(this));
	}
	
	/**
	 * ゾンビAIと同等AI
	 */
	@Override
	protected void initEntityAI() {
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
	}
	
	/**
	 * ステータス設定
	 */
	@Override
	protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
    }
	
	
	@Override
	protected void entityInit() {
		super.entityInit();
		this.getDataManager().register(LMMM_TEXTURE, LMTextureBoxManager.defaultTextureModelName);
		this.getDataManager().register(LMMM_COLOR, EnumColor.BROWN.getColor());
	}
	
	/**
	 * Lmmmテクスチャ名取得
	 * @return
	 */
	protected String getLmmmTexture() {
		return this.getDataManager().get(LMMM_TEXTURE);
	}
	
	/**
	 * Lmmmテクスチャ名設定
	 * @param texture
	 */
	protected void setLmmmTexture(String texture) {
		this.getDataManager().set(LMMM_TEXTURE, texture);
	}
	
	/**
	 * Lmmm色情報取得
	 * @return
	 */
	protected int getLmmmColor() {
		return this.getDataManager().get(LMMM_COLOR);
	}
	
	/***
	 * Lmmm色情報設定
	 * @param color
	 */
	protected void setLmmmColor(int color) {
		this.getDataManager().set(LMMM_COLOR, color);
	}
	
	/**
	 * 通常音声
	 */
	@Override
	protected SoundEvent getAmbientSound() {
		return LMLibraryAPI.instance().getSoundEvent(EnumSound.LIVING_DAYTIME, "", 0, true);
	}
	
	/**
	 * ダメージ音声
	 */
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return LMLibraryAPI.instance().getSoundEvent(EnumSound.HURT, "", 0, false);
	}
	
	/**
	 * デッド音声
	 */
	@Override
	protected SoundEvent getDeathSound() {
		return LMLibraryAPI.instance().getSoundEvent(EnumSound.DEATH, "", 0, false);
	}
	
	/**
	 * モンスター属性
	 */
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return super.getCreatureAttribute();
	}
	
	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		return null;
	}
	
	@Override
    @Nullable
    protected Item getDropItem() {
        return Items.CAKE;
    }
	
    /**
     * Drop the equipment for this entity.
     */
	@Override
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
    {
		if (this.world.rand.nextInt(100) < 50) return;
		ItemStack stackMain = this.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
		this.entityDropItem(stackMain, 0.0F);
    }
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		
		compound.setString("LmmmTexture", this.getLmmmTexture());
		compound.setInteger("LmmmColor", this.getLmmmColor());
		
	}
	
	/**
	 * 
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		
		this.setLmmmTexture(compound.getString("LmmmTexture"));
		this.setLmmmColor(compound.getInteger("LmmmColor"));
	}
	
	/**
	 * 反逆メイドがモブを倒したとき
	 */
	@Override
	public void onKillEntity(EntityLivingBase entityLivingIn) {
		
	}
	
	/*
	/**
	 * 明るさに関係なくスポーンさせる
	 */
	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}
	
	@Override
	public double getYOffset() {
		return -0.35D;
	}
	
	/**
	 * 見た目と初期装備を設定
	 */
	@Override
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		IEntityLivingData entitydata = super.onInitialSpawn(difficulty, livingdata);
		
		//テクスチャのランダム指定
		LMTextureBox textureBox = LMLibraryAPI.instance().getTextureManager().getRandomTexture(this.world.rand);
		this.setLmmmTexture(textureBox.getTextureModelName());
		this.setLmmmColor(textureBox.getRandomColor(this.world.rand).getColor());
		
		if (world.getDifficulty() == EnumDifficulty.EASY) {
			if (2 <= world.rand.nextInt(10)) {
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_AXE));
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.WOODEN_AXE));
			} else {
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_AXE));
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.STONE_AXE));
			}
		} else if (world.getDifficulty() == EnumDifficulty.NORMAL) {
			if (2 <= world.rand.nextInt(10)) {
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.IRON_AXE));
			} else if (9 <= world.rand.nextInt(10)) {
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_AXE));
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.DIAMOND_AXE));
			} else {
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.IRON_AXE));
			}
			
			//鉄防具
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
			
		} else if (world.getDifficulty() == EnumDifficulty.HARD) {
			if (5 <= world.rand.nextInt(10)) {
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.DIAMOND_AXE));
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.DIAMOND_AXE));
			} else if (9 <= world.rand.nextInt(10)) {
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.IRON_AXE));
			} else if (5 <= world.rand.nextInt(10)) {
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_AXE));
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.DIAMOND_AXE));
			} else {
				ItemStack stack = new ItemStack(Items.DIAMOND_AXE);
				stack.addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), 10);
				this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, stack.copy());
				this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, stack.copy());
			}
			
			//ダイヤ防具
			this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.DIAMOND_HELMET));
			this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.DIAMOND_CHESTPLATE));
			this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.DIAMOND_LEGGINGS));
			this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.DIAMOND_BOOTS));
		}
		
		return entitydata;
	}
	
	/**
	 * モデル情報取得
	 * @return
	 */
	public IModelCompound getModelCompound() {
		this.modelCompound.setLmmmTextureWithColor(this.getLmmmTexture(), this.getLmmmColor());
		return this.modelCompound;
	}
}

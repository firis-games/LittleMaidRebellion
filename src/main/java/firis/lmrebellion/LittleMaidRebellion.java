package firis.lmrebellion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import firis.lmrebellion.client.renderer.RenderLittleMaidRebellion;
import firis.lmrebellion.common.entity.EntityLittleMaidRebellion;
import firis.lmrebellion.common.item.LMItemMaidBook;
import firis.lmrebellion.common.network.LMRebellionNetwork;
import firis.lmrebellion.common.proxy.IProxy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
		modid = LittleMaidRebellion.MODID, 
		name = LittleMaidRebellion.NAME,
		version = LittleMaidRebellion.VERSION,
		dependencies = LittleMaidRebellion.MOD_DEPENDENCIES,
		acceptedMinecraftVersions = LittleMaidRebellion.MOD_ACCEPTED_MINECRAFT_VERSIONS
)
@EventBusSubscriber(modid=LittleMaidRebellion.MODID)
public class LittleMaidRebellion {

    public static final String MODID = "lmrebellion";
    public static final String NAME = "LittleMaidRebellion";
    public static final String VERSION = "0.1.0";
    public static final String MOD_DEPENDENCIES = "required-after:forge@[1.12.2-14.23.5.2768,);"
    		+ "required-after:lmlibrary@[1.1.2,);";
    public static final String MOD_ACCEPTED_MINECRAFT_VERSIONS = "[1.12.2]";
    
    @Instance(MODID)
	public static LittleMaidRebellion instance;
    
    /** logger */
    public static Logger logger = LogManager.getLogger(MODID);
    
    /** proxy */
    @SidedProxy(serverSide = "firis.lmrebellion.common.proxy.CommonProxy", 
    		clientSide = "firis.lmrebellion.client.proxy.ClientProxy")
	public static IProxy proxy;
	
	/**
     * アイテムインスタンス保持用
     */
    @ObjectHolder(MODID)
    public static class LMRItems {
    	public final static Item LMR_MAID_BOOK = null;
    }
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {}
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	LMRebellionNetwork.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {}
    
    
    @SubscribeEvent
    protected static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
    	
    	int entityId = 0;
    	
    	//Little Maid Rebellion
    	EntityEntryBuilder<EntityLittleMaidRebellion> littlemaidEntry = EntityEntryBuilder.create();
    	littlemaidEntry.entity(EntityLittleMaidRebellion.class)
    			.id(new ResourceLocation(MODID, "littlemaid_rebellion"), entityId++)
    			.name("littlemaid_rebellion")
    			.egg(0x7e7e7e, 0xcc6600)
    			.tracker(80, 1, true);
    	littlemaidEntry.spawn(EnumCreatureType.MONSTER, 50, 1, 1, Biome.REGISTRY);
    	event.getRegistry().register(littlemaidEntry.build());
    	    	
    }
    
    
    /**
     * アイテム登録
     * @param event
     */
    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
    	// メイドさんのおめかし本
    	event.getRegistry().register(new LMItemMaidBook()
    			.setRegistryName(LittleMaidRebellion.MODID, "lmr_maid_book")
    			.setUnlocalizedName("lmr_maid_book"));
    }
    
    /**
     * モデル登録
     * @param event
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    protected static void registerModels(ModelRegistryEvent event) {
    	//Entityの描画設定
		RenderingRegistry.registerEntityRenderingHandler(EntityLittleMaidRebellion.class, new IRenderFactory<EntityLittleMaidRebellion>() {
			@Override
			public Render<? super EntityLittleMaidRebellion> createRenderFor(RenderManager manager) {
				return new RenderLittleMaidRebellion(manager);
			}
		});
		
    	// メイドさんのおめかし本
		ModelLoader.setCustomModelResourceLocation(LMRItems.LMR_MAID_BOOK, 0,
				new ModelResourceLocation(LMRItems.LMR_MAID_BOOK.getRegistryName(), "inventory"));
		
    }
	
}

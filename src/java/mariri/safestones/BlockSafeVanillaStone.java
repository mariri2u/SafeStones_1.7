package mariri.safestones;


import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockStone;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSafeVanillaStone extends BlockStone implements IBlockSafeStone{
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] icons;

	protected String unlocalizedName;

	
//	protected float[] hardnessTable = new float[]{
//			2.0F, 1.5F, 1.5F, 2.0F
//	};
	
	protected BlockSafeVanillaStone(){
		super();
		setCreativeTab(CreativeTabs.tabBlock);
		this.setStepSound(soundTypePiston);
		this.setHardness(2.0F);
		this.setResistance(2000.0F);
    	this.icons = new IIcon[5];
    	this.unlocalizedName = "safeVanillaStone";

		setBlockName(unlocalizedName);
        GameRegistry.registerBlock(this, ItemBlockSafeStone.class, unlocalizedName);
   
        registerRecipe();
	}
    
	private void registerRecipe(){
		
		// cobblestone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 0),
        		"XXX", "XYX", "XXX",
        		'X', "cobblestone",
        		'Y', new ItemStack(Items.gold_nugget)
        	));

        // stone
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 1),
        		"XXX", "XYX", "XXX",
        		'X', "stone",
        		'Y', new ItemStack(Items.gold_nugget)
        	));
        
        GameRegistry.addSmelting(new ItemStack(this, 1, 0), new ItemStack(this, 1, 1), 0);
        
        // stonebrick
        GameRegistry.addRecipe( new ShapedOreRecipe( new ItemStack(this, 1, 2),
        		"XXX", "XYX", "XXX",
        		'X', "stonebrick",
        		'Y', new ItemStack(Items.gold_nugget)
        	));

        GameRegistry.addRecipe( new ItemStack(this, 1, 2),
        	new Object[] { "XX", "XX",
        		'X', new ItemStack(this, 1, 1)
        	});

        // brick
        GameRegistry.addRecipe( new ItemStack(this, 1, 3),
        	new Object[] { "XXX", "XYX", "XXX",
        		'X', new ItemStack(Blocks.brick_block),
        		'Y', new ItemStack(Items.gold_nugget)
        	});
        
        // nether brick
        GameRegistry.addRecipe( new ItemStack(this, 1, 4),
            new Object[] { "XXX", "XYX", "XXX",
            	'X', new ItemStack(Blocks.nether_brick),
            	'Y', new ItemStack(Items.gold_nugget)
            });
    }
	
	@Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_){
        return Item.getItemFromBlock(this);
    }
	
    @Override
    public int damageDropped(int meta){
    	if(meta == 1){
    		return 0;
    	}else{
    		return meta;
    	}
    }
    
//    @Override
//    public float getBlockHardness(World world, int x, int y, int z){
//    	int meta = world.getBlockMetadata(x, y, z);
//    	float hardness = hardnessTable[0];
//    	if(meta < hardnessTable.length){
//    		hardness = hardnessTable[meta];
//    	}
//    	return hardness;
//    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        this.icons[0] = iconRegister.registerIcon("minecraft:cobblestone");
        this.icons[1] = iconRegister.registerIcon("minecraft:stone");
        this.icons[2] = iconRegister.registerIcon("minecraft:stonebrick");
        this.icons[3] = iconRegister.registerIcon("minecraft:brick");
        this.icons[4] = iconRegister.registerIcon("minecraft:nether_brick");
    }
    
    public String getUnlocalizedName(int meta){
    	return "tile."+ unlocalizedName + "." + meta;
    }
    
	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z){
    	return false;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta){
    	if(meta < icons.length){
    		return icons[meta];
    	}else{
    		return icons[0];
    	}
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list){
    	for(int i = 0; i < icons.length; i++){
    		list.add(new ItemStack(item, 1, i));
    	}
    }
}
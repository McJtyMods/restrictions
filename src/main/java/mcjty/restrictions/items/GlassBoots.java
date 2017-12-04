package mcjty.restrictions.items;

import mcjty.restrictions.Restrictions;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class GlassBoots extends ItemArmor {

    public GlassBoots() {
        super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.FEET);
        setRegistryName("glassboots");
        setUnlocalizedName(Restrictions.MODID + ".glassboots");
        setCreativeTab(Restrictions.creativeTab);
    }

    @Override
    public void addInformation(ItemStack itemStack, World player, List<String> list, ITooltipFlag advancedToolTip) {
        super.addInformation(itemStack, player, list, advancedToolTip);
        list.add("If you were these boots the Attractor,");
        list.add("Pusher and One Way will have no effect");
        list.add("on you");
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return Restrictions.MODID+":textures/items/textureboots.png";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        return GlassBootsModel.getModel(entityLiving, itemStack);
    }


}

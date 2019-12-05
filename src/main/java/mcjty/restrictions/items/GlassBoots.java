package mcjty.restrictions.items;

import mcjty.restrictions.Restrictions;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GlassBoots extends ArmorItem {

    public GlassBoots() {
        super(ArmorMaterial.LEATHER, EquipmentSlotType.FEET, new Properties().group(Restrictions.setup.getTab()));
    }

    @Override
    public void addInformation(ItemStack itemStack, World world, List<ITextComponent> list, ITooltipFlag advancedToolTip) {
        super.addInformation(itemStack, world, list, advancedToolTip);
        list.add(new StringTextComponent("If you were these boots the Attractor,"));
        list.add(new StringTextComponent("Pusher and One Way will have no effect"));
        list.add(new StringTextComponent("on you"));
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return Restrictions.MODID+":textures/item/textureboots.png";
    }

    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A def) {
        return GlassBootsModel.getModel(entityLiving, itemStack);
    }


}

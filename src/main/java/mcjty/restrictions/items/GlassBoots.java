package mcjty.restrictions.items;

import mcjty.lib.builder.TooltipBuilder;
import mcjty.lib.tooltips.ITooltipSettings;
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
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

import static mcjty.lib.builder.TooltipBuilder.*;

import net.minecraft.item.Item.Properties;

public class GlassBoots extends ArmorItem implements ITooltipSettings {

    private final TooltipBuilder tooltipBuilder = new TooltipBuilder()
            .info(key("message.restrictions.shiftmessage"))
            .infoShift(header(), gold());

    public GlassBoots() {
        super(ArmorMaterial.LEATHER, EquipmentSlotType.FEET, new Properties().tab(Restrictions.setup.getTab()));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, World world, List<ITextComponent> list, ITooltipFlag flags) {
        super.appendHoverText(itemStack, world, list, flags);
        tooltipBuilder.makeTooltip(getRegistryName(), itemStack, list, flags);
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

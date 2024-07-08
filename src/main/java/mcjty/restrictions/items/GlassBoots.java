package mcjty.restrictions.items;

import mcjty.lib.builder.TooltipBuilder;
import mcjty.lib.items.GenericArmorItem;
import mcjty.lib.tooltips.ITooltipSettings;
import mcjty.restrictions.Restrictions;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

import static mcjty.lib.builder.TooltipBuilder.*;

public class GlassBoots extends GenericArmorItem implements ITooltipSettings {

    private final TooltipBuilder tooltipBuilder = new TooltipBuilder()
            .info(key("message.restrictions.shiftmessage"))
            .infoShift(header(), gold());

    public GlassBoots() {
        super(ArmorMaterials.LEATHER, EquipmentSlot.FEET, Restrictions.setup.defaultProperties());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext pContext, List<Component> list, TooltipFlag flags) {
        super.appendHoverText(itemStack, pContext, list, flags);
        tooltipBuilder.makeTooltip(BuiltInRegistries.ITEM.getKey(this), itemStack, list, flags);
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return ResourceLocation.fromNamespaceAndPath(Restrictions.MODID, "textures/item/textureboots.png");
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            @Nonnull
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                return GlassBootsModel.getModel(livingEntity, itemStack);
            }
        });
    }
}

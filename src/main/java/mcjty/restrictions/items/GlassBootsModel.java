package mcjty.restrictions.items;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

public class GlassBootsModel extends BipedModel<LivingEntity> {

    private static GlassBootsModel modelBoots;

    private RendererModel bootsLeft;
    private RendererModel bootsRight;


    // Boots
    private RendererModel bootsleftfootbase;
    private RendererModel bootsrightfootbase;
    private RendererModel bootsleftback;
    private RendererModel bootsrightback;
    private RendererModel bootsleftfront;
    private RendererModel bootsrightfront;
    private RendererModel bootsrightside2;
    private RendererModel bootsleftside2;
    private RendererModel bootsleftside1;
    private RendererModel bootsrightside1;
    private RendererModel bootslefttip;
    private RendererModel bootsrighttip;

    public GlassBootsModel() {
        textureWidth = 64;
        textureHeight = 32;

        setupBoots();

        bootsLeft = new RendererModel(this, 0, 0);
        bootsRight = new RendererModel(this, 0, 0);

        bootsLeft.addChild(bootsleftback);
        bootsLeft.addChild(bootsleftfootbase);
        bootsLeft.addChild(bootsleftfront);
        bootsLeft.addChild(bootsleftside1);
        bootsLeft.addChild(bootsleftside2);
        bootsLeft.addChild(bootslefttip);

        bootsRight.addChild(bootsrightback);
        bootsRight.addChild(bootsrightfootbase);
        bootsRight.addChild(bootsrightfront);
        bootsRight.addChild(bootsrightside1);
        bootsRight.addChild(bootsrightside2);
        bootsRight.addChild(bootsrighttip);

        bipedLeftLeg.addChild(bootsLeft);
        bipedRightLeg.addChild(bootsRight);
    }

    private void setupBoots() {
        float offY = 0;

        bootsleftfootbase = new RendererModel(this, 12, 0);
        bootsleftfootbase.addBox(0F, offY, 0F, 4, 0, 4);
        bootsleftfootbase.setRotationPoint(0F, 24F, -2F);
        bootsleftfootbase.setTextureSize(64, 32);
        bootsleftfootbase.mirror = true;
        setRotation(bootsleftfootbase, 0F, 0F, 0F);

        bootsrightfootbase = new RendererModel(this, 12, 0);
        bootsrightfootbase.addBox(0F, offY, 0F, 4, 0, 4);
        bootsrightfootbase.setRotationPoint(-4F, 24F, -2F);
        bootsrightfootbase.setTextureSize(64, 32);
        bootsrightfootbase.mirror = true;
        setRotation(bootsrightfootbase, 0F, 0F, 0F);

        bootsleftback = new RendererModel(this, 0, 8);
        bootsleftback.addBox(0F, offY, 0F, 4, 4, 2);
        bootsleftback.setRotationPoint(0F, 20F, 2F);
        bootsleftback.setTextureSize(64, 32);
        bootsleftback.mirror = true;
        setRotation(bootsleftback, 0F, 0F, 0F);

        bootsrightback = new RendererModel(this, 0, 8);
        bootsrightback.addBox(0F, offY, 0F, 4, 4, 2);
        bootsrightback.setRotationPoint(-4F, 20F, 2F);
        bootsrightback.setTextureSize(64, 32);
        bootsrightback.mirror = true;
        setRotation(bootsrightback, 0F, 0F, 0F);

        bootsleftfront = new RendererModel(this, 0, 8);
        bootsleftfront.addBox(0F, offY, 0F, 4, 4, 2);
        bootsleftfront.setRotationPoint(0F, 20F, -4F);
        bootsleftfront.setTextureSize(64, 32);
        bootsleftfront.mirror = true;
        setRotation(bootsleftfront, 0F, 0F, 0F);

        bootsrightfront = new RendererModel(this, 0, 8);
        bootsrightfront.addBox(0F, offY, 0F, 4, 4, 2);
        bootsrightfront.setRotationPoint(-4F, 20F, -4F);
        bootsrightfront.setTextureSize(64, 32);
        bootsrightfront.mirror = true;
        setRotation(bootsrightfront, 0F, 0F, 0F);

        bootsrightside2 = new RendererModel(this, 0, 0);
        bootsrightside2.addBox(0F, offY, 0F, 2, 4, 4);
        bootsrightside2.setRotationPoint(-2F, 20F, -2F);
        bootsrightside2.setTextureSize(64, 32);
        bootsrightside2.mirror = true;
        setRotation(bootsrightside2, 0F, 0F, 0F);

        bootsleftside2 = new RendererModel(this, 0, 0);
        bootsleftside2.addBox(0F, offY, 0F, 2, 4, 4);
        bootsleftside2.setRotationPoint(0F, 20F, -2F);
        bootsleftside2.setTextureSize(64, 32);
        bootsleftside2.mirror = true;
        setRotation(bootsleftside2, 0F, 0F, 0F);

        bootsleftside1 = new RendererModel(this, 0, 0);
        bootsleftside1.addBox(0F, offY, 0F, 2, 4, 4);
        bootsleftside1.setRotationPoint(4F, 20F, -2F);
        bootsleftside1.setTextureSize(64, 32);
        bootsleftside1.mirror = true;
        setRotation(bootsleftside1, 0F, 0F, 0F);

        bootsrightside1 = new RendererModel(this, 0, 0);
        bootsrightside1.addBox(0F, offY, 0F, 2, 4, 4);
        bootsrightside1.setRotationPoint(-6F, 20F, -2F);
        bootsrightside1.setTextureSize(64, 32);
        bootsrightside1.mirror = true;
        setRotation(bootsrightside1, 0F, 0F, 0F);

        bootslefttip = new RendererModel(this, 12, 4);
        bootslefttip.addBox(0F, offY, 0F, 2, 3, 1);
        bootslefttip.setRotationPoint(1F, 21F, -5F);
        bootslefttip.setTextureSize(64, 32);
        bootslefttip.mirror = true;
        setRotation(bootslefttip, 0F, 0F, 0F);

        bootsrighttip = new RendererModel(this, 12, 4);
        bootsrighttip.addBox(0F, offY, 0F, 2, 3, 1);
        bootsrighttip.setRotationPoint(-3F, 21F, -5F);
        bootsrighttip.setTextureSize(64, 32);
        bootsrighttip.mirror = true;
        setRotation(bootsrighttip, 0F, 0F, 0F);
    }

    private void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public static <A extends BipedModel<?>> A getModel(LivingEntity entity, ItemStack stack) {

        if (stack.isEmpty() || !(stack.getItem() instanceof ArmorItem)) {
            return null;
        }
        EquipmentSlotType slot = ((ArmorItem) stack.getItem()).getEquipmentSlot();

        if (slot == EquipmentSlotType.FEET && modelBoots != null) {
            //noinspection unchecked
            return (A) modelBoots;
        }

        GlassBootsModel armor = new GlassBootsModel();
        armor.field_78115_e.isHidden = true;    // @todo 1.14 bipedBody
        armor.bipedLeftArm.isHidden = true;
        armor.bipedRightArm.isHidden = true;

        armor.field_78116_c.isHidden = true;    // @todo 1.14 bipedHead

        armor.bipedLeftLeg.isHidden = true;
        armor.bipedRightLeg.isHidden = true;

        armor.bootsRight.isHidden = true;
        armor.bootsLeft.isHidden = true;

        if (slot == EquipmentSlotType.FEET) {
            armor.bootsLeft.isHidden = false;
            armor.bootsRight.isHidden = false;
            modelBoots = armor;
        }
        //noinspection unchecked
        return (A) armor;
    }

    @Override
    public void render(@Nonnull LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.isSneak = entity.isSneaking();
        this.isSitting = entity.isPassenger();

        this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        if (this.isChild) {
            float f6 = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
            GlStateManager.translatef(0.0F, 16.0F * scale, 0.0F);
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.enableBlend();
            this.field_78116_c.render(scale);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
            this.field_78115_e.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
            GlStateManager.popMatrix();
        } else {
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GlStateManager.enableBlend();
            this.field_78116_c.render(scale);
            GlStateManager.disableBlend();
            this.field_78115_e.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
            bootsLeft.rotateAngleX = bipedLeftLeg.rotateAngleX / 2;
            bootsLeft.rotationPointY = -Math.abs((float) Math.sin(bipedLeftLeg.rotateAngleX) * 4);
            bootsLeft.render(scale);
            bootsRight.rotateAngleX = bipedRightLeg.rotateAngleX / 2;
            bootsRight.rotationPointY = -Math.abs((float) Math.sin(bipedRightLeg.rotateAngleX) * 4);
            bootsRight.render(scale);

        }
    }

}

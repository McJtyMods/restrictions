package mcjty.restrictions.items;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class GlassBootsModel extends BipedModel<LivingEntity> {

    private static GlassBootsModel modelBoots;

    private ModelRenderer bootsLeft;
    private ModelRenderer bootsRight;


    // Boots
    private ModelRenderer bootsleftfootbase;
    private ModelRenderer bootsrightfootbase;
    private ModelRenderer bootsleftback;
    private ModelRenderer bootsrightback;
    private ModelRenderer bootsleftfront;
    private ModelRenderer bootsrightfront;
    private ModelRenderer bootsrightside2;
    private ModelRenderer bootsleftside2;
    private ModelRenderer bootsleftside1;
    private ModelRenderer bootsrightside1;
    private ModelRenderer bootslefttip;
    private ModelRenderer bootsrighttip;

    public GlassBootsModel() {
        super(0.0f);    // @todo 1.15 check constructor!
        texWidth = 64;
        texHeight = 32;

        setupBoots();

        bootsLeft = new ModelRenderer(this, 0, 0);
        bootsRight = new ModelRenderer(this, 0, 0);

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

        leftLeg.addChild(bootsLeft);
        rightLeg.addChild(bootsRight);
    }

    private void setupBoots() {
        float offY = 0;

        bootsleftfootbase = new ModelRenderer(this, 12, 0);
        bootsleftfootbase.addBox(0F, offY, 0F, 4, 0, 4);
        bootsleftfootbase.setPos(0F, 24F, -2F);
        bootsleftfootbase.setTexSize(64, 32);
        bootsleftfootbase.mirror = true;
        setRotation(bootsleftfootbase, 0F, 0F, 0F);

        bootsrightfootbase = new ModelRenderer(this, 12, 0);
        bootsrightfootbase.addBox(0F, offY, 0F, 4, 0, 4);
        bootsrightfootbase.setPos(-4F, 24F, -2F);
        bootsrightfootbase.setTexSize(64, 32);
        bootsrightfootbase.mirror = true;
        setRotation(bootsrightfootbase, 0F, 0F, 0F);

        bootsleftback = new ModelRenderer(this, 0, 8);
        bootsleftback.addBox(0F, offY, 0F, 4, 4, 2);
        bootsleftback.setPos(0F, 20F, 2F);
        bootsleftback.setTexSize(64, 32);
        bootsleftback.mirror = true;
        setRotation(bootsleftback, 0F, 0F, 0F);

        bootsrightback = new ModelRenderer(this, 0, 8);
        bootsrightback.addBox(0F, offY, 0F, 4, 4, 2);
        bootsrightback.setPos(-4F, 20F, 2F);
        bootsrightback.setTexSize(64, 32);
        bootsrightback.mirror = true;
        setRotation(bootsrightback, 0F, 0F, 0F);

        bootsleftfront = new ModelRenderer(this, 0, 8);
        bootsleftfront.addBox(0F, offY, 0F, 4, 4, 2);
        bootsleftfront.setPos(0F, 20F, -4F);
        bootsleftfront.setTexSize(64, 32);
        bootsleftfront.mirror = true;
        setRotation(bootsleftfront, 0F, 0F, 0F);

        bootsrightfront = new ModelRenderer(this, 0, 8);
        bootsrightfront.addBox(0F, offY, 0F, 4, 4, 2);
        bootsrightfront.setPos(-4F, 20F, -4F);
        bootsrightfront.setTexSize(64, 32);
        bootsrightfront.mirror = true;
        setRotation(bootsrightfront, 0F, 0F, 0F);

        bootsrightside2 = new ModelRenderer(this, 0, 0);
        bootsrightside2.addBox(0F, offY, 0F, 2, 4, 4);
        bootsrightside2.setPos(-2F, 20F, -2F);
        bootsrightside2.setTexSize(64, 32);
        bootsrightside2.mirror = true;
        setRotation(bootsrightside2, 0F, 0F, 0F);

        bootsleftside2 = new ModelRenderer(this, 0, 0);
        bootsleftside2.addBox(0F, offY, 0F, 2, 4, 4);
        bootsleftside2.setPos(0F, 20F, -2F);
        bootsleftside2.setTexSize(64, 32);
        bootsleftside2.mirror = true;
        setRotation(bootsleftside2, 0F, 0F, 0F);

        bootsleftside1 = new ModelRenderer(this, 0, 0);
        bootsleftside1.addBox(0F, offY, 0F, 2, 4, 4);
        bootsleftside1.setPos(4F, 20F, -2F);
        bootsleftside1.setTexSize(64, 32);
        bootsleftside1.mirror = true;
        setRotation(bootsleftside1, 0F, 0F, 0F);

        bootsrightside1 = new ModelRenderer(this, 0, 0);
        bootsrightside1.addBox(0F, offY, 0F, 2, 4, 4);
        bootsrightside1.setPos(-6F, 20F, -2F);
        bootsrightside1.setTexSize(64, 32);
        bootsrightside1.mirror = true;
        setRotation(bootsrightside1, 0F, 0F, 0F);

        bootslefttip = new ModelRenderer(this, 12, 4);
        bootslefttip.addBox(0F, offY, 0F, 2, 3, 1);
        bootslefttip.setPos(1F, 21F, -5F);
        bootslefttip.setTexSize(64, 32);
        bootslefttip.mirror = true;
        setRotation(bootslefttip, 0F, 0F, 0F);

        bootsrighttip = new ModelRenderer(this, 12, 4);
        bootsrighttip.addBox(0F, offY, 0F, 2, 3, 1);
        bootsrighttip.setPos(-3F, 21F, -5F);
        bootsrighttip.setTexSize(64, 32);
        bootsrighttip.mirror = true;
        setRotation(bootsrighttip, 0F, 0F, 0F);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    public static <A extends BipedModel<?>> A getModel(LivingEntity entity, ItemStack stack) {

        if (stack.isEmpty() || !(stack.getItem() instanceof ArmorItem)) {
            return null;
        }
        EquipmentSlotType slot = ((ArmorItem) stack.getItem()).getSlot();

        if (slot == EquipmentSlotType.FEET && modelBoots != null) {
            //noinspection unchecked
            return (A) modelBoots;
        }

        GlassBootsModel armor = new GlassBootsModel();
        armor.body.visible = false;
        armor.leftArm.visible = false;
        armor.rightArm.visible = false;

        armor.head.visible = false;

        armor.leftLeg.visible = false;
        armor.rightLeg.visible = false;

        armor.bootsRight.visible = false;
        armor.bootsLeft.visible = false;

        if (slot == EquipmentSlotType.FEET) {
            armor.bootsLeft.visible = true;
            armor.bootsRight.visible = true;
            modelBoots = armor;
        }
        //noinspection unchecked
        return (A) armor;
    }


    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder builder, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        super.renderToBuffer(matrixStack, builder, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
    }

    @Override
    public void setupAnim(LivingEntity entity, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
        this.crouching = entity.isShiftKeyDown();
        this.riding = entity.isPassenger();

        super.setupAnim(entity, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
//        this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        if (this.young) {
            float f6 = 2.0F;
            // @todo 1.15
//            GlStateManager.pushMatrix();
//            GlStateManager.scalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
//            GlStateManager.translatef(0.0F, 16.0F * scale, 0.0F);
//            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//            GlStateManager.enableBlend();
//            this.bipedHead.render(scale);
//            GlStateManager.disableBlend();
//            GlStateManager.popMatrix();
//            GlStateManager.pushMatrix();
//            GlStateManager.scalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
//            GlStateManager.translatef(0.0F, 24.0F * scale, 0.0F);
//            this.bipedBody.render(scale);
//            this.bipedRightArm.render(scale);
//            this.bipedLeftArm.render(scale);
//            this.bipedRightLeg.render(scale);
//            this.bipedLeftLeg.render(scale);
//            GlStateManager.popMatrix();
        } else {
            // @todo 1.15
//            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//            GlStateManager.enableBlend();
//            this.bipedHead.render(scale);
//            GlStateManager.disableBlend();
//            this.bipedBody.render(scale);
//            this.bipedRightArm.render(scale);
//            this.bipedLeftArm.render(scale);
//            this.bipedRightLeg.render(scale);
//            this.bipedLeftLeg.render(scale);
//            bootsLeft.rotateAngleX = bipedLeftLeg.rotateAngleX / 2;
//            bootsLeft.rotationPointY = -Math.abs((float) Math.sin(bipedLeftLeg.rotateAngleX) * 4);
//            bootsLeft.render(scale);
//            bootsRight.rotateAngleX = bipedRightLeg.rotateAngleX / 2;
//            bootsRight.rotationPointY = -Math.abs((float) Math.sin(bipedRightLeg.rotateAngleX) * 4);
//            bootsRight.render(scale);

        }
    }

}

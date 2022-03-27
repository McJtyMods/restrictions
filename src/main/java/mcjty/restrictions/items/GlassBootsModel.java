package mcjty.restrictions.items;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import mcjty.restrictions.Restrictions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;

public class GlassBootsModel extends HumanoidModel<LivingEntity> {

    public static final ModelLayerLocation GLASS_BOOTS = new ModelLayerLocation(new ResourceLocation(Restrictions.MODID, "main"), "glass_boots");
    private static GlassBootsModel modelBoots;

    private final ModelPart bootsLeft;
    private final ModelPart bootsRight;


    public GlassBootsModel(ModelPart part) {
        super(part);    // @todo 1.15 check constructor!

        bootsLeft = part.getChild("left_leg").getChild("boots_left");
        bootsRight = part.getChild("right_leg").getChild("boots_right");
    }

    public static LayerDefinition createBootsLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();

        float offY = 0;
        PartDefinition leftLeg = partdefinition.getChild("left_leg");
        PartDefinition bootsLeft = leftLeg.addOrReplaceChild("boots_left", CubeListBuilder.create(), PartPose.offset(-2, -12, 0));
        bootsLeft.addOrReplaceChild("foot_base", CubeListBuilder.create()
                        .texOffs(12, 0).addBox(0F, offY, 0F, 4, 0, 4).mirror(),
                PartPose.offset(0, 24F, -2F));
        bootsLeft.addOrReplaceChild("back", CubeListBuilder.create()
                        .texOffs(0, 8).addBox(0F, offY, 0F, 4, 4, 2).mirror(),
                PartPose.offset(0F, 20F, 2F));
        bootsLeft.addOrReplaceChild("front", CubeListBuilder.create()
                        .texOffs(0, 8).addBox(0F, offY, 0F, 4, 4, 2).mirror(),
                PartPose.offset(0F, 20F, -4F));
        bootsLeft.addOrReplaceChild("side2", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(0F, offY, 0F, 2, 4, 4).mirror(),
                PartPose.offset(0F, 20F, -2F));
        bootsLeft.addOrReplaceChild("side1", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(0F, offY, 0F, 2, 4, 4).mirror(),
                PartPose.offset(4F, 20F, -2F));
        bootsLeft.addOrReplaceChild("tip", CubeListBuilder.create()
                        .texOffs(12, 4).addBox(0F, offY, 0F, 2, 3, 1).mirror(),
                PartPose.offset(1F, 21F, -5F));

        PartDefinition rightLeg = partdefinition.getChild("right_leg");
        PartDefinition bootsRight = rightLeg.addOrReplaceChild("boots_right", CubeListBuilder.create(), PartPose.offset(2, -12, 0));
        bootsRight.addOrReplaceChild("foot_base", CubeListBuilder.create()
                        .texOffs(12, 0).addBox(0F, offY, 0F, 4, 0, 4).mirror(),
                PartPose.offset(-4F, 24F, -2F));
        bootsRight.addOrReplaceChild("back", CubeListBuilder.create()
                        .texOffs(0, 8).addBox(0F, offY, 0F, 4, 4, 2).mirror(),
                PartPose.offset(-4F, 20F, 2F));
        bootsRight.addOrReplaceChild("front", CubeListBuilder.create()
                        .texOffs(0, 8).addBox(0F, offY, 0F, 4, 4, 2).mirror(),
                PartPose.offset(-4F, 20F, -4F));
        bootsRight.addOrReplaceChild("side2", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(0F, offY, 0F, 2, 4, 4).mirror(),
                PartPose.offset(-2F, 20F, -2F));
        bootsRight.addOrReplaceChild("side1", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(0F, offY, 0F, 2, 4, 4).mirror(),
                PartPose.offset(-6F, 20F, -2F));
        bootsRight.addOrReplaceChild("tip", CubeListBuilder.create()
                        .texOffs(12, 4).addBox(0F, offY, 0F, 2, 3, 1).mirror(),
                PartPose.offset(-3F, 21F, -5F));

        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    public static <A extends HumanoidModel<?>> A getModel(LivingEntity entity, ItemStack stack) {
        if (stack.isEmpty() || !(stack.getItem() instanceof ArmorItem)) {
            return null;
        }
        EquipmentSlot slot = ((ArmorItem) stack.getItem()).getSlot();

        if (slot == EquipmentSlot.FEET && modelBoots != null) {
            //noinspection unchecked
            return (A) modelBoots;
        }

        GlassBootsModel armor = new GlassBootsModel(Minecraft.getInstance().getEntityModels().bakeLayer(GLASS_BOOTS));
        armor.body.visible = false;
        armor.leftArm.visible = false;
        armor.rightArm.visible = false;

        armor.head.visible = false;

        armor.leftLeg.visible = false;
        armor.rightLeg.visible = false;

        armor.bootsRight.visible = false;
        armor.bootsLeft.visible = false;

        if (slot == EquipmentSlot.FEET) {
            armor.bootsLeft.visible = true;
            armor.bootsRight.visible = true;
            modelBoots = armor;
        }
        //noinspection unchecked
        return (A) armor;
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer consumer, int p_225598_3_, int p_225598_4_, float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
        super.renderToBuffer(poseStack, consumer, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
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

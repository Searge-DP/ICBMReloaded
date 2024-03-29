// Date: 10/13/2013 11:18:31 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package icbmrl.explosion.model.tiles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelMissileAssemblerClaw extends ModelBase
{
    // fields
    ModelRenderer baseCouple;
    ModelRenderer baseCoupleA;
    ModelRenderer fingerLowerA;
    ModelRenderer fingerUpperA;
    ModelRenderer baseCoupleB;
    ModelRenderer fingerUpperB;
    ModelRenderer fingerLowerB;
    ModelRenderer fingerLowerC;
    ModelRenderer fingerUpperC;
    ModelRenderer fingerLowerD;
    ModelRenderer fingerUpperD;

    public ModelMissileAssemblerClaw(int space)
    {
        textureWidth = 512;
        textureHeight = 512;

        baseCouple = new ModelRenderer(this, 0, 0);
        baseCouple.addBox(-1.5F + space, -3F, -3F, 3, 3, 6);
        baseCouple.setRotationPoint(0F, 21.5F, 0F);
        baseCouple.setTextureSize(512, 512);
        baseCouple.mirror = true;
        setRotation(baseCouple, 0F, 0F, 0F);

        baseCoupleA = new ModelRenderer(this, 0, 0);
        baseCoupleA.addBox(-2F + space, -4F, -4F, 1, 1, 8);
        baseCoupleA.setRotationPoint(0F, 22F, 0F);
        baseCoupleA.setTextureSize(512, 512);
        baseCoupleA.mirror = true;
        setRotation(baseCoupleA, 0F, 0F, 0F);

        fingerLowerA = new ModelRenderer(this, 0, 0);
        fingerLowerA.addBox(0F + space, 0F, -6F, 1, 1, 6);
        fingerLowerA.setRotationPoint(1F, 18F, -3F);
        fingerLowerA.setTextureSize(512, 512);
        fingerLowerA.mirror = true;
        setRotation(fingerLowerA, -0.8028515F, 0F, 0F);

        fingerUpperA = new ModelRenderer(this, 0, 0);
        fingerUpperA.addBox(-3F + space, 0F, -6F, 1, 1, 6);
        fingerUpperA.setRotationPoint(4F, 10F, -4F);
        fingerUpperA.setTextureSize(512, 512);
        fingerUpperA.mirror = true;
        setRotation(fingerUpperA, 0.7679449F, 0F, 0F);

        baseCoupleB = new ModelRenderer(this, 0, 0);
        baseCoupleB.addBox(1F + space, -4F, -4F, 1, 1, 8);
        baseCoupleB.setRotationPoint(0F, 22F, 0F);
        baseCoupleB.setTextureSize(512, 512);
        baseCoupleB.mirror = true;
        setRotation(baseCoupleB, 0F, 0F, 0F);

        fingerUpperB = new ModelRenderer(this, 0, 0);
        fingerUpperB.addBox(0F + space, 0F, -6F, 1, 1, 6);
        fingerUpperB.setRotationPoint(-2F, 10F, -4F);
        fingerUpperB.setTextureSize(512, 512);
        fingerUpperB.mirror = true;
        setRotation(fingerUpperB, 0.7679449F, 0F, 0F);

        fingerLowerB = new ModelRenderer(this, 0, 0);
        fingerLowerB.addBox(0F + space, 0F, -6F, 1, 1, 6);
        fingerLowerB.setRotationPoint(-2F, 18F, -3F);
        fingerLowerB.setTextureSize(512, 512);
        fingerLowerB.mirror = true;
        setRotation(fingerLowerB, -0.8028515F, 0F, 0F);

        fingerLowerC = new ModelRenderer(this, 0, 0);
        fingerLowerC.addBox(20F + space, 0F, 0F, 1, 1, 6);
        fingerLowerC.setRotationPoint(-19F, 18F, 3F);
        fingerLowerC.setTextureSize(512, 512);
        fingerLowerC.mirror = true;
        setRotation(fingerLowerC, 0.8028515F, 0F, 0F);

        fingerUpperC = new ModelRenderer(this, 0, 0);
        fingerUpperC.addBox(17F + space, 0F, 0F, 1, 1, 6);
        fingerUpperC.setRotationPoint(-16F, 10F, 4F);
        fingerUpperC.setTextureSize(512, 512);
        fingerUpperC.mirror = true;
        setRotation(fingerUpperC, -0.7679449F, 0F, 0F);

        fingerLowerD = new ModelRenderer(this, 0, 0);
        fingerLowerD.addBox(14F + space, 0F, 0F, 1, 1, 6);
        fingerLowerD.setRotationPoint(-16F, 18F, 3F);
        fingerLowerD.setTextureSize(512, 512);
        fingerLowerD.mirror = true;
        setRotation(fingerLowerD, 0.8028515F, 0F, 0F);

        fingerUpperD = new ModelRenderer(this, 0, 0);
        fingerUpperD.addBox(11F + space, 0F, 0F, 1, 1, 6);
        fingerUpperD.setRotationPoint(-13F, 10F, 4F);
        fingerUpperD.setTextureSize(512, 512);
        fingerUpperD.mirror = true;
        setRotation(fingerUpperD, -0.7679449F, 0F, 0F);
    }

    public void render(float f5)
    {
        baseCouple.render(f5);
        baseCoupleA.render(f5);
        fingerLowerA.render(f5);
        fingerUpperA.render(f5);
        baseCoupleB.render(f5);
        fingerUpperB.render(f5);
        fingerLowerB.render(f5);
        fingerLowerC.render(f5);
        fingerUpperC.render(f5);
        fingerLowerD.render(f5);
        fingerUpperD.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}

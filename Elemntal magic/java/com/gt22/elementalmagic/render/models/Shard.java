// Date: 03.06.2016 20:06:13
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package com.gt22.elementalmagic.render.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Shard extends ModelBase
{
  //fields
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape5;
    ModelRenderer Shape4;
    ModelRenderer Shape3;
    ModelRenderer Shape2;
  
  public Shard()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(0F, 0F, 0F, 2, 8, 2);
      Shape6.setRotationPoint(-1F, 12F, -1F);
      Shape6.setTextureSize(64, 64);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 10, 0);
      Shape7.addBox(0F, 0F, 0F, 2, 4, 1);
      Shape7.setRotationPoint(-1F, 14F, -2F);
      Shape7.setTextureSize(64, 64);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 10, 0);
      Shape8.addBox(0F, 0F, 0F, 2, 4, 1);
      Shape8.setRotationPoint(-1F, 14F, 1F);
      Shape8.setTextureSize(64, 64);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 10, 0);
      Shape9.addBox(0F, 0F, 0F, 1, 4, 2);
      Shape9.setRotationPoint(1F, 14F, -1F);
      Shape9.setTextureSize(64, 64);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 10, 0);
      Shape10.addBox(0F, 0F, 0F, 1, 4, 2);
      Shape10.setRotationPoint(-2F, 14F, -1F);
      Shape10.setTextureSize(64, 64);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 17, 0);
      Shape5.addBox(0F, 0F, 0F, 2, 2, 1);
      Shape5.setRotationPoint(-1F, 15F, 2F);
      Shape5.setTextureSize(64, 64);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 17, 0);
      Shape4.addBox(0F, 0F, 0F, 2, 2, 1);
      Shape4.setRotationPoint(-1F, 15F, -3F);
      Shape4.setTextureSize(64, 64);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 17, 0);
      Shape3.addBox(0F, 0F, 0F, 1, 2, 2);
      Shape3.setRotationPoint(-3F, 15F, -1F);
      Shape3.setTextureSize(64, 64);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 17, 0);
      Shape2.addBox(0F, 0F, 0F, 1, 2, 2);
      Shape2.setRotationPoint(2F, 15F, -1F);
      Shape2.setTextureSize(64, 64);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape5.render(f5);
    Shape4.render(f5);
    Shape3.render(f5);
    Shape2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }

}
